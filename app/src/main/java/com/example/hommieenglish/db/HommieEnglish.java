package com.example.hommieenglish.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hommieenglish.dao.LearningMaterialsDao;
import com.example.hommieenglish.dao.UserDao;
import com.example.hommieenglish.entity.LearningMaterials;
import com.example.hommieenglish.entity.User;

@Database(entities = {User.class, LearningMaterials.class}, version = 3)
public abstract class HommieEnglish extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract LearningMaterialsDao learningMaterialsDao();
    private static volatile HommieEnglish INSTANCE;
    public static HommieEnglish getInstance(Context context) {
//        if (INSTANCE == null) {
            synchronized (HommieEnglish.class) {
//                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    HommieEnglish.class, "hommie_english")
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomCallback(context))
                            .build();
//                }
            }
//        }
        return INSTANCE;
    }
}
