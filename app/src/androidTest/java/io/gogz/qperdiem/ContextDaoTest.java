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
import io.gogz.qperdiem.room_db.ContextWithQuestions;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionContextCrossRef;
import io.gogz.qperdiem.room_db.QuestionContextCrossRefDao;
import io.gogz.qperdiem.room_db.QuestionDao;
import io.gogz.qperdiem.room_db.QuestionWithContexts;
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
    private QuestionContextCrossRefDao mQuestionContextCrossRefDao;


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
        mQuestionContextCrossRefDao = mDb.questionContextCrossRefDao();

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



    @Test
    public void canGetContextWithQuestions() throws Exception {
        Question question = new Question();
        question.text = "words";
        question.questionId = mQuestionDao.insertQuestion(question);

        Question question2 = new Question();
        question2.text = "words2";
        question2.questionId = mQuestionDao.insertQuestion(question2);

        ContextQ context = new ContextQ();
        context.name = "social";
        context.contextId = mContextQDao.insertOne(context);

        ContextQ context2 = new ContextQ();
        context2.name = "work";
        context2.contextId = mContextQDao.insertOne(context2);


        QuestionContextCrossRef questionContextCrossRef = new QuestionContextCrossRef(question.questionId, context.contextId);
        mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);

        questionContextCrossRef = new QuestionContextCrossRef(question2.questionId, context.contextId);
        mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);

        List<ContextWithQuestions> response = LiveDataTestUtil.getValue(mContextQDao.getContextsWithQuestions());

//        List<QuestionWithRatings> allQuestionsWithRatings = LiveDataTestUtil.getValue(mQuestionDao.getQuestionsWithRatings());
        assertEquals(response.get(0).context.name, context.name);
        assertEquals(response.size(), 2);
        assertEquals(response.get(0).questions.size(), 2);
        assertEquals(response.get(0).questions.get(0).text, question.text);

    }
}
