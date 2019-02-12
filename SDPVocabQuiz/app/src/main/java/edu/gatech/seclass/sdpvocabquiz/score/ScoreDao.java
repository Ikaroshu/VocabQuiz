package edu.gatech.seclass.sdpvocabquiz.score;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface ScoreDao {
    @Insert
    void insertScore(Score score);

    @Query("SELECT * FROM Score WHERE user_name = :userName AND quiz_name = :quizName ORDER BY quiz_time LIMIT 3")
    Score[] fetchScore(String userName, String quizName);

    @Query("DELETE FROM Score WHERE quiz_name = :quizName")
    void deleteScore(String quizName);
}
