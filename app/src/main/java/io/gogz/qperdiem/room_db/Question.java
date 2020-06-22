package io.gogz.qperdiem.room_db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {

    @PrimaryKey(autoGenerate = true)
    public long questionId;
    public String text;
    //   List<Rating> ratings; moved to a separated entity
    //   List<Context> contexts moved to a separate entity  ;
    public boolean active;
    public String icon;
    public String test;


    public Rating addRating(float score){
        Rating rating = new Rating();
        rating.score = score;
        rating.questionId = this.questionId;
        rating.date = "tuesday rating";
        return rating;
    }


}
