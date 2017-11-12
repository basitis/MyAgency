/*
 * This is the source code of Telegram for Android v. 3.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2016.
 */

package com.basitis.myagency.globals;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.facebook.stetho.Stetho;

import java.io.IOException;

public class ApplicationLoader extends Application {

    private static final String TAG = "ApplicationLoader";
    public static volatile Context applicationContext;
    public static volatile Handler applicationHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        applicationHandler = new Handler(applicationContext.getMainLooper());
        Stetho.initializeWithDefaults(this);
    }
}
