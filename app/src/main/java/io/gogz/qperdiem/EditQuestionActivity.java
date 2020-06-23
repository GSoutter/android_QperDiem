package io.gogz.qperdiem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import io.gogz.qperdiem.adapters.QuestionListAdapter;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionWithContexts;
import io.gogz.qperdiem.room_db.QuestionWithRatings;
import io.gogz.qperdiem.viewmodels.QuestionViewModel;

public class EditQuestionActivity extends AppCompatActivity {

    private static final String TAG = "EditQuestion Activity";
    private EditText mEditQuestionView;
    private ToggleButton mDeleteToggle;
//    public static final String QUESTION_TEXT = "com.example.android.wordlistsql.REPLY";
//    public static final String QUESTION_ID = "com.example.android.wordlistsql.REPLY";

    private QuestionViewModel mQuestionViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

//        final QuestionListAdapter adapter = new QuestionListAdapter(this, this);

        Intent intent = getIntent();
        long questionId = getIntent().getLongExtra("questionId", 0);

        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        QuestionWithContexts questionWithContexts = mQuestionViewModel.getOneQuestionWithContexts(questionId).getValue();

        mEditQuestionView = findViewById(R.id.edit_question);


        Question question = new Question();
//        mEditQuestionView.setText(questionWithContexts.question.text);

        mQuestionViewModel.getOneQuestionWithContexts(questionId).observe(this, new Observer<QuestionWithContexts>() {
            @Override
            public void onChanged(@Nullable final QuestionWithContexts questionWithContexts) {
                // Update the cached copy of the questions in the adapter.
                if (questionWithContexts != null){
                    mEditQuestionView.setText(questionWithContexts.question.text);
                }
            }
        });

        Log.d(TAG, "onCreate: ");

        mEditQuestionView = findViewById(R.id.edit_question);
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
