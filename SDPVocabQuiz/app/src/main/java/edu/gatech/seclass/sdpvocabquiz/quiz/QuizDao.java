package edu.gatech.seclass.sdpvocabquiz.quiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface QuizDao {
    @Insert
    void insertQuiz(Quiz quiz);

    @Query("SELECT * FROM quizzes WHERE quiz_name = :quizName")
    Quiz fetchQuiz(String quizName);

    @Query("SELECT quiz_name FROM quizzes")
    String[] fetchQuizNames();

    @Query("DELETE FROM quizzes WHERE quiz_name = :quizName")
    void deleteQuiz(String quizName);

    @Query("UPDATE quizzes SET quiz_content = :content WHERE quiz_name = :name")
    void updateQuiz(String name, String content);
}
