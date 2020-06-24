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
import io.gogz.qperdiem.room_db.QuestionContextCrossRef;
import io.gogz.qperdiem.room_db.QuestionContextCrossRefDao;
import io.gogz.qperdiem.room_db.QuestionDao;
import io.gogz.qperdiem.room_db.QuestionWithContexts;
import io.gogz.qperdiem.room_db.QuestionWithRatings;
import io.gogz.qperdiem.room_db.QuestionsRoomDatabase;
import io.gogz.qperdiem.room_db.Rating;
import io.gogz.qperdiem.room_db.RatingDao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class QuestionDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private QuestionDao mQuestionDao;
    private QuestionsRoomDatabase mDb;
    private RatingDao mRatingDao;
    private QuestionContextCrossRefDao mQuestionContextCrossRefDao;
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
        mQuestionContextCrossRefDao = mDb.questionContextCrossRefDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetWord() throws Exception {
        Question question = new Question();
        question.text = "words";
        mQuestionDao.insertQuestion(question);
        List<Question> allWords = LiveDataTestUtil.getValue(mQuestionDao.getQuestions());
        assertEquals(allWords.get(0).text, question.text);
    }

    @Test
    public void getAllWords() throws Exception {

        Question question = new Question();
        question.text = "aaa";
        mQuestionDao.insertQuestion(question);

        Question question2 = new Question();
        question2.text = "bbb";
        mQuestionDao.insertQuestion(question2);

        List<Question> allQuestions = LiveDataTestUtil.getValue(mQuestionDao.getQuestions());
        assertEquals(allQuestions.get(0).text, question.text);
        assertEquals(allQuestions.get(1).text, question2.text);
    }

    @Test
    public void deleteAll() throws Exception {

        Question question = new Question();
        question.text = "aaa";
        mQuestionDao.insertQuestion(question);

        Question question2 = new Question();
        question2.text = "bbb";
        mQuestionDao.insertQuestion(question2);

        mQuestionDao.deleteAll();
        List<Question> allWords = LiveDataTestUtil.getValue(mQuestionDao.getQuestions());
        assertTrue(allWords.isEmpty());
    }

    @Test
    public void canGetQuestionWithRatings() throws Exception {
        Question question = new Question();
        question.text = "words";
        question.questionId = mQuestionDao.insertQuestion(question);

        Question question2 = new Question();
        question2.text = "words2";
        question2.questionId = mQuestionDao.insertQuestion(question2);

        Rating rating = question.addRating(4);
        mRatingDao.insert(rating);

        List<QuestionWithRatings> allQuestionsWithRatings = LiveDataTestUtil.getValue(mQuestionDao.getQuestionsWithRatings());

//        List<QuestionWithRatings> allQuestionsWithRatings = LiveDataTestUtil.getValue(mQuestionDao.getQuestionsWithRatings());
        assertEquals(allQuestionsWithRatings.get(0).question.text, question.text);
        assertEquals(allQuestionsWithRatings.get(0).ratings.get(0).score, rating.score, 0.1);

    }

    @Test
    public void canGetQuestionWithContexts() throws Exception {
        Question question = new Question();
        question.text = "words";
        question.questionId = mQuestionDao.insertQuestion(question);

        Question question2 = new Question();
        question2.text = "words2";
        question2.questionId = mQuestionDao.insertQuestion(question2);

        ContextQ context = new ContextQ();
        context.name = "social";
        context.contextId = mContextQDao.insertOne(context);


        QuestionContextCrossRef questionContextCrossRef = new QuestionContextCrossRef(question.questionId, context.contextId);
        mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);

        List<QuestionWithContexts> response = LiveDataTestUtil.getValue(mQuestionDao.getQuestionsWithContexts());

//        List<QuestionWithRatings> allQuestionsWithRatings = LiveDataTestUtil.getValue(mQuestionDao.getQuestionsWithRatings());
        assertEquals(response.get(0).question.text, question.text);
        assertEquals(response.get(1).question.text, question2.text);
        assertEquals(response.get(0).contexts.get(0).name, context.name);

    }

    @Test
    public void canGetOneQuestionWithContexts() throws Exception {
        Question question = new Question();
        question.text = "words";
        question.questionId = mQuestionDao.insertQuestion(question);

        Question question2 = new Question();
        question2.text = "words2";
        question2.questionId = mQuestionDao.insertQuestion(question2);

        ContextQ context = new ContextQ();
        context.name = "social";
        context.contextId = mContextQDao.insertOne(context);


        QuestionContextCrossRef questionContextCrossRef = new QuestionContextCrossRef(question.questionId, context.contextId);
        mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);

        QuestionWithContexts response = LiveDataTestUtil.getValue(mQuestionDao.getOneQuestionWithContexts(question.questionId));

//        List<QuestionWithRatings> allQuestionsWithRatings = LiveDataTestUtil.getValue(mQuestionDao.getQuestionsWithRatings());
        assertEquals(response.question.text, question.text);
        assertEquals(response.contexts.get(0).name, context.name);

    }

}