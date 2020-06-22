package io.gogz.qperdiem;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.room_db.ContextQDao;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionDao;
import io.gogz.qperdiem.room_db.QuestionsRoomDatabase;
import io.gogz.qperdiem.room_db.RatingDao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ContextDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private QuestionDao mQuestionDao;
    private QuestionsRoomDatabase mDb;
    private RatingDao mRatingDao;
    private ContextQDao mContextQDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, QuestionsRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mQuestionDao = mDb.questionDao();
        mRatingDao = mDb.ratingDao();
        mContextQDao = mDb.contextQDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGet() throws Exception {
        ContextQ context = new ContextQ();
        context.name = "social";
        mContextQDao.insertOne(context);
        List<ContextQ> allWords = LiveDataTestUtil.getValue(mContextQDao.getAll());
        assertEquals(allWords.get(0).name, context.name);
    }

    @Test
    public void getAll() throws Exception {

        ContextQ context = new ContextQ();
        context.name = "social";
        mContextQDao.insertOne(context);

        ContextQ context2 = new ContextQ();
        context2.name = "work";
        mContextQDao.insertOne(context2);

        List<ContextQ> allWords = LiveDataTestUtil.getValue(mContextQDao.getAll());
        assertEquals(allWords.get(0).name, context.name);
        assertEquals(allWords.get(1).name, context2.name);
    }

    @Test
    public void deleteAll() throws Exception {

        ContextQ context = new ContextQ();
        context.name = "social";
        mContextQDao.insertOne(context);

        ContextQ context2 = new ContextQ();
        context2.name = "work";
        mContextQDao.insertOne(context2);

        mContextQDao.deleteAll();
        List<ContextQ> allWords = LiveDataTestUtil.getValue(mContextQDao.getAll());
        assertTrue(allWords.isEmpty());
    }
}
