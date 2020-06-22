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
public interface RatingDao {
    @Query("SELECT * FROM ratings")
    public LiveData<List<Rating>> getRatings();

    @Transaction
    @Query("DELETE FROM ratings")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Rating...ratings);

    @Transaction
    @Delete
    public void delete(Rating...ratings);
}
