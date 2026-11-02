package hung.edu.apptienganh.engine;

import hung.edu.apptienganh.data.local.entity.SentenceBuilderExercise;
import hung.edu.apptienganh.data.local.entity.SentenceBuilderResult;

public class SentenceBuilderScorer {

    public SentenceBuilderResult scoreSentence(SentenceBuilderExercise exercise, String userSentence) {
        SentenceBuilderResult result = new SentenceBuilderResult();
        result.exerciseId = exercise.id;
        result.userSentence = userSentence;
        result.createdAt = System.currentTimeMillis();

        if (userSentence == null || userSentence.isEmpty()) {
            result.isCorrect = false;
            result.score = 0;
            return result;
        }

        // Normalize punctuation and spaces for comparison
        String normalizedExpected = exercise.correctSentence.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim();
        String normalizedActual = userSentence.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim();

        if (normalizedExpected.equals(normalizedActual)) {
            result.isCorrect = true;
            result.score = 100;
        } else {
            result.isCorrect = false;
            // Basic partial scoring based on correct words sequence length
            result.score = 0; 
        }

        return result;
    }
}
