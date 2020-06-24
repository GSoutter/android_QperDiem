package io.gogz.qperdiem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.gogz.qperdiem.adapters.ContextQOnlyListAdapter;
import io.gogz.qperdiem.adapters.QuestionListAdapter;
import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionWithContexts;
import io.gogz.qperdiem.room_db.QuestionWithRatings;
import io.gogz.qperdiem.viewmodels.ContextQViewModel;
import io.gogz.qperdiem.viewmodels.QuestionViewModel;

public class EditQuestionActivity extends AppCompatActivity {

    private static final String TAG = "EditQuestion Activity";
    private EditText mEditQuestionView;
    private ToggleButton mDeleteToggle;

    private QuestionViewModel mQuestionViewModel;
    private ContextQViewModel mContextQViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        long questionId = getIntent().getLongExtra("questionId", 0);

        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        mEditQuestionView = findViewById(R.id.edit_question);

        mQuestionViewModel.getOneQuestionWithContexts(questionId).observe(this, new Observer<QuestionWithContexts>() {
            @Override
            public void onChanged(@Nullable final QuestionWithContexts questionWithContexts) {
                if (questionWithContexts != null){
                    mEditQuestionView.setText(questionWithContexts.question.text);
                }
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContextQOnlyListAdapter adapter = new ContextQOnlyListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContextQViewModel = new ViewModelProvider(this).get(ContextQViewModel.class);

        mContextQViewModel.getAllContexts().observe(this, new Observer<List<ContextQ>>() {
            @Override
            public void onChanged(@Nullable final List<ContextQ> contextQAll) {
                // Update the cached copy of the contexts in the adapter.
                    adapter.setContextQs(contextQAll);
            }
        });


        mDeleteToggle = findViewById(R.id.toggleButton);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditQuestionView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);

                } else {
                    String questionText = mEditQuestionView.getText().toString();
                    Boolean deleteToggle = mDeleteToggle.isChecked();

//                    replyIntent.putExtra(EXTRA_REPLY, questionText);
                    replyIntent.putExtra("questionText", questionText);
                    replyIntent.putExtra("questionId", questionId);
                    replyIntent.putExtra("delete_toggle", deleteToggle);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
