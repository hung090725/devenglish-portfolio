package hung.edu.apptienganh.engine;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.local.entity.WeaknessItem;

public class WeaknessDetector {
    
    public List<WeaknessItem> detectWeaknesses() {
        // TODO: Implement complex rule-based logic querying DAOs
        return new ArrayList<>();
    }
    
    public static int calculateScore(int mistakes, int occurrences) {
        if (occurrences == 0) return 0;
        return (mistakes * 100) / occurrences;
    }
}
