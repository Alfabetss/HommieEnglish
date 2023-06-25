package com.example.hommieenglish.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hommieenglish.dao.UserDao;
import com.example.hommieenglish.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class HommieEnglish extends RoomDatabase {
    public abstract UserDao userDao();
}
