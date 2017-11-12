package com.basitis.myagency.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.basitis.myagency.models.Company;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Bhavin on 11-11-2017.
 */

@Dao
public interface CompanyDao {
    @Query("select * from "+Company.TABLE_NAME)
    List<Company> loadAllCompanys();

    @Query("select * from "+Company.TABLE_NAME+" where "+Company.COLUMN_ID+"= :id")
    Company loadCompanyById(int id);

    @Query("select "+Company.COLUMN_ID+" from "+Company.TABLE_NAME+" where "+Company.COLUMN_REG_ID+"= :id")
    long loadCompanyId(int id);

    @Insert(onConflict = REPLACE)
    long insertCompany(Company user);

    @Delete
    void deleteCompany(Company user);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceCompanys(Company... users);

    @Delete
    void deleteCompanys(Company user1, Company user2);

    @Query("DELETE FROM "+Company.TABLE_NAME)
    void deleteAll();
}
