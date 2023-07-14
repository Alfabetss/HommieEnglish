package com.example.hommieenglish.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.OnConflictStrategy;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hommieenglish.dao.AnswerDao;
import com.example.hommieenglish.dao.LearningMaterialsDao;
import com.example.hommieenglish.dao.QuestionsDao;
import com.example.hommieenglish.dao.UserDao;
import com.example.hommieenglish.entity.Answer;
import com.example.hommieenglish.entity.DataModel;
import com.example.hommieenglish.entity.LearningMaterials;
import com.example.hommieenglish.entity.QuestionAndAnswers;
import com.example.hommieenglish.entity.Questions;
import com.example.hommieenglish.entity.User;
import com.example.hommieenglish.utils.Helper;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
//                insertLearningMaterialsData(context);
                importData(context);
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
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Let's Protect Our Environment", "description", "unit_1", "1"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "You Look Prettier In Kebaya Clothes", "description", "unit_2", "2"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Internet Is The Best Invention", "description", "unit_3", "3"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "How Much Does It Cost", "description", "unit_4", "4"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "How Are You Feeling?", "description", "unit_5", "5"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "The Heroes Are Very Brave", "description", "unit_6", "6"});
        data.add(new String[]{"https://www.youtube.com/embed/e_04ZrNroTo", "Antarctica Is The Coldest Place On Earth", "description", "unit_7", "7"});

        for (int i=0; i < data.size(); i++) {
            LearningMaterials materials = new LearningMaterials();
            materials.setVideoUrl(data.get(i)[0]);
            materials.setDescription(data.get(i)[2]);
            materials.setTitle(data.get(i)[1]);
            materials.setImage_button_name(data.get(i)[3]);
            materials.setUnit(Integer.valueOf(data.get(i)[4]));
            learningMaterialsDao.insertMaterials(materials);
        }
    }

    public static void importData(Context context) {
        try {
            HommieEnglish hommieEnglish = HommieEnglish.getInstance(context);
            LearningMaterialsDao learningMaterialsDao = hommieEnglish.learningMaterialsDao();
            QuestionsDao questionsDao = hommieEnglish.questionsDao();
            AnswerDao answerDao = hommieEnglish.answerDao();
            UserDao userDao = hommieEnglish.userDao();

            InputStream inputStream = context.getAssets().open("data-model.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            DataModel dataModel = gson.fromJson(json, DataModel.class);
            User user = dataModel.user;
            user.setPassword(Helper.encodePassword(user.getPassword()));
            userDao.insertUser(user);
            for (LearningMaterials materials : dataModel.learningMaterials) {
                learningMaterialsDao.insertMaterials(materials);
            }

            for (QuestionAndAnswers qna : dataModel.questions) {
                Questions q = new Questions();
                q.setUnitId(qna.getUnitId());
                q.setType(qna.getType());
                q.setQuestion(qna.getQuestion());
                q.setId(qna.getId());
                q.setContent(qna.getContent());
                q.setSequence(qna.getSequence());
                q.setParentQuestion(qna.getParentQuestion());
                questionsDao.insert(q);

                for (Answer a : qna.getAnswers()) {
                    a.setQuestionId(q.getId());
                    answerDao.insert(a);
                }
            }
            Log.d("DataImporter", "Data imported successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
