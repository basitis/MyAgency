package com.basitis.myagency.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.provider.BaseColumns;
import android.util.SparseBooleanArray;

@Entity(tableName = Job.TABLE_NAME,foreignKeys = {
        @ForeignKey(entity = Company.class,
                parentColumns = Company.COLUMN_ID,
                childColumns = Job.COLUMN_REG_ID)})
@TypeConverters(DateConverter.class)
public class Job {
    public static final String TABLE_NAME = "Job";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_PROFILE = "job_profile";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_CRITERIA = "criteria";
    public static final String COLUMN_BOND = "bond_period";
        public static final String COLUMN_REG_ID="reg_id";

    /** The unique ID of the cheese. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_PROFILE)
    public String name;

    @ColumnInfo(name = COLUMN_EMAIL)
    public String email;

    @ColumnInfo(name = COLUMN_SALARY)
    public float salary;

    @ColumnInfo(name = COLUMN_CRITERIA)
    public String criteria;

    @ColumnInfo(name=COLUMN_BOND)
    public float bond;

    @ColumnInfo(name=COLUMN_REG_ID)
    public long RegId;
}
