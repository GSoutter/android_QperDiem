package io.gogz.qperdiem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import io.gogz.qperdiem.viewmodels.QuestionViewModel;

public class EditQuestionActivity extends AppCompatActivity {

    private static final String TAG = "EditQuestion Activity";
    private EditText mEditQuestionView;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);


//        mQuestionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
//
//
//        Log.d(TAG, "onCreate: ");
//
////        mEditQuestionView = findViewById(R.id.edit_question);
////
////        final Button button = findViewById(R.id.button_save);
////        button.setOnClickListener(new View.OnClickListener(){
////            public void onClick(View view) {
////                Intent replyIntent = new Intent();
////                if (TextUtils.isEmpty(mEditQuestionView.getText())) {
////                    setResult(RESULT_CANCELED, replyIntent);
////
////                } else {
////                    String questionText = mEditQuestionView.getText().toString();
////
////                    replyIntent.putExtra(EXTRA_REPLY, question);
////                    setResult(RESULT_OK, replyIntent);
////                }
////                finish();
////            }
////        });
    }
}
