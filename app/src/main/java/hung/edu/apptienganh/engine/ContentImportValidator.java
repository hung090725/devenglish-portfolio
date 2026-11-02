package hung.edu.apptienganh.engine;

import org.json.JSONException;
import org.json.JSONObject;

public class ContentImportValidator {
    public boolean validateVocabJson(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            return obj.has("word") && obj.has("meaningVi") && obj.has("level");
        } catch (JSONException e) {
            return false;
        }
    }
}
