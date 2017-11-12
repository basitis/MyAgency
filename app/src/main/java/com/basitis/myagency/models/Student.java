package com.basitis.myagency.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.provider.BaseColumns;

import java.util.Date;

@Entity(tableName = Student.TABLE_NAME,foreignKeys = {
        @ForeignKey(entity = Registration.class,
                parentColumns = Registration.COLUMN_ID,
                childColumns = Student.COLUMN_REG_ID)})
@TypeConverters(DateConverter.class)
public class Student {
    public static final String TABLE_NAME = "Student";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_FNMAE = "first_name";
    public static final String COLUMN_MNMAE = "middle_name";
    public static final String COLUMN_LNMAE = "last_name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_AVG = "aggregate";
    public static final String COLUMN_DEPT = "department";
    public static final String COLUMN_PLATFORM = "platform";
    public static final String COLUMN_REG_ID="reg_id";

    /** The unique ID of the cheese. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_FNMAE)
    public String first_name;

    @ColumnInfo(name = COLUMN_MNMAE)
    public String middle_name;

    @ColumnInfo(name = COLUMN_LNMAE)
    public String last_name;

    @ColumnInfo(name = COLUMN_GENDER)
    public int gender;

    @ColumnInfo(name = COLUMN_EMAIL)
    public String email;

    @ColumnInfo(name = COLUMN_CONTACT)
    public String contact;

    @ColumnInfo(name = COLUMN_ADDRESS)
    public String address;

    @ColumnInfo(name = COLUMN_DOB)
    public Date dob;

    @ColumnInfo(name = COLUMN_AGE)
    public int age;

    @ColumnInfo(name = COLUMN_AVG)
    public float average;

    @ColumnInfo(name = COLUMN_DEPT)
    public String department;

    @ColumnInfo(name = COLUMN_PLATFORM)
    public String platform;

    @ColumnInfo(name=COLUMN_REG_ID)
    public long RegId;
}
