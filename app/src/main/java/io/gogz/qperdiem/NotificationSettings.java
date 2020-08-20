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
    private ContextQ mContextQSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences(
                                                    "QperDiemPrefs", MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor sharedPrefsEditor = sharedPrefs.edit();

        boolean contextOn = sharedPrefs.getBoolean("contextNotificationOn", false);
        long contextId = sharedPrefs.getLong("contextIdNotification", 0);

        mSpinner = findViewById(R.id.context_spinner);
        List<ContextQ> contextQs = new ArrayList<>();

        ArrayAdapter<ContextQ> adapter = new ArrayAdapter<ContextQ>(this,
                android.R.layout.simple_spinner_item, contextQs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mContextQViewModel = new ViewModelProvider(this).get(ContextQViewModel.class);

        mContextQViewModel.getAllContexts().observe(this, new Observer<List<ContextQ>>() {
            @Override
            public void onChanged(@Nullable final List<ContextQ> contextQAll) {
                contextQs.clear();
                assert contextQAll != null;
                contextQs.addAll(contextQAll);
                adapter.notifyDataSetChanged();

                mSpinner.setSelection(findIndexOfContextQInList(contextId, contextQs));
            }
        });


        final ToggleButton toggleButtonNotificationOnOff = findViewById(R.id.toggleButtonNotificationOnOff);
        toggleButtonNotificationOnOff.setChecked(contextOn);

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                mContextQSelected = (ContextQ) mSpinner.getSelectedItem();
                sharedPrefsEditor.putBoolean("contextNotificationOn", toggleButtonNotificationOnOff.isChecked());
                sharedPrefsEditor.putLong("contextIdNotification", mContextQSelected.contextId);
                sharedPrefsEditor.apply();

                Toast.makeText(
                        NotificationSettings.this,
                        R.string.settings_saved + " " + mContextQSelected.name,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public int findIndexOfContextQInList(long contextQId, List<ContextQ> contextQs ){
        for (int i = 0; i < contextQs.size(); i++) {
            if (contextQId == contextQs.get(i).contextId){
                return i;
            }
        }
        return 0; // default position if contextQ has been deleted from db.
    };

}