package com.example.watchon.di;

import android.content.Context;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.LocalDataSource;
import com.example.watchon.data.source.local.room.WatchOnDatabase;
import com.example.watchon.data.source.remote.RemoteDataSource;
import com.example.watchon.data.source.remote.api.ApiConfig;
import com.example.watchon.utils.AppExecutors;

public class Injection {
    public static WatchOnRepository provideRepository(Context context){
        WatchOnDatabase watchOnDatabase = WatchOnDatabase.getInstance(context);

        AppExecutors appExecutors = new AppExecutors();
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApi(), appExecutors);
        LocalDataSource localDataSource = LocalDataSource.getInstance(watchOnDatabase.watchOnDao());

        return WatchOnRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
