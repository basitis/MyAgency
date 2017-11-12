package com.basitis.myagency.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.basitis.myagency.models.Student;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Bhavin on 11-11-2017.
 */

@Dao
public interface StudentDao {
    @Query("select * from "+Student.TABLE_NAME)
    List<Student> loadAllStudents();

    @Query("select * from "+Student.TABLE_NAME+" where "+Student.COLUMN_ID+"= :id")
    Student loadStudentById(int id);

    @Query("select "+Student.COLUMN_ID+" from "+Student.TABLE_NAME+" where "+Student.COLUMN_REG_ID+"= :id")
    long loadStudentId(int id);

    @Insert(onConflict = REPLACE)
    long insertStudent(Student user);

    @Delete
    void deleteStudent(Student user);

    @Query("delete from "+Student.TABLE_NAME+" where "+Student.COLUMN_FNMAE+" like :badName OR "+Student.COLUMN_LNMAE+" like :badName")
    int deleteStudentsByName(String badName);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceStudents(Student... users);

    @Delete
    void deleteStudents(Student user1, Student user2);

    @Query("SELECT * FROM Student WHERE :age == :age") // TODO: Fix this!
    List<Student> findYoungerThan(int age);

    @Query("SELECT * FROM Student WHERE age < :age")
    List<Student> findYoungerThanSolution(int age);

    @Query("DELETE FROM Student")
    void deleteAll();
}
