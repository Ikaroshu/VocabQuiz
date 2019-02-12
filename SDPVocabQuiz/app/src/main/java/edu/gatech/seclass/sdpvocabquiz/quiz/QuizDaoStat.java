package edu.gatech.seclass.sdpvocabquiz.quiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuizDaoStat {

    @Query("SELECT quiz_name FROM quizzes")
    public List<String> getAllQuiz();
}
