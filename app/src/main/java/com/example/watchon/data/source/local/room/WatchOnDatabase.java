package com.example.watchon.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;

@Database(
        entities = {MovieEntity.class, TvShowEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class WatchOnDatabase extends RoomDatabase {
    public abstract WatchOnDao watchOnDao();

    public static volatile WatchOnDatabase INSTANCE;

    public static WatchOnDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (WatchOnDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WatchOnDatabase.class, "WatchOn.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
