package hung.edu.apptienganh.speech;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechRecognitionManager {
    private static final String TAG = "SpeechRecognition";
    public static final int RECORD_AUDIO_REQUEST_CODE = 101;

    public interface SpeechRecognitionCallback {
        void onReadyForSpeech();
        void onBeginningOfSpeech();
        void onRmsChanged(float rmsdB);
        void onBufferReceived(byte[] buffer);
        void onEndOfSpeech();
        void onError(String errorMessage);
        void onResults(String recognizedText);
        void onPartialResults(String partialText);
    }

    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private final Context context;
    private SpeechRecognitionCallback callback;
    private boolean isListening = false;

    public SpeechRecognitionManager(Context context, SpeechRecognitionCallback callback) {
        this.context = context.getApplicationContext();
        this.callback = callback;
        initSpeechRecognizer();
    }

    private void initSpeechRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
            speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.toString());
            speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
            
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {
                    isListening = true;
                    if (callback != null) callback.onReadyForSpeech();
                }

                @Override
                public void onBeginningOfSpeech() {
                    if (callback != null) callback.onBeginningOfSpeech();
                }

                @Override
                public void onRmsChanged(float rmsdB) {
                    if (callback != null) callback.onRmsChanged(rmsdB);
                }

                @Override
                public void onBufferReceived(byte[] buffer) {
                    if (callback != null) callback.onBufferReceived(buffer);
                }

                @Override
                public void onEndOfSpeech() {
                    isListening = false;
                    if (callback != null) callback.onEndOfSpeech();
                }

                @Override
                public void onError(int error) {
                    isListening = false;
                    String message;
                    switch (error) {
                        case SpeechRecognizer.ERROR_AUDIO:
                            message = "Lỗi thu âm thanh.";
                            break;
                        case SpeechRecognizer.ERROR_CLIENT:
                            message = "Lỗi phía client.";
                            break;
                        case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                            message = "Chưa cấp quyền ghi âm.";
                            break;
                        case SpeechRecognizer.ERROR_NETWORK:
                            message = "Lỗi kết nối mạng.";
                            break;
                        case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                            message = "Hết thời gian kết nối.";
                            break;
                        case SpeechRecognizer.ERROR_NO_MATCH:
                            message = "Không nhận diện được giọng nói. Vui lòng nói lại.";
                            break;
                        case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                            message = "Trình nhận dạng đang bận.";
                            break;
                        case SpeechRecognizer.ERROR_SERVER:
                            message = "Lỗi từ máy chủ.";
                            break;
                        case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                            message = "Không nghe thấy tiếng nói.";
                            break;
                        default:
                            message = "Lỗi không xác định: " + error;
                            break;
                    }
                    Log.e(TAG, "SpeechRecognizer Error: " + message);
                    if (callback != null) callback.onError(message);
                }

                @Override
                public void onResults(Bundle results) {
                    isListening = false;
                    ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (matches != null && !matches.isEmpty()) {
                        if (callback != null) callback.onResults(matches.get(0));
                    } else {
                        if (callback != null) callback.onError("Không nhận diện được từ nào.");
                    }
                }

                @Override
                public void onPartialResults(Bundle partialResults) {
                    ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (matches != null && !matches.isEmpty() && callback != null) {
                        callback.onPartialResults(matches.get(0));
                    }
                }

                @Override
                public void onEvent(int eventType, Bundle params) {}
            });
        }
    }

    public static boolean checkPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_REQUEST_CODE);
    }

    public void startListening() {
        if (!checkPermission(context)) {
            if (callback != null) callback.onError("Quyền ghi âm chưa được cấp.");
            return;
        }

        if (speechRecognizer == null) {
            initSpeechRecognizer();
        }

        if (speechRecognizer != null && !isListening) {
            try {
                speechRecognizer.startListening(speechRecognizerIntent);
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi gọi startListening: " + e.getMessage());
                if (callback != null) callback.onError("Không thể khởi động trình nhận dạng.");
            }
        } else if (speechRecognizer == null && callback != null) {
            callback.onError("Trình nhận dạng giọng nói không khả dụng trên thiết bị này.");
        }
    }

    public void stopListening() {
        if (speechRecognizer != null && isListening) {
            speechRecognizer.stopListening();
            isListening = false;
        }
    }

    public void destroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
            speechRecognizer = null;
        }
        callback = null;
    }

    public boolean isListening() {
        return isListening;
    }
}
