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
                QuestionDao dao = INSTANCE.questionDao();
                dao.deleteAll();
                RatingDao rDao = INSTANCE.ratingDao();
                rDao.deleteAll();

                Question question = new Question();
                question.text = "God damn I hope this works.";
                dao.insertQuestion(question);

                question = new Question();
                question.text = "I really really really hope it does";
                dao.insertQuestion(question);

                Rating rating = question.addRating(4);
                rDao.insert(rating);
            });
        }
    };

}
