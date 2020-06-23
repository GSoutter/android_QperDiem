package io.gogz.qperdiem.room_db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContextRepository {

    private ContextQDao mDao;
    private LiveData<List<ContextQ>> mAll;


    public ContextRepository(Application application) {
        QuestionsRoomDatabase db = QuestionsRoomDatabase.getDatabase(application);
        this.mDao = db.contextQDao();
        this.mAll = mDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<ContextQ>> getAll() {
        return mAll;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(ContextQ entry) {
        QuestionsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDao.insertOne(entry);
        });
    }

    public void deleteOne(ContextQ entry) {
        QuestionsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDao.deleteOne(entry);
        });
    }

    public LiveData<List<ContextWithQuestions>> getAllContextWithQuestions() {
        return mDao.getContextsWithQuestions();
    }
}