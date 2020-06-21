//package io.gogz.qperdiem.room_db;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//import androidx.room.Transaction;
//
//import java.util.List;
//
//@Dao
//public interface QuestionWithRatingsDao {
//
//    @Transaction
//    @Query("SELECT * FROM questions")
//    public LiveData<List<QuestionWithRatings>> getQuestionsWithRatings();
//
//    @Transaction
//    @Query("DELETE FROM questions")
//    void deleteAll();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void insertQuestion(Question...questions);
//
//    @Transaction
//    @Delete
//    public void deleteQuestion(Question...questions);
//}
