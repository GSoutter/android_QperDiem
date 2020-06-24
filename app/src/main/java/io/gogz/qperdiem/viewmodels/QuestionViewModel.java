package io.gogz.qperdiem.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionContextCrossRef;
import io.gogz.qperdiem.room_db.QuestionRepository;
import io.gogz.qperdiem.room_db.QuestionWithContexts;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;

    private LiveData<List<Question>> mAllQuestions;

    public QuestionViewModel (Application application) {
        super(application);
        this.mRepository = new QuestionRepository(application);
        mAllQuestions = mRepository.getQuestions();

    }

    public LiveData<List<Question>> getQuestions() {
        return mAllQuestions;
    }

    public void insert(Question question) {
        mRepository.insert(question);
    }

    public void deleteOne(Question question) {
        mRepository.deleteOne(question);
    }

    public void insertOneQuestionContextCrossRef(QuestionContextCrossRef questionContextCrossRef) {
        mRepository.insertOneQuestionContextCrossRef(questionContextCrossRef);
    }

    public void deleteOneQuestionContextCrossRef(QuestionContextCrossRef questionContextCrossRef) {
        mRepository.deleteOneQuestionContextCrossRef(questionContextCrossRef);
    }

    public LiveData<QuestionWithContexts> getOneQuestionWithContexts(long questionId){
        return mRepository.getOneQuestionWithContexts(questionId);
    };
}
