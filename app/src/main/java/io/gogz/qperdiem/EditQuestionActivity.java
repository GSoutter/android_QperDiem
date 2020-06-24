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
import io.gogz.qperdiem.room_db.ContextWithQuestions;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.room_db.QuestionWithContexts;
import io.gogz.qperdiem.room_db.QuestionWithRatings;
import io.gogz.qperdiem.viewmodels.ContextQViewModel;
import io.gogz.qperdiem.viewmodels.QuestionViewModel;

public class EditQuestionActivity extends AppCompatActivity implements ContextQOnlyListAdapter.OnContextToggleListener {

    private static final String TAG = "EditQuestion Activity";
    private EditText mEditQuestionView;
    private ToggleButton mDeleteToggle;

    private QuestionViewModel mQuestionViewModel;
    private ContextQViewModel mContextQViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContextQOnlyListAdapter adapter = new ContextQOnlyListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        long questionId = getIntent().getLongExtra("questionId", 0);

        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        mEditQuestionView = findViewById(R.id.edit_question);


        mQuestionViewModel.getOneQuestionWithContexts(questionId).observe(this, new Observer<QuestionWithContexts>() {
            @Override
            public void onChanged(@Nullable final QuestionWithContexts questionWithContexts) {
                if (questionWithContexts != null){
                    mEditQuestionView.setText(questionWithContexts.question.text);
                    adapter.setQuestionWithContext(questionWithContexts);
                }
            }
        });


        mContextQViewModel = new ViewModelProvider(this).get(ContextQViewModel.class);

        mContextQViewModel.getAllContexts().observe(this, new Observer<List<ContextQ>>() {
            @Override
            public void onChanged(@Nullable final List<ContextQ> contextQAll) {
                // Update the cached copy of the contexts in the adapter.
                    adapter.setContextQs(contextQAll);
            }
        });


        mDeleteToggle = findViewById(R.id.toggleButton);
        RecyclerView mRecycleView = findViewById(R.id.recyclerview);
        ;


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditQuestionView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);

                } else {
                    String contextQOn = "";
                    String contextQOff = "";
                    List<ContextQ> contexts = mContextQViewModel.getAllContexts().getValue();
                    for (int i = 0; i < recyclerView.getChildCount(); i++ ){
                        ToggleButton contextQToggle = mRecycleView.getChildAt(i).findViewById(R.id.toggleButtonRecy);
                        if (contextQToggle.isChecked()){
                            contextQOn +=  contexts.get(i).contextId + " ";
                        } else {
                            contextQOff += contexts.get(i).contextId + " ";
                        }
                    }
                    String questionText = mEditQuestionView.getText().toString();
                    Boolean deleteToggle = mDeleteToggle.isChecked();

                    replyIntent.putExtra("questionText", questionText);
                    replyIntent.putExtra("questionId", questionId);
                    replyIntent.putExtra("delete_toggle", deleteToggle);
                    replyIntent.putExtra("context_on", contextQOn);
                    replyIntent.putExtra("context_off", contextQOff);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    @Override
    public void onContextToggleClick(int position) {
        long contextId = mContextQViewModel.getAllContextWithQuestions().getValue().get(position).context.contextId;

    }
}
