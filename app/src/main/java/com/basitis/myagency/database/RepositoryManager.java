package com.basitis.myagency.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.basitis.myagency.globals.AndroidUtilities;
import com.basitis.myagency.globals.ApplicationLoader;
import com.basitis.myagency.models.Company;
import com.basitis.myagency.models.Job;
import com.basitis.myagency.models.Platform;
import com.basitis.myagency.models.Registration;
import com.basitis.myagency.models.Student;

import java.util.List;

/**
 * Created by Bhavin on 11-11-2017.
 */

public class RepositoryManager {
    private MyDatabase myDatabase;

    public static RepositoryManager getInstance() {
        if (instance == null) {
            instance = new RepositoryManager(ApplicationLoader.applicationContext);
        }
        return instance;
    }

    public static RepositoryManager instance;

    public RepositoryManager(Context context) {
        myDatabase = MyDatabase.getInMemoryDatabase(context);
    }

    public Registration signInUser(String user_name, String password) {
        password = AndroidUtilities.encryptBeforeSave(password);
        return myDatabase.registrationModel().loginUser(user_name, password);
    }

    public long addUserInTable(Registration registration){
       return myDatabase.registrationModel().insertUser(registration);
    }

    public long addStudentInTable(Student student) {
        return myDatabase.studentModel().insertStudent(student);
    }

    public long addCompanyName(Company company) {
        return myDatabase.companyModel().insertCompany(company);
    }

    public LiveData<List<Platform>> getAllPlatform() {
        return myDatabase.platformModel().loadAllPlatforms();
    }


    public long getStudentId(long id) {
        return myDatabase.studentModel().loadStudentId((int) id);
    }

    public long getCompanyId(long id){
        return myDatabase.companyModel().loadCompanyId((int) id);
    }

    public long insertNewJob(Job job) {
        return myDatabase.jobModel().insertJob(job);
    }
}
