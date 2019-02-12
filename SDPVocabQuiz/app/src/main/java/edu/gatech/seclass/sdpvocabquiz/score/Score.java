package edu.gatech.seclass.sdpvocabquiz.score;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.sql.Timestamp;

@Entity(tableName = "Score", primaryKeys = {"user_name", "quiz_name", "quiz_time"},
        indices = {@Index("user_name"), @Index(value = {"user_name", "quiz_name"})})
public class Score {

    @ColumnInfo(name = "user_name")
    @NonNull
    private String userName;

    @ColumnInfo(name = "quiz_name")
    @NonNull
    private String quizName;

    @ColumnInfo(name = "quiz_time")
    @NonNull
    @TypeConverters({TimestampConverters.class})
    private Timestamp quizTime;

    private double score;

    public Score(String userName, String quizName, Timestamp quizTime, double score) {
        this.userName = userName;
        this.quizName = quizName;
        this.quizTime = quizTime;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Timestamp getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(Timestamp quizTime) {
        this.quizTime = quizTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}