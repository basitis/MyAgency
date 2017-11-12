package com.basitis.myagency.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.BaseColumns;

/**
 * Created by Bhavin on 11-11-2017.
 */

@Entity(tableName = Registration.TABLE_NAME)
public class Registration {

    public static final String TABLE_NAME = "Registration";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_TYPE = "user_type";

    /** The unique ID of the cheese. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_EMAIL)
    public String email;

    @ColumnInfo(name = COLUMN_PASSWORD)
    public String password;

    @ColumnInfo(name = COLUMN_TYPE)
    public String type;


}
