package io.gogz.qperdiem.room_db;

import androidx.room.Entity;

@Entity(primaryKeys = {"questionId", "contextId"}, tableName = "question_context")
public class QuestionContextCrossRef {
    public long questionId;
    public long contextId;

    public QuestionContextCrossRef(long questionId, long contextId){
        this.questionId = questionId;
        this.contextId = contextId;
    }
    public QuestionContextCrossRef(){
    }
}
