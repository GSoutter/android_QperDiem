package io.gogz.qperdiem.room_db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contexts")
public class ContextQ {
    @PrimaryKey(autoGenerate = true) public long contextId;
    public String name;
    public String icon;
    //  List<Question> is moved to separate entity
    public boolean active;

}
