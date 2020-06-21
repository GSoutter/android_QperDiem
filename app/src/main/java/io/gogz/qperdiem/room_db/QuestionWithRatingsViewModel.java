//package io.gogz.qperdiem.room_db;
//
//import android.app.Application;
//
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import java.util.List;
//
//public class QuestionWithRatingsViewModel extends AndroidViewModel {
//    private QuestionWithRatingsRepository mRepository;
//
//    private LiveData<List<QuestionWithRatings>> mAllQuestions;
//
//    public QuestionWithRatingsViewModel (Application application) {
//        super(application);
//        this.mRepository = new QuestionWithRatingsRepository(application);
//        mAllQuestions = mRepository.getAllQuestions();
//
//    }
//
//    public LiveData<List<QuestionWithRatings>> getAllQuestions() {
//        return mAllQuestions;
//    }
//
//    public void insert(Question question) {
//        mRepository.insert(question);
//    }
//
//}
