package com.example.hommieenglish.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hommieenglish.dao.UserDao;
import com.example.hommieenglish.entity.User;

@Database(entities = {User.class}, version = 2)
public abstract class HommieEnglish extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile HommieEnglish INSTANCE;
    public static HommieEnglish getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HommieEnglish.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    HommieEnglish.class, "hommie_english")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Tindakan yang akan dilakukan saat database pertama kali dibuat
            // Misalnya, memasukkan data awal atau menginisiasi tabel lainnya
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // Tindakan yang akan dilakukan saat database dibuka
        }
    };
}
