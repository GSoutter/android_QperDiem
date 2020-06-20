package io.gogz.qperdiem.room_db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_rating")
public class Rating {

    @PrimaryKey
    public long roomRatingId;
    public float score;


}
