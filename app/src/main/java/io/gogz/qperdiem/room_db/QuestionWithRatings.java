package io.gogz.qperdiem.room_db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class QuestionWithRatings {
    @Embedded public Question question;

    @Relation(
            parentColumn = "questionId",
            entityColumn = "questionId",
            entity = Rating.class
    )
    public List<Rating> ratings;

}
