package io.gogz.qperdiem.room_db;

import androidx.room.Entity;

@Entity(primaryKeys = {"questionId", "contextId"})
public class QuestionContextCrossRef {
    public long questionId;
    public long contextId;
}
