package io.gogz.qperdiem.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.gogz.qperdiem.models.Question;
import io.gogz.qperdiem.models.Rating;

public class roomQuestionWithRatings {
    @Embedded public Question question;

    @Relation(
            parentColumn = "roomQuestionId",
            entityColumn = "roomQuestionRatingId"
    )
    public List<Rating> ratings;

}
