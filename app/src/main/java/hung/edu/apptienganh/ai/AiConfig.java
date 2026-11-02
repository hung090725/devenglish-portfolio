package hung.edu.apptienganh.ai;

import android.content.Context;
import android.content.SharedPreferences;

public final class AiConfig {

    private static final String PREF_NAME = "ai_tutor_config";
    private static final String KEY_GEMINI_API_KEY = "gemini_api_key";
    private static final String KEY_AI_MODEL = "ai_model";
    private static final String KEY_USE_OFFLINE_FALLBACK = "use_offline_fallback";

    public static final String DEFAULT_MODEL = "gemini-1.5-flash";

    private AiConfig() {}

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getApiKey(Context context) {
        return getPrefs(context).getString(KEY_GEMINI_API_KEY, "").trim();
    }

    public static void setApiKey(Context context, String apiKey) {
        getPrefs(context).edit().putString(KEY_GEMINI_API_KEY, apiKey != null ? apiKey.trim() : "").apply();
    }

    public static boolean hasApiKey(Context context) {
        String key = getApiKey(context);
        return key != null && !key.isEmpty();
    }

    public static String getModel(Context context) {
        return getPrefs(context).getString(KEY_AI_MODEL, DEFAULT_MODEL);
    }

    public static void setModel(Context context, String model) {
        getPrefs(context).edit().putString(KEY_AI_MODEL, model != null ? model.trim() : DEFAULT_MODEL).apply();
    }

    public static boolean isOfflineFallbackAllowed(Context context) {
        return getPrefs(context).getBoolean(KEY_USE_OFFLINE_FALLBACK, true);
    }

    public static void setOfflineFallbackAllowed(Context context, boolean allowed) {
        getPrefs(context).edit().putBoolean(KEY_USE_OFFLINE_FALLBACK, allowed).apply();
    }
}
