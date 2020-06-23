package io.gogz.qperdiem.room_db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ContextWithQuestions {

        @Embedded
        public ContextQ context;
        @Relation(
                parentColumn = "contextId",
                entityColumn = "questionId",
                associateBy = @Junction(QuestionContextCrossRef.class)
        )
        public List<Question> questions;

}
