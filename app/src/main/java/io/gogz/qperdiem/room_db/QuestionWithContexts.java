package io.gogz.qperdiem.room_db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class QuestionWithContexts {
    @Embedded public Question question;
    @Relation(
            parentColumn = "questionId",
            entityColumn = "contextId",
            associateBy = @Junction(QuestionContextCrossRef.class)
    )
    public List<ContextQ> contexts;


    public boolean containsContext(ContextQ contextQ){
        for (ContextQ context : this.contexts){
            if (contextQ.contextId == context.contextId){
                return true;
            }
        }
        return false;
    }
}
