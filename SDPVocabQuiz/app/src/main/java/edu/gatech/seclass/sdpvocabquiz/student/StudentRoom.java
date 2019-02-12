package edu.gatech.seclass.sdpvocabquiz.student;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentRoom extends RoomDatabase {

    public abstract StudentDao studentDao();

    private static volatile StudentRoom ourInstance;

    public static StudentRoom getInstance(final Context context) {
        if (ourInstance == null) {
            synchronized (StudentRoom.class) {
                if (ourInstance == null) {
                    ourInstance = Room.databaseBuilder(context.getApplicationContext(),
                            StudentRoom.class, "Student").allowMainThreadQueries().build();
                }
            }
        }
        return ourInstance;
    }
}

