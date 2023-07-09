package com.example.hommieenglish.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hommieenglish.dao.LearningMaterialsDao;
import com.example.hommieenglish.entity.LearningMaterials;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

class RoomCallback extends RoomDatabase.Callback {
    private Context context;
    public RoomCallback(Context ctx) {
        this.context = ctx;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        Log.d("DEBUG", "room callback on create invoked");

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                insertLearningMaterialsData(context);
            }
        });
    }

    private void insertLearningMaterialsData(Context context) {
        Log.d("DEBUG", "insertLearningMaterialsData invoked");
        HommieEnglish hommieEnglish = HommieEnglish.getInstance(context);
        LearningMaterialsDao learningMaterialsDao = hommieEnglish.learningMaterialsDao();

        List<LearningMaterials> learningMaterials = learningMaterialsDao.getAllMaterials();
        if (learningMaterials.size() > 0) {
            Log.d("DEBUG", "Learning materials already inserted");
            return;
        }

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 1", "description", "unit_1"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 2", "description", "unit_2"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 3", "description", "unit_3"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 4", "description", "unit_4"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 5", "description", "unit_5"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 6", "description", "unit_6"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 7", "description", "unit_7"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Learning Chapter 8", "description", "unit_8"});

        for (int i=0; i < data.size(); i++) {
            LearningMaterials materials = new LearningMaterials();
            materials.setVideoUrl(data.get(i)[0]);
            materials.setDescription(data.get(i)[2]);
            materials.setTitle(data.get(i)[1]);
            materials.setImage_button_name(data.get(i)[3]);

            learningMaterialsDao.insertMaterials(materials);
        }
    }
}
