package io.gogz.qperdiem.room_db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

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
}
