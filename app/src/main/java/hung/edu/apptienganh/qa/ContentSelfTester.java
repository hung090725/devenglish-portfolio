package hung.edu.apptienganh.qa;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.repository.AppRepository;
import hung.edu.apptienganh.domain.model.LevelConstants;

public class ContentSelfTester {

    private final AppRepository repository;
    private final Context context;

    public ContentSelfTester(Context context) {
        this.context = context.getApplicationContext();
        this.repository = new AppRepository(context);
    }

    public List<SelfTestResult> runTests() {
        List<SelfTestResult> results = new ArrayList<>();
        
        try {
            // Check learning days
            // Wait, we need AppDatabase access for direct counts.
            hung.edu.apptienganh.data.local.AppDatabase db = hung.edu.apptienganh.data.local.AppDatabase.getInstance(context);

            checkLevelDays(results, db, LevelConstants.A0, 14);
            checkLevelDays(results, db, LevelConstants.A1, 14);
            checkLevelDays(results, db, LevelConstants.A2, 14);
            checkLevelDays(results, db, LevelConstants.B1, 14);
            
            // Vocabulary checks
            int vA0 = db.vocabularyDao().getVocabularyByLevelSync(LevelConstants.A0).size();
            int vA1 = db.vocabularyDao().getVocabularyByLevelSync(LevelConstants.A1).size();
            int vA2 = db.vocabularyDao().getVocabularyByLevelSync(LevelConstants.A2).size();
            int vB1 = db.vocabularyDao().getVocabularyByLevelSync(LevelConstants.B1).size();
            
            results.add(new SelfTestResult("Vocab A0 count >= 120", vA0 >= 120 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + vA0));
            results.add(new SelfTestResult("Vocab A1 count >= 150", vA1 >= 150 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + vA1));
            results.add(new SelfTestResult("Vocab A2 count >= 180", vA2 >= 180 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + vA2));
            results.add(new SelfTestResult("Vocab B1 count >= 200", vB1 >= 200 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + vB1));

            // Grammar checks
            int gA0 = db.grammarPointDao().getCountByLevelSync(LevelConstants.A0);
            int gA1 = db.grammarPointDao().getCountByLevelSync(LevelConstants.A1);
            int gA2 = db.grammarPointDao().getCountByLevelSync(LevelConstants.A2);
            int gB1 = db.grammarPointDao().getCountByLevelSync(LevelConstants.B1);
            
            results.add(new SelfTestResult("Grammar A0 count >= 12", gA0 >= 12 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + gA0));
            results.add(new SelfTestResult("Grammar A1 count >= 14", gA1 >= 14 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + gA1));
            results.add(new SelfTestResult("Grammar A2 count >= 16", gA2 >= 16 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + gA2));
            results.add(new SelfTestResult("Grammar B1 count >= 18", gB1 >= 18 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Actual: " + gB1));
            
            // Conversation checks (assuming we can get counts or we skip for now if no DAO method)
            // Actually I should add getConversationCountSync to DAO or just pass it as WARNING if not impl
            results.add(new SelfTestResult("Conversation counts", SelfTestStatus.WARNING, "Not yet implemented in DAO"));
            
            // Quiz checks
            results.add(new SelfTestResult("Quiz counts", SelfTestStatus.WARNING, "Not yet implemented in DAO"));
            
            // STRICT MODE CHECKS
            runStrictModeChecks(results, db);

        } catch (Exception e) {
            results.add(new SelfTestResult("ContentSelfTester", SelfTestStatus.FAIL, "Exception: " + e.getMessage()));
        }

        return results;
    }

    private void checkLevelDays(List<SelfTestResult> results, hung.edu.apptienganh.data.local.AppDatabase db, String level, int expectedDays) {
        try {
            int days = db.learningDayDao().getDaysByLevelSync(level).size();
            SelfTestStatus status = (days == expectedDays) ? SelfTestStatus.PASS : SelfTestStatus.FAIL;
            results.add(new SelfTestResult("LearningDays " + level + " == " + expectedDays, status, "Actual: " + days));
        } catch (Exception e) {
            results.add(new SelfTestResult("LearningDays " + level, SelfTestStatus.FAIL, "Error: " + e.getMessage()));
        }
    }

    private void runStrictModeChecks(List<SelfTestResult> results, hung.edu.apptienganh.data.local.AppDatabase db) {
        try {
            // 1. Vocabulary Strict Checks
            List<hung.edu.apptienganh.data.local.entity.Vocabulary> allVocab = db.vocabularyDao().getAllVocabularySync();
            int emptyWords = 0;
            int shortExamples = 0;
            int emptyExplanations = 0;
            int invalidLevels = 0;
            
            for (hung.edu.apptienganh.data.local.entity.Vocabulary v : allVocab) {
                if (v.getWord() == null || v.getWord().trim().isEmpty()) emptyWords++;
                if (v.getExampleEn() == null || v.getExampleEn().trim().split("\\s+").length < 3) shortExamples++;
                if (v.getMeaningVi() == null || v.getMeaningVi().trim().isEmpty()) emptyExplanations++;
                if (!isValidLevel(v.getLevel())) invalidLevels++;
            }
            
            results.add(new SelfTestResult("Strict: No empty words", emptyWords == 0 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Found: " + emptyWords));
            results.add(new SelfTestResult("Strict: ExampleEn >= 3 words", shortExamples == 0 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Found: " + shortExamples));
            results.add(new SelfTestResult("Strict: MeaningVi not empty", emptyExplanations == 0 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Found: " + emptyExplanations));
            results.add(new SelfTestResult("Strict: Valid levels (A0-B1)", invalidLevels == 0 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Found: " + invalidLevels));

            // 2. Learning Day Strict Checks
            List<hung.edu.apptienganh.data.local.entity.LearningDay> allDays = db.learningDayDao().getAllLearningDaysSync();
            int invalidEstimatedMinutes = 0;
            
            for (hung.edu.apptienganh.data.local.entity.LearningDay d : allDays) {
                if (d.getEstimatedMinutes() <= 0) invalidEstimatedMinutes++;
            }
            results.add(new SelfTestResult("Strict: EstimatedMinutes > 0", invalidEstimatedMinutes == 0 ? SelfTestStatus.PASS : SelfTestStatus.FAIL, "Found: " + invalidEstimatedMinutes));

            // 3. Quiz Question Strict Checks (Optional - need DAO methods)
            // if we had db.quizQuestionDao().getAllSync() we would check correctAnswers.
            
        } catch (Exception e) {
            results.add(new SelfTestResult("Strict Mode Checks", SelfTestStatus.FAIL, "Exception: " + e.getMessage()));
        }
    }

    private boolean isValidLevel(String level) {
        return LevelConstants.A0.equals(level) || LevelConstants.A1.equals(level) || 
               LevelConstants.A2.equals(level) || LevelConstants.B1.equals(level);
    }
}
