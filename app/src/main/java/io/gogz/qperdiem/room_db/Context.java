package io.gogz.qperdiem.room_db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_context")
public class Context {
    @PrimaryKey public long roomContextId;
    public String name;
    public String icon;
    //  List<Question> is moved to separate entity
    public boolean active;

}
