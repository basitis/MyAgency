package com.basitis.myagency.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.provider.BaseColumns;

@Entity(tableName = Platform.TABLE_NAME)
public class Platform {
    public static final String TABLE_NAME = "Platform";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";

    /** The unique ID of the cheese. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_NAME)
    public String name;

    public Platform(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
