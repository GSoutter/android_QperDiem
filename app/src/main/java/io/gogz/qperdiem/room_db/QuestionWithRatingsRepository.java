package io.gogz.qperdiem.room_db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionWithRatingsRepository {

    private QuestionWithRatingsDao mQuestionWithRatingsDao;
    private LiveData<List<QuestionWithRatings>> mAllQuestions;


    QuestionWithRatingsRepository(Application application) {
        QuestionsRoomDatabase db = QuestionsRoomDatabase.getDatabase(application);
        this.mQuestionWithRatingsDao = db.questionWithRatingsDao();
        this.mAllQuestions = mQuestionWithRatingsDao.getQuestionsWithRatings();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<QuestionWithRatings>> getAllQuestions() {
        return mAllQuestions;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Question question) {
        QuestionsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionWithRatingsDao.insertQuestion(question);
        });
    }
}
