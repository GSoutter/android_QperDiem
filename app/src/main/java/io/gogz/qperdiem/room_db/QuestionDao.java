package io.gogz.qperdiem.room_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM questions")
    public LiveData<List<Question>> getQuestions();

    @Transaction
    @Query("DELETE FROM questions")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertQuestion(Question question);

    @Transaction
    @Delete
    public void deleteQuestion(Question...questions);

    @Transaction
    @Query("Select * from questions")
    public LiveData<List<QuestionWithRatings>> getQuestionsWithRatings();

    @Transaction
    @Query("Select * from questions")
    public LiveData<List<QuestionWithContexts>> getQuestionsWithContexts();

    @Transaction
    @Query("Select * from questions WHERE questionId = :questionId")
    public QuestionWithContexts getOneQuestionsWithContexts(long questionId);


}
