package io.gogz.qperdiem.room_db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ratings")
public class Rating {

    @PrimaryKey
    public long id;
    public float score;
    public long questionId;


}
