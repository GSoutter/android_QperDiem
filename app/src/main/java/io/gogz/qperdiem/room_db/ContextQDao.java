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
public interface ContextQDao {
    @Query("SELECT * FROM contexts")
    public LiveData<List<ContextQ>> getAll();

    @Transaction
    @Query("DELETE FROM contexts")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertOne(ContextQ context);

    @Transaction
    @Delete
    public void deleteOne(ContextQ context);

    @Transaction
    @Query("Select * from contexts")
    public LiveData<List<ContextWithQuestions>> getContextsWithQuestions();

    @Transaction
    @Query("Select * from contexts WHERE contextId = :contextId")
    public LiveData<ContextWithQuestions> getOneWithQuestions(long contextId);
}
