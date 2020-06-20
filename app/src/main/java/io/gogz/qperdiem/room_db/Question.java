package io.gogz.qperdiem.room_db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_question")
public class Question {

    @PrimaryKey
    public long roomQuestionId;
    public String text;
    //   List<Rating> ratings; moved to a separated entity
    //   List<Context> contexts moved to a separate entity  ;
    public boolean active;
    public String icon;

}
