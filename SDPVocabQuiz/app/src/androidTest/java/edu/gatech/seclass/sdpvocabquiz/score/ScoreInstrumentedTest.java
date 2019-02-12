package edu.gatech.seclass.sdpvocabquiz.score;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Timestamp;

@RunWith(AndroidJUnit4.class)
public class ScoreInstrumentedTest {
    private ScoreDao scoreDao;
    private ScoreRoom scoreRoom;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        scoreRoom = Room.inMemoryDatabaseBuilder(context, ScoreRoom.class).build();
        scoreDao = scoreRoom.scoreDao();
    }

    @After
    public void closeDb() {
        scoreRoom.close();
    }

    @Test
    public void crdScore() {
        for(int i = 0; i < 3; i++) {
            scoreDao.insertScore(new Score("user" + i, "quiz" + i, new Timestamp(System.currentTimeMillis()), 100 - i));
            scoreDao.insertScore(new Score("user" + i, "quiz" + i, new Timestamp(101 + i), 100 - i));
        }
        Assert.assertEquals(2, scoreDao.fetchScore("user1", "quiz1").length);

        scoreDao.deleteScore("quiz2");
        Assert.assertEquals(0, scoreDao.fetchScore("user2", "quiz2").length);
    }
}
