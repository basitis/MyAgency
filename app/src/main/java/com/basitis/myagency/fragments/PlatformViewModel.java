package com.basitis.myagency.fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.basitis.myagency.database.RepositoryManager;
import com.basitis.myagency.models.Platform;

import java.util.List;

/**
 * Created by Bhavin on 12-11-2017.
 */

public class PlatformViewModel extends ViewModel {
    private LiveData<List<Platform>> platform;

    public LiveData<List<Platform>> getPlatform() {
        if(platform == null){
            platform = new MutableLiveData<>();
            loadPlatform();
        }
        return platform;
    }

    public void loadPlatform() {
        platform = RepositoryManager.getInstance().getAllPlatform();
    }
}
