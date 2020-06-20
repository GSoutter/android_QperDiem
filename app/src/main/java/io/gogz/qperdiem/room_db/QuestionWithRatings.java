package io.gogz.qperdiem.room_db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.Rating;

public class QuestionWithRatings {
    @Embedded public Question question;

    @Relation(
            parentColumn = "roomQuestionId",
            entityColumn = "roomQuestionReaId"
    )
    public List<Rating> ratings;

}
