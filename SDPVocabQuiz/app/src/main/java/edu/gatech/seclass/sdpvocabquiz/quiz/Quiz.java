package edu.gatech.seclass.sdpvocabquiz.quiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "quizzes")
public class Quiz {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "quiz_name")
    private String quizName;

    @ColumnInfo(name = "quiz_author")
    private String quizAuthor;

    // This is gonna be a json string encoded with all the questions
    @ColumnInfo(name = "quiz_content")
    private String quizContent;

    public Quiz(String quizName, String quizAuthor, String quizContent) {
        this.quizName = quizName;
        this.quizAuthor = quizAuthor;
        this.quizContent = quizContent;
    }

    public String getQuizName() {
        return quizName;
    }

    public String getQuizContent() {
        return quizContent;
    }

    public String getQuizAuthor() {
        return quizAuthor;
    }

    public void setQuizAuthor(String quizAuthor) {
        this.quizAuthor = quizAuthor;}

    public void setQuizName(@NonNull String quizName) {
        this.quizName = quizName;
    }

    public void setQuizContent(String quizContent) {
        this.quizContent = quizContent;
    }
}
