package hung.edu.apptienganh.engine.audit.content.review.feedback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HumanReviewFeedbackParser {

    public List<HumanReviewFeedbackItem> parseCsv(File csvFile) throws IOException {
        List<HumanReviewFeedbackItem> items = new ArrayList<>();
        if (!csvFile.exists()) {
            return items;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length < 9) continue; // Need exactly 9 parts based on format
                
                HumanReviewFeedbackItem item = new HumanReviewFeedbackItem();
                item.setReviewId(parts[0].trim());
                item.setDecision(parts[1].trim());
                item.setCorrectedText(parts[2].trim());
                item.setCorrectedLevel(parts[3].trim());
                item.setReason(parts[4].trim());
                item.setConfidence(parts[5].trim());
                item.setReviewerName(parts[6].trim());
                item.setReviewedAt(parts[7].trim());
                item.setNotes(parts[8].trim());
                
                items.add(item);
            }
        }
        return items;
    }
}
