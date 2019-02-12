package edu.gatech.seclass.sdpvocabquiz.quiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class QuizInstrumentedTest {
    private QuizDao quizDao;
    private QuizRoom quizRoom;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        quizRoom = Room.inMemoryDatabaseBuilder(context, QuizRoom.class).build();
        quizDao = quizRoom.quizDao();
    }

    @After
    public void closeDb() {
        quizRoom.close();
    }

    @Test
    public void crdQuiz() {
        for (int i = 0; i < 3; i++) {
            quizDao.insertQuiz(new Quiz("quiz" + i, "quiz" + i));
        }
        Assert.assertEquals("quiz0", quizDao.fetchQuiz("quiz0").getQuizContent());
        quizDao.deleteQuiz("quiz1");
        Assert.assertNull(quizDao.fetchQuiz("quiz1"));
    }
}
