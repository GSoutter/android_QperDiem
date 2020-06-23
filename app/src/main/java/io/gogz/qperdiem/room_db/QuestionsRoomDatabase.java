package io.gogz.qperdiem.room_db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Question.class, Rating.class, ContextQ.class, QuestionContextCrossRef.class}, version =1, exportSchema = false)
public abstract class QuestionsRoomDatabase extends RoomDatabase {

//    public abstract QuestionWithRatingsDao questionWithRatingsDao();
    public abstract QuestionDao questionDao();
    public abstract RatingDao ratingDao();
    public abstract ContextQDao contextQDao();
    public abstract QuestionContextCrossRefDao questionContextCrossRefDao();

    private static volatile QuestionsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static QuestionsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuestionsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionsRoomDatabase.class, "qperdiem_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                QuestionDao mQuestionDao = INSTANCE.questionDao();
                mQuestionDao.deleteAll();
                RatingDao rDao = INSTANCE.ratingDao();
                rDao.deleteAll();

                ContextQDao mContextQDao = INSTANCE.contextQDao();
                mContextQDao.deleteAll();

                QuestionContextCrossRefDao mQuestionContextCrossRefDao = INSTANCE.questionContextCrossRefDao();

                Question question = new Question();
                question.text = "Get 7 hours sleep?";
                mQuestionDao.insertQuestion(question);

                Question question2 = new Question();
                question2.text = "Exercise";
                question2.questionId = mQuestionDao.insertQuestion(question2);

                Question question3 = new Question();
                question3.text = "Start small then build up";
                question3.questionId = mQuestionDao.insertQuestion(question3);

                Question question4 = new Question();
                question4.text = "Limit reddit and news";
                question4.questionId = mQuestionDao.insertQuestion(question4);

                Question question5 = new Question();
                question5.text = "Tackle the boring tasks";
                question5.questionId = mQuestionDao.insertQuestion(question5);




                Rating rating = question.addRating(4);
                rDao.insert(rating);

                Rating rating2 = question2.addRating(2);
                rDao.insert(rating2);

                Rating rating3 = question3.addRating(1);
                rDao.insert(rating3);

                Rating rating4 = question4.addRating(4);
                rDao.insert(rating4);

                Rating rating5 = question5.addRating(2);
                rDao.insert(rating5);


                ContextQ context2 = new ContextQ();
                context2.name = "work";
                context2.contextId = mContextQDao.insertOne(context2);

                ContextQ context3 = new ContextQ();
                context3.name = "holiday";
                context3.contextId = mContextQDao.insertOne(context3);

                ContextQ context4 = new ContextQ();
                context3.name = "evening";
                context3.contextId = mContextQDao.insertOne(context4);

                ContextQ context5 = new ContextQ();
                context3.name = "weekend";
                context3.contextId = mContextQDao.insertOne(context5);

                QuestionContextCrossRef questionContextCrossRef = new QuestionContextCrossRef(question.questionId, context3.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question.questionId, context4.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question.questionId, context5.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);

                questionContextCrossRef = new QuestionContextCrossRef(question2.questionId, context3.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question2.questionId, context4.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question2.questionId, context5.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question3.questionId, context2.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);

                questionContextCrossRef = new QuestionContextCrossRef(question4.questionId, context2.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question4.questionId, context3.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question4.questionId, context4.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question4.questionId, context5.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);

                questionContextCrossRef = new QuestionContextCrossRef(question5.questionId, context2.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question5.questionId, context4.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);
                questionContextCrossRef = new QuestionContextCrossRef(question5.questionId, context5.contextId);
                mQuestionContextCrossRefDao.insertOne(questionContextCrossRef);


            });
        }
    };

}
