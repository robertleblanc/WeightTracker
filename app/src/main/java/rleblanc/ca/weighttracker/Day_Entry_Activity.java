package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by littlebox on 8/20/2015.
 */
public class Day_Entry_Activity extends Activity {
    TextView tv_date;
    TextView tv_weight;
    EditText et_weight;
    Button btn_submit;
    int day;

    DbAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_entry_layout);

        Intent i = getIntent();
        String month_name = i.getStringExtra("month_name");
        day = i.getIntExtra("day", 0);
        final int month = i.getIntExtra("month",0);

        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_weight = (TextView) findViewById(R.id.tv_weight);
        et_weight = (EditText)findViewById(R.id.et_weight);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        tv_date.setText(month_name + " " + day);

        db = new DbAdapter(this);
        float weight = db.getDayData(day,month,2015);

        tv_weight.setText(weight+" lbs");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertWeight(day, month,2015, Float.parseFloat(et_weight.getText().toString()));
                et_weight.getText().clear();
            }
        });

        Toast.makeText(this, month_name + " " + day + " was clicked", Toast.LENGTH_SHORT).show();


    }
}
