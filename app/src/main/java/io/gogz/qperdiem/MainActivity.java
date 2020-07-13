package io.gogz.qperdiem;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //initial fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragmentRatings()).commit();


    }


    // Bottom navigation bar navigation bar switching code.
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.navigation_add_ratings:
                            selectedFragment = new FragmentRatings();
                            break;

                            case R.id.navigation_view_questions:
                            selectedFragment = new FragmentQuestions();
                            break;

                            case R.id.navigation_view_contexts:
                            selectedFragment = new FragmentContexts();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };


    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "QperDiemReminderChannel";
            String description = "Channel for QperDiem reminders";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyQperDiem", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notification_test) {
            Log.d(TAG, "onOptionsItemSelected: notification test");
            Toast.makeText(this, "Notification Set!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, NotificationContextQuestions.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();

            long threeSecondsInMillis = 1000 * 3;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick + threeSecondsInMillis,
                    pendingIntent);

            return true;
        }

        if (id == R.id.notification_settings) {
            Intent intent = new Intent(MainActivity.this, NotificationSettings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}