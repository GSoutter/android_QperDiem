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

import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.room_db.ContextWithQuestions;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionWithContexts;
import io.gogz.qperdiem.viewmodels.ContextQViewModel;
import io.gogz.qperdiem.viewmodels.QuestionViewModel;

public class EditContextQActivity extends AppCompatActivity {

    private static final String TAG = "EditContext Activity";
    private EditText mEditContextQView;
    private ToggleButton mDeleteToggle;
//    public static final String QUESTION_TEXT = "com.example.android.wordlistsql.REPLY";
//    public static final String QUESTION_ID = "com.example.android.wordlistsql.REPLY";

    private ContextQViewModel mContextQViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_context);

//        final QuestionListAdapter adapter = new QuestionListAdapter(this, this);

//        Intent intent = getIntent();
        long contextId = getIntent().getLongExtra("contextId", 0);

        mContextQViewModel = new ViewModelProvider(this).get(ContextQViewModel.class);

        ContextWithQuestions contextWithQuestions = mContextQViewModel.getOneWithQuestions(contextId).getValue();

        mEditContextQView = findViewById(R.id.edit_context);


        ContextQ contextQ = new ContextQ();
//        mEditQuestionView.setText(questionWithContexts.question.text);

        mContextQViewModel.getOneWithQuestions(contextId).observe(this, new Observer<ContextWithQuestions>() {
            @Override
            public void onChanged(@Nullable final ContextWithQuestions contextWithQuestions) {
                // Update the cached copy of the questions in the adapter.
                if (contextWithQuestions != null){
                    mEditContextQView.setText(contextWithQuestions.context.name);
                }
            }
        });

        Log.d(TAG, "onCreate: ");

        mEditContextQView = findViewById(R.id.edit_context);
        mDeleteToggle = findViewById(R.id.toggleButton);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditContextQView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);

                } else {
                    String contextQName = mEditContextQView.getText().toString();
                    Boolean deleteToggle = mDeleteToggle.isChecked();

                    replyIntent.putExtra("contextQName", contextQName);
                    replyIntent.putExtra("contextId", contextId);
                    replyIntent.putExtra("delete_toggle", deleteToggle);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
