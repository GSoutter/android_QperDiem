package io.gogz.qperdiem.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionRepository;



public class QuestionWithContextViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;

    private LiveData<List<Question>> mAllQuestions;

    public QuestionWithContextViewModel (Application application) {
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
}
