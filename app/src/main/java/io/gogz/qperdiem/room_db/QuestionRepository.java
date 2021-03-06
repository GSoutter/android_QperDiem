package io.gogz.qperdiem.room_db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionDao mQuestionDao;
    private QuestionContextCrossRefDao mQuestionContextCrossRefDao;
    private LiveData<List<Question>> mAllQuestions;


    public QuestionRepository(Application application) {
        QuestionsRoomDatabase db = QuestionsRoomDatabase.getDatabase(application);
        this.mQuestionDao = db.questionDao();
        this.mQuestionContextCrossRefDao = db.questionContextCrossRefDao();
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

    public void insertOneQuestionContextCrossRef(QuestionContextCrossRef questionContextCrossRef) {
        QuestionsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
        });
    }

    public void deleteOneQuestionContextCrossRef(QuestionContextCrossRef questionContextCrossRef) {
        QuestionsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionContextCrossRefDao.deleteOne(questionContextCrossRef);
        });
    }

    public void deleteOne(Question question) {
        QuestionsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionDao.deleteQuestion(question);
        });
    }

    public LiveData<QuestionWithContexts> getOneQuestionWithContexts(long questionId) {
        return mQuestionDao.getOneQuestionWithContexts(questionId);
    }


}

