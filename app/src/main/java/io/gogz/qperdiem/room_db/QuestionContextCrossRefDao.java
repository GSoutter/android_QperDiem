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
public interface QuestionContextCrossRefDao {
    @Query("SELECT * FROM question_context")
    public LiveData<List<QuestionContextCrossRef>> getAll();

    @Transaction
    @Query("DELETE FROM question_context")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertOne(QuestionContextCrossRef questionContextCrossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMany(List<QuestionContextCrossRef> questionContextCrossRefs);

    @Transaction
    @Delete
    public void deleteOne(QuestionContextCrossRef questionContextCrossRef);

    @Transaction
    @Delete
    public void deleteMany(List<QuestionContextCrossRef> questionContextCrossRefs);

}
