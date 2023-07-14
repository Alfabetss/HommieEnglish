package com.example.hommieenglish;

import android.content.Context;

import com.example.hommieenglish.dao.AnswerDao;
import com.example.hommieenglish.dao.QuestionsDao;
import com.example.hommieenglish.db.HommieEnglish;
import com.example.hommieenglish.entity.Answer;
import com.example.hommieenglish.entity.QuestionAndAnswers;
import com.example.hommieenglish.entity.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuestionManager {
    private Executor executor = Executors.newSingleThreadExecutor();
    private QuestionsDao questionsDao;
    private AnswerDao answerDao;

    public QuestionManager(Context context) {
        HommieEnglish db = HommieEnglish.getInstance(context);
        questionsDao = db.questionsDao();
        answerDao = db.answerDao();
    }

    public List<QuestionAndAnswers> getQuestionsAndAnswer(int unit) {
        List<Questions> questions = questionsDao.getByUnit(unit);
        List<QuestionAndAnswers> result = new ArrayList<>();
        for (Questions q : questions) {
            List<Answer> answers = answerDao.getByQuestionId(q.getId());
            QuestionAndAnswers questionWithAnswer = new QuestionAndAnswers();
            questionWithAnswer.setAnswers(answers);
            questionWithAnswer.setParentQuestion(q.getParentQuestion());
            questionWithAnswer.setQuestion(q.getQuestion());
            questionWithAnswer.setId(q.getId());
            questionWithAnswer.setContent(q.getContent());
            questionWithAnswer.setSequence(q.getSequence());
            questionWithAnswer.setType(q.getType());
            questionWithAnswer.setUnitId(q.getUnitId());
            result.add(questionWithAnswer);
        }
        return result;
    }

}
