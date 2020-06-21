package io.gogz.qperdiem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionWithRatingListAdapter;
import io.gogz.qperdiem.room_db.QuestionViewModel;
import io.gogz.qperdiem.room_db.QuestionWithRatings;
import io.gogz.qperdiem.room_db.QuestionWithRatingsViewModel;

public class MainActivity extends AppCompatActivity {

    private QuestionViewModel mQuestionViewModel;
    public static final int NEW_QUESTION_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final QuestionWithRatingListAdapter adapter = new QuestionWithRatingListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mQuestionViewModel = new ViewModelProvider(this).get(QuestionWithRatingsViewModel.class);

        mQuestionViewModel.getAllQuestions().observe(this, new Observer<List<QuestionWithRatings>>() {
            @Override
            public void onChanged(@Nullable final List<QuestionWithRatings> questions) {
                // Update the cached copy of the questions in the adapter.
                adapter.setQuestions(questions);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewQuestionActivity.class);
                startActivityForResult(intent, NEW_QUESTION_ACTIVITY_REQUEST_CODE);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_QUESTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Question question = new Question();
            question.text = data.getStringExtra(NewQuestionActivity.EXTRA_REPLY);
            mQuestionsViewModel.insert(question);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}