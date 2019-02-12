package edu.gatech.seclass.sdpvocabquiz.score;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Score.class}, version = 1, exportSchema = false)
@TypeConverters({TimestampConverters.class})
public abstract class ScoreRoom extends RoomDatabase {

    public abstract ScoreDao scoreDao();
    public abstract ScoreDaoStat scoreDaoStat();

    private static volatile ScoreRoom ourInstance;

    public static ScoreRoom getInstance(final Context context) {
        if (ourInstance == null) {
            synchronized (ScoreRoom.class) {
                if (ourInstance == null) {
                    ourInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ScoreRoom.class, "Score").allowMainThreadQueries().build();
                }
            }
        }
        return ourInstance;
    }
}