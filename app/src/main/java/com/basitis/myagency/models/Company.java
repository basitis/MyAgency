package com.basitis.myagency.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.provider.BaseColumns;

import java.util.Date;

@Entity(tableName = Company.TABLE_NAME, foreignKeys = {
        @ForeignKey(entity = Registration.class,
                parentColumns = Registration.COLUMN_ID,
                childColumns = Company.COLUMN_REG_ID)})
@TypeConverters(DateConverter.class)
public class Company {
    public static final String TABLE_NAME = "Company";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NMAE = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_HR = "department";
    public static final String COLUMN_REG_ID = "reg_id";

    /**
     * The unique ID of the cheese.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_NMAE)
    public String name;

    @ColumnInfo(name = COLUMN_EMAIL)
    public String email;

    @ColumnInfo(name = COLUMN_CONTACT)
    public String contact;

    @ColumnInfo(name = COLUMN_HR)
    public String hrName;

    @ColumnInfo(name = COLUMN_REG_ID)
    public long RegId;
}
