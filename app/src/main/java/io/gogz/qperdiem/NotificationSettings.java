package io.gogz.qperdiem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class NotificationSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Toast.makeText(
                        NotificationSettings.this,
                        R.string.settings_saved,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

}