package hung.edu.apptienganh.engine;

import java.util.ArrayList;
import java.util.List;
import hung.edu.apptienganh.data.local.entity.WeaknessItem;

public class DailyPlanGenerator2 {
    public List<String> generateAdaptivePlan(List<WeaknessItem> weaknesses) {
        List<String> plan = new ArrayList<>();
        plan.add("Continue lesson");
        plan.add("Review weak words");
        plan.add("Fix one grammar mistake");
        plan.add("Practice one low-score speaking sentence");
        plan.add("One listening drill");
        plan.add("One writing prompt");
        plan.add("One sentence builder exercise");
        return plan;
    }
}
