package io.gogz.qperdiem.room_db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionDao mQuestionDao;
    private LiveData<List<Question>> mAllQuestions;


    public QuestionRepository(Application application) {
        QuestionsRoomDatabase db = QuestionsRoomDatabase.getDatabase(application);
        this.mQuestionDao = db.questionDao();
        this.mAllQuestions = mQuestionDao.getQuestions();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Question>> getQuestions() {
        return mAllQuestions;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Question question) {
        QuestionsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionDao.insertQuestion(question);
        });
    }
}

