package hung.edu.apptienganh.data.local.migration;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public final class DatabaseMigrations {

  public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase db) {
      db.execSQL("ALTER TABLE day_progress ADD COLUMN bestQuizScore INTEGER NOT NULL DEFAULT 0");
      db.execSQL("ALTER TABLE day_progress ADD COLUMN status TEXT NOT NULL DEFAULT 'LOCKED'");
      db.execSQL("ALTER TABLE day_progress ADD COLUMN currentSectionIndex INTEGER NOT NULL DEFAULT 0");
      db.execSQL("ALTER TABLE day_progress ADD COLUMN lastOpenedAt INTEGER NOT NULL DEFAULT 0");

      db.execSQL("UPDATE day_progress SET status = 'COMPLETED' WHERE isCompleted = 1");
      db.execSQL("UPDATE day_progress SET status = 'AVAILABLE' WHERE isUnlocked = 1 AND isCompleted = 0");
      db.execSQL("UPDATE day_progress SET bestQuizScore = quizScore WHERE quizScore > bestQuizScore");

      db.execSQL("ALTER TABLE speaking_results ADD COLUMN userId INTEGER NOT NULL DEFAULT 1");
      db.execSQL("ALTER TABLE speaking_results ADD COLUMN learningDayId INTEGER NOT NULL DEFAULT 0");
      db.execSQL("ALTER TABLE speaking_results ADD COLUMN missingWords TEXT");
      db.execSQL("ALTER TABLE speaking_results ADD COLUMN extraWords TEXT");
      db.execSQL("ALTER TABLE speaking_results ADD COLUMN feedbackVi TEXT");

      db.execSQL("CREATE TABLE IF NOT EXISTS vocabulary_review_progress ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "vocabularyId INTEGER NOT NULL, "
          + "userId INTEGER NOT NULL, "
          + "masteryLevel INTEGER NOT NULL, "
          + "easeFactor REAL NOT NULL, "
          + "intervalDays INTEGER NOT NULL, "
          + "reviewCount INTEGER NOT NULL, "
          + "correctStreak INTEGER NOT NULL, "
          + "wrongCount INTEGER NOT NULL, "
          + "nextReviewAt INTEGER NOT NULL, "
          + "lastReviewedAt INTEGER NOT NULL, "
          + "updatedAt INTEGER NOT NULL)");
      db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_vocabulary_review_progress_userId_vocabularyId "
          + "ON vocabulary_review_progress (userId, vocabularyId)");

      db.execSQL("CREATE TABLE IF NOT EXISTS quiz_attempts ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "userId INTEGER NOT NULL, "
          + "learningDayId INTEGER NOT NULL, "
          + "score INTEGER NOT NULL, "
          + "totalQuestions INTEGER NOT NULL, "
          + "correctCount INTEGER NOT NULL, "
          + "wrongCount INTEGER NOT NULL, "
          + "createdAt INTEGER NOT NULL)");

      db.execSQL("CREATE TABLE IF NOT EXISTS quiz_answers ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "attemptId INTEGER NOT NULL, "
          + "questionId INTEGER NOT NULL, "
          + "selectedAnswer TEXT, "
          + "correctAnswer TEXT, "
          + "isCorrect INTEGER NOT NULL, "
          + "explanationVi TEXT)");
    }
  };

  /**
   * Sprint 4: tracks idempotent content seeding (A1 upgrade path).
   * TODO(production): remove fallbackToDestructiveMigration before release.
   */
  public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase db) {
      db.execSQL("CREATE TABLE IF NOT EXISTS content_seed_meta ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "seedKey TEXT NOT NULL, "
          + "seededAt INTEGER NOT NULL)");
      db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_content_seed_meta_seedKey "
          + "ON content_seed_meta (seedKey)");
    }
  };

  public static final Migration MIGRATION_4_5 = new Migration(4, 5) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase db) {
      db.execSQL("CREATE TABLE IF NOT EXISTS beta_feedback ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "category TEXT, "
          + "screenName TEXT, "
          + "description TEXT, "
          + "stepsToReproduce TEXT, "
          + "expectedResult TEXT, "
          + "actualResult TEXT, "
          + "severity TEXT, "
          + "deviceInfo TEXT, "
          + "appVersionName TEXT, "
          + "appVersionCode INTEGER NOT NULL, "
          + "createdAt INTEGER NOT NULL, "
          + "status TEXT)");
    }
  };

  public static final Migration MIGRATION_5_6 = new Migration(5, 6) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase db) {
      db.execSQL("CREATE TABLE IF NOT EXISTS real_device_test_cases ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "title TEXT, "
          + "category TEXT, "
          + "status TEXT, "
          + "note TEXT, "
          + "updatedAt INTEGER NOT NULL, "
          + "orderIndex INTEGER NOT NULL)");
    }
  };

  public static final Migration MIGRATION_6_7 = new Migration(6, 7) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase db) {
      db.execSQL("CREATE TABLE IF NOT EXISTS achievements ("
          + "code TEXT PRIMARY KEY NOT NULL, "
          + "title TEXT, "
          + "description TEXT, "
          + "isUnlocked INTEGER NOT NULL, "
          + "unlockedAt INTEGER NOT NULL)");

      db.execSQL("CREATE TABLE IF NOT EXISTS placement_results ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "timestamp INTEGER NOT NULL, "
          + "score INTEGER NOT NULL, "
          + "totalQuestions INTEGER NOT NULL, "
          + "suggestedLevel TEXT, "
          + "explanationVi TEXT)");

      db.execSQL("CREATE TABLE IF NOT EXISTS listening_practice_results ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "level TEXT, "
          + "sentenceEn TEXT, "
          + "userAnswer TEXT, "
          + "score INTEGER NOT NULL, "
          + "createdAt INTEGER NOT NULL)");

      db.execSQL("CREATE TABLE IF NOT EXISTS grammar_mistake_records ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "grammarPointId INTEGER NOT NULL, "
          + "level TEXT, "
          + "lastMistakeAt INTEGER NOT NULL, "
          + "mistakeCount INTEGER NOT NULL, "
          + "isResolved INTEGER NOT NULL)");

      db.execSQL("CREATE TABLE IF NOT EXISTS quiz_wrong_answer_reviews ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "quizQuestionId INTEGER NOT NULL, "
          + "level TEXT, "
          + "lastMistakeAt INTEGER NOT NULL, "
          + "mistakeCount INTEGER NOT NULL, "
          + "isResolved INTEGER NOT NULL)");

      db.execSQL("CREATE TABLE IF NOT EXISTS speaking_weak_sentences ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "level TEXT, "
          + "sentenceEn TEXT, "
          + "sentenceVi TEXT, "
          + "ipa TEXT, "
          + "lastMistakeAt INTEGER NOT NULL, "
          + "lowestScore INTEGER NOT NULL, "
          + "isResolved INTEGER NOT NULL)");

      db.execSQL("CREATE TABLE IF NOT EXISTS favorite_vocabulary ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "vocabularyId INTEGER NOT NULL, "
          + "level TEXT, "
          + "addedAt INTEGER NOT NULL)");
    }
  };

  public static final Migration MIGRATION_7_8 = new Migration(7, 8) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase db) {
      db.execSQL("CREATE TABLE IF NOT EXISTS learning_insights ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "type TEXT, title TEXT, descriptionVi TEXT, targetId TEXT, priority TEXT, createdAt INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS weakness_items ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "category TEXT, level TEXT, title TEXT, descriptionVi TEXT, score INTEGER NOT NULL, priority TEXT, recommendedAction TEXT, targetActivity TEXT, targetId TEXT)");
      db.execSQL("CREATE TABLE IF NOT EXISTS writing_prompts ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "level TEXT, type TEXT, title TEXT, instructionVi TEXT, promptText TEXT, expectedKeywords TEXT, sampleAnswer TEXT, explanationVi TEXT, orderIndex INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS writing_attempts ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "promptId INTEGER NOT NULL, userAnswer TEXT, score INTEGER NOT NULL, feedbackVi TEXT, createdAt INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS sentence_builder_exercises ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "level TEXT, scrambledWords TEXT, correctSentence TEXT, meaningVi TEXT, grammarFocus TEXT, explanationVi TEXT, orderIndex INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS sentence_builder_results ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "exerciseId INTEGER NOT NULL, userSentence TEXT, isCorrect INTEGER NOT NULL, score INTEGER NOT NULL, createdAt INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS error_notes ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "sourceType TEXT, level TEXT, title TEXT, originalText TEXT, correctedText TEXT, explanationVi TEXT, userNote TEXT, isResolved INTEGER NOT NULL, createdAt INTEGER NOT NULL, resolvedAt INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS recent_word_views ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "wordId INTEGER NOT NULL, viewedAt INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS grammar_practice_exercises ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "grammarPointId INTEGER NOT NULL, level TEXT, type TEXT, questionText TEXT, optionsJson TEXT, correctOptionIndex INTEGER NOT NULL, explanationVi TEXT)");
      db.execSQL("CREATE TABLE IF NOT EXISTS grammar_practice_results ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "exerciseId INTEGER NOT NULL, isCorrect INTEGER NOT NULL, userAnswerIndex INTEGER NOT NULL, createdAt INTEGER NOT NULL)");
      db.execSQL("CREATE TABLE IF NOT EXISTS learning_events ("
          + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "eventType TEXT, screenName TEXT, level TEXT, metadataJson TEXT, createdAt INTEGER NOT NULL)");
    }
  };

  private DatabaseMigrations() {}
}
