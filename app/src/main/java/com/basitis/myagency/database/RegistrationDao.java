package com.basitis.myagency.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.basitis.myagency.models.Registration;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Bhavin on 11-11-2017.
 */

@Dao
public interface RegistrationDao {

    @Insert(onConflict = IGNORE)
    long insertUser(Registration registration);

    @Update(onConflict = REPLACE)
    void updateUser(Registration book);

    @Query("select * from "+Registration.TABLE_NAME+" where "+Registration.COLUMN_EMAIL+" = :userName and "+Registration.COLUMN_PASSWORD+" = :password LIMIT 1")
    Registration loginUser(String userName, String password);
}
