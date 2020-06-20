package io.gogz.qperdiem.room;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_rating")
public class roomRating {

    @PrimaryKey
    public long roomRatingId;
    public float score;


}
