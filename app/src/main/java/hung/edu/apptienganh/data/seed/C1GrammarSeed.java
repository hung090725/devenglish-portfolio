package hung.edu.apptienganh.data.seed;

import hung.edu.apptienganh.data.local.entity.GrammarPoint;
import java.util.ArrayList;
import java.util.List;

public class C1GrammarSeed {
    public static List<GrammarPoint> getGrammarPoints() {
        List<GrammarPoint> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(new GrammarPoint("C1 Grammar " + i, "Formula " + i, "Usage " + i, "Signal words",
            "Correct Example " + i, "Wrong Example " + i, "Explanation " + i, "Practice questions " + i, "C1"));
        }
        return list;
    }
}
