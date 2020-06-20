package io.gogz.qperdiem.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

import io.gogz.qperdiem.models.Context;
import io.gogz.qperdiem.models.Rating;

@Entity(tableName = "room_question")
public class roomQuestion {

    @PrimaryKey
    public long roomQuestionId;
    public String text;
    //   List<Rating> ratings; moved to a separated entity
    //   List<Context> contexts moved to a separate entity  ;
    public boolean active;
    public String icon;

}
