package hung.edu.apptienganh.engine;

import hung.edu.apptienganh.data.local.entity.LearningGoal;

public class GoalPlanGenerator {
    public LearningGoal generatePlan(String goalType) {
        LearningGoal goal = new LearningGoal();
        goal.type = goalType;
        goal.title = "Travel English";
        goal.descriptionVi = "Học tiếng Anh để du lịch tự túc";
        goal.recommendedLevels = "A1, A2";
        goal.dailyMinutes = 15;
        goal.isActive = true;
        goal.createdAt = System.currentTimeMillis();
        return goal;
    }
}
