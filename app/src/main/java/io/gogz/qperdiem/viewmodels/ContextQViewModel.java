package io.gogz.qperdiem.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.room_db.ContextQRepository;
import io.gogz.qperdiem.room_db.ContextWithQuestions;

public class ContextQViewModel extends AndroidViewModel {

    private ContextQRepository mRepository;

    private LiveData<List<ContextWithQuestions>> mAllContextsWithRatings;

    private LiveData<List<ContextQ>> mAllContexts;

    public ContextQViewModel (Application application) {
        super(application);
        this.mRepository = new ContextQRepository(application);
        mAllContextsWithRatings = mRepository.getAllContextWithQuestions();
        mAllContexts = mRepository.getAllContexts();

    }

    public LiveData<List<ContextWithQuestions>> getAllContextWithQuestions() {
        return mAllContextsWithRatings;
    }

    public LiveData<List<ContextQ>> getAllContexts() {
        return mAllContexts;
    }

    public void insert(ContextQ entry) {
        mRepository.insert(entry);
    }

    public void deleteOne(ContextQ entry) {
        mRepository.deleteOne(entry);
    }

    public LiveData<ContextWithQuestions> getOneWithQuestions(long contextId){
        return mRepository.getOneWithQuestions(contextId);
    };
}
