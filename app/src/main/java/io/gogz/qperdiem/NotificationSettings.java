package io.gogz.qperdiem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class NotificationSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("QperDiemPrefs", MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor sharedPrefsEditor = sharedPrefs.edit();

        boolean contextOn = sharedPrefs.getBoolean("contextNotificationOn", false);


        final ToggleButton toggleButtonNotificationOnOff = findViewById(R.id.toggleButtonNotificationOnOff);

        toggleButtonNotificationOnOff.setChecked(contextOn);


        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                sharedPrefsEditor.putBoolean("contextNotificationOn", toggleButtonNotificationOnOff.isChecked());
                sharedPrefsEditor.apply();


                Toast.makeText(
                        NotificationSettings.this,
                        R.string.settings_saved,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

}