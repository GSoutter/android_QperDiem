package io.gogz.qperdiem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;
import java.util.List;

import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.viewmodels.ContextQViewModel;

public class NotificationSettings extends AppCompatActivity {
    private Spinner mSpinner;
    private ContextQViewModel mContextQViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("QperDiemPrefs", MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor sharedPrefsEditor = sharedPrefs.edit();

        mSpinner = findViewById(R.id.context_spinner);

        List<ContextQ> contextQs = new ArrayList<>();

        boolean contextOn = sharedPrefs.getBoolean("contextNotificationOn", false);

        ArrayAdapter<ContextQ> adapter = new ArrayAdapter<ContextQ>(this, android.R.layout.simple_spinner_item, contextQs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);

        mContextQViewModel = new ViewModelProvider(this).get(ContextQViewModel.class);

        mContextQViewModel.getAllContexts().observe(this, new Observer<List<ContextQ>>() {
            @Override
            public void onChanged(@Nullable final List<ContextQ> contextQAll) {
                contextQs.clear();
                assert contextQAll != null;
                contextQs.addAll(contextQAll);
            }
        });

        mSpinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO: complete method actions
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO: complete method actions
            }
        }));




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