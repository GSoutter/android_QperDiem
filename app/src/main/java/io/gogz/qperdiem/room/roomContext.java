package io.gogz.qperdiem.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_context")
public class roomContext {
    @PrimaryKey public long roomContextId;
    public String name;
    public String icon;
    //  List<Question> is moved to separate entity
    public boolean active;

}
