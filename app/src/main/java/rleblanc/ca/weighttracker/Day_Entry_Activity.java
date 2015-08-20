package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by littlebox on 8/20/2015.
 */
public class Day_Entry_Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_entry_layout);

        Intent i = getIntent();
        String month_name = i.getStringExtra("month_name");
        int day = i.getIntExtra("day", 0);

        Toast.makeText(this, month_name + " " + day + " was clicked", Toast.LENGTH_SHORT).show();
    }
}
