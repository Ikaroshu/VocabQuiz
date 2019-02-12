package edu.gatech.seclass.sdpvocabquiz.score;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ScoreDaoStat {

    @Query("SELECT * FROM Score WHERE user_name LIKE :name ORDER BY quiz_time DESC")
    public List<Score> getScoresByTime(String name);

    @Query("SELECT * FROM score WHERE (user_name LIKE :username) AND (quiz_name LIKE :quizname) ORDER BY quiz_time DESC")
    public Score getFirstScore(String username, String quizname);

    @Query("SELECT * FROM score WHERE (user_name LIKE :username) AND (quiz_name LIKE :quizname) ORDER BY score DESC")
    public Score getHighesttScore(String username, String quizname);

    @Query("SELECT * FROM score WHERE (score == :score) AND (quiz_name LIKE :quizname) ORDER BY user_name")
    public List<Score> getTopScore(String quizname, double score);
}
