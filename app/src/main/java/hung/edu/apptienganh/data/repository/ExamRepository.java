package hung.edu.apptienganh.data.repository;

import android.content.Context;
import hung.edu.apptienganh.data.local.AppDatabase;
import hung.edu.apptienganh.data.local.dao.ExamDao;
import hung.edu.apptienganh.data.local.entity.ExamSession;

public class ExamRepository {
    private final ExamDao examDao;

    public ExamRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.examDao = db.examDao();
    }
    
    public void startExamSession(ExamSession session) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            examDao.insertSession(session);
        });
    }
}
