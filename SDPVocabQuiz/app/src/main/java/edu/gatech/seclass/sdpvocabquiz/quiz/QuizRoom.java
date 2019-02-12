package edu.gatech.seclass.sdpvocabquiz.quiz;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Quiz.class}, version = 1, exportSchema = false)
public abstract class QuizRoom extends RoomDatabase {

    public abstract QuizDao quizDao();
    public abstract QuizDaoStat quizDaoStat();

    private static volatile QuizRoom ourInstance;

    public static QuizRoom getInstance(final Context context) {
        if (ourInstance == null) {
            synchronized (QuizRoom.class) {
                if (ourInstance == null) {
                    ourInstance = Room.databaseBuilder(context.getApplicationContext(),
                            QuizRoom.class, "Quiz").allowMainThreadQueries().build();
                }
            }
        }
        return ourInstance;
    }
}