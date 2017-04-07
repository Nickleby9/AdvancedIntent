package hilay.edu.advancedintent;

import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null){
            int hours = intent.getIntExtra(AlarmClock.EXTRA_HOUR, 0);
            int minutes = intent.getIntExtra(AlarmClock.EXTRA_MINUTES, 0);
            timePicker.setCurrentHour(hours);
            timePicker.setCurrentMinute(minutes);
        }

    }

    public void website(View view) {
        Uri web = Uri.parse("https://developer.android.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, web);
        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        } else {
            Toast.makeText(this, "No browser found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void time(View view) {
        int hours, minutes;
        hours = timePicker.getCurrentHour();
        minutes = timePicker.getCurrentMinute();

        Intent timeIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        timeIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Wake up!");
        timeIntent.putExtra(AlarmClock.EXTRA_HOUR, hours);
        timeIntent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        timeIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (timeIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(timeIntent);
            Toast.makeText(this,
                    "Alarm is set for " + hours + ":" + minutes,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No alarm found!", Toast.LENGTH_SHORT).show();
        }
    }
}
