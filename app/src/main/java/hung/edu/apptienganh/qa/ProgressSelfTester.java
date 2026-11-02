package hung.edu.apptienganh.qa;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hung.edu.apptienganh.data.repository.AppRepository;

public class ProgressSelfTester {
    private final AppRepository repository;

    public ProgressSelfTester(Context context) {
        this.repository = new AppRepository(context);
    }

    public List<SelfTestResult> runTests() {
        List<SelfTestResult> results = new ArrayList<>();
        
        try {
            // A1 locked if A0 not finished?
            // Actually, we can just check the current state
            boolean a0Completed = repository.isA0CompletedSync();
            boolean a1Unlocked = repository.isA1UnlockedSync();
            
            if (a0Completed && a1Unlocked) {
                results.add(new SelfTestResult("A1 unlocked logic", SelfTestStatus.PASS, "A0 finished, A1 unlocked"));
            } else if (!a0Completed && !a1Unlocked) {
                results.add(new SelfTestResult("A1 locked logic", SelfTestStatus.PASS, "A0 not finished, A1 locked"));
            } else {
                results.add(new SelfTestResult("A1 lock logic", SelfTestStatus.FAIL, "Mismatch: A0 completed=" + a0Completed + ", A1 unlocked=" + a1Unlocked));
            }
            
            // ContinueDayResolver crash test
            repository.resolveContinueDay("A0", (dayIndex, sectionIndex) -> {
                results.add(new SelfTestResult("ContinueDayResolver", SelfTestStatus.PASS, "Resolved successfully"));
            });
            
        } catch (Exception e) {
            results.add(new SelfTestResult("ProgressSelfTester", SelfTestStatus.FAIL, "Exception: " + e.getMessage()));
        }
        
        return results;
    }
}
