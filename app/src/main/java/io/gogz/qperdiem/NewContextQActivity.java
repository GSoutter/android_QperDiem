package io.gogz.qperdiem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewContextQActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.questionlistsql.REPLY";

    private EditText mEditContextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_context);
        mEditContextView = findViewById(R.id.edit_context);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditContextView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);

                } else {
                    String question = mEditContextView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, question);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
