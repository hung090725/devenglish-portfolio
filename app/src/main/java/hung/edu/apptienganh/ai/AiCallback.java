package hung.edu.apptienganh.ai;

public interface AiCallback<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}
