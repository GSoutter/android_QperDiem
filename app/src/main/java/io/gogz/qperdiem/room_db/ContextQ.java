package io.gogz.qperdiem.room_db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contexts")
public class ContextQ {
    @PrimaryKey public long id;
    public String name;
    public String icon;
    //  List<Question> is moved to separate entity
    public boolean active;

}
