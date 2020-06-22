package io.gogz.qperdiem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.gogz.qperdiem.adapters.QuestionListAdapter;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.viewmodels.QuestionViewModel;

public class FragmentQuestions extends Fragment {


    private QuestionViewModel mQuestionViewModel;
    public static final int NEW_QUESTION_ACTIVITY_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questions, container, false);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final QuestionListAdapter adapter = new QuestionListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        mQuestionViewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable final List<Question> questions) {
                // Update the cached copy of the questions in the adapter.
                adapter.setQuestions(questions);
            }
        });

        // Floating Action Button coding
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
            mQuestionViewModel.insert(question);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
