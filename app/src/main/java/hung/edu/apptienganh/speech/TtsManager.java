package hung.edu.apptienganh.speech;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class TtsManager {
    private static final String TAG = "TtsManager";
    private TextToSpeech tts;
    private boolean isReady = false;
    private final Context context;

    public TtsManager(Context context) {
        this.context = context.getApplicationContext();
        initTts();
    }

    private void initTts() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
            isReady = false;
        }

        tts = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                isReady = true;
                int result = tts.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e(TAG, "Ngôn ngữ tiếng Anh US không được hỗ trợ trên thiết bị này.");
                }
            } else {
                Log.e(TAG, "Khởi tạo TextToSpeech thất bại.");
                isReady = false;
            }
        });
    }

    public void speakEnglishUs(String text) {
        speak(text, Locale.US, "US_SPEAK");
    }

    public void speakEnglishUk(String text) {
        speak(text, Locale.UK, "UK_SPEAK");
    }

    private void speak(String text, Locale locale, String utteranceId) {
        if (text == null || text.trim().isEmpty()) {
            Toast.makeText(context, "Không có nội dung để phát âm.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isReady || tts == null) {
            Toast.makeText(context, "Bộ phát âm (TTS) chưa sẵn sàng. Đang khởi tạo lại...", Toast.LENGTH_SHORT).show();
            initTts();
            return;
        }

        try {
            int langResult = tts.setLanguage(locale);
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "Ngôn ngữ phát âm chưa được hỗ trợ trên thiết bị.", Toast.LENGTH_SHORT).show();
                return;
            }

            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi phát âm: " + e.getMessage());
            Toast.makeText(context, "Lỗi phát âm. Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop() {
        if (tts != null) {
            tts.stop();
        }
    }

    public void shutdown() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
            isReady = false;
        }
    }

    public boolean isReady() {
        return isReady;
    }
}
