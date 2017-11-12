package com.basitis.myagency.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.basitis.myagency.models.Job;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Bhavin on 11-11-2017.
 */

@Dao
public interface JobDao {
    @Query("select * from "+Job.TABLE_NAME)
    List<Job> loadAllJobs();

    @Query("select * from "+Job.TABLE_NAME+" where "+Job.COLUMN_ID+"= :id")
    Job loadJobById(int id);

    @Insert(onConflict = REPLACE)
    long insertJob(Job user);

    @Delete
    void deleteJob(Job user);

    @Query("delete from "+Job.TABLE_NAME+" where "+Job.COLUMN_EMAIL+" like :badName")
    int deleteJobsByName(String badName);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceJobs(Job... users);

    @Delete
    void deleteJobs(Job user1, Job user2);

    @Query("SELECT * FROM Job WHERE :age == :age") // TODO: Fix this!
    List<Job> findYoungerThan(int age);

    @Query("SELECT * FROM "+Job.TABLE_NAME+" WHERE "+Job.COLUMN_SALARY+"  > :age")
    List<Job> findJobByMinSalary(int age);

    @Query("DELETE FROM "+Job.TABLE_NAME)
    void deleteAll();
}
