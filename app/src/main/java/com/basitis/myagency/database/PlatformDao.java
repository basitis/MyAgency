package com.basitis.myagency.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.basitis.myagency.models.Platform;
import com.basitis.myagency.models.Platform;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Bhavin on 11-11-2017.
 */

@Dao
public interface PlatformDao {
    @Query("select * from "+Platform.TABLE_NAME)
    LiveData<List<Platform>> loadAllPlatforms();

    @Insert(onConflict = REPLACE)
    long insertPlatform(Platform user);

    @Delete
    void deletePlatform(Platform user);

}
