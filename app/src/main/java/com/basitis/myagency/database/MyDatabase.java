package com.basitis.myagency.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.basitis.myagency.models.Company;
import com.basitis.myagency.models.Job;
import com.basitis.myagency.models.Platform;
import com.basitis.myagency.models.Registration;
import com.basitis.myagency.models.Student;

/**
 * Created by Bhavin on 11-11-2017.
 */


@Database(entities = {Registration.class, Student.class, Company.class, Job.class, Platform.class}, version = 5)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase INSTANCE;

    public abstract RegistrationDao registrationModel();

    public abstract StudentDao studentModel();

    public abstract CompanyDao companyModel();

    public abstract JobDao jobModel();

    public abstract PlatformDao platformModel();

    public static MyDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class,"pa")
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
