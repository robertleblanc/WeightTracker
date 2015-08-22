package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by littlebox on 8/20/2015.
 */
public class Day_Entry_Activity extends MyActionBarActivity {
    TextView tv_date;
    TextView tv_weight;
    EditText et_weight;
    Button btn_submit;
    Button btn_clear_weight;
    int day;
    int year;
    int month;

    DbAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_entry_layout);

        Intent i = getIntent();

        String month_name = null;


        if (i.getAction() == "android.intent.action.MAIN") {
            GregorianCalendar cal = new GregorianCalendar();
            month = cal.get(Calendar.MONTH);
            month_name = getMonth(month);
            day = cal.get(Calendar.DAY_OF_MONTH);
            year = cal.get(Calendar.YEAR);
        }
        else {
            month_name = i.getStringExtra("month_name");
            day = i.getIntExtra("day", 0);
            month = i.getIntExtra("month", 0);
            year = i.getIntExtra("year", 2015);
        }
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_clear_weight = (Button) findViewById(R.id.btn_clear_weight);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_weight = (TextView) findViewById(R.id.tv_weight);
        et_weight = (EditText) findViewById(R.id.et_weight);

        tv_date.setText(month_name + " " + day + ", " + year);

        db = new DbAdapter(this);
        float weight = db.getDayData(day,month,year);

        tv_weight.setText(weight+" lbs");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertWeight(day, month, year, Float.parseFloat(et_weight.getText().toString()));
                et_weight.getText().clear();
                tv_weight.setText(db.getDayData(day, month, year) + " pounds");
            }
        });

        btn_clear_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteDayData(day, month, year);
                tv_weight.setText(db.getDayData(day, month, year) + " pounds");
            }
        });

        Toast.makeText(this, month_name + " " + day + " was clicked", Toast.LENGTH_SHORT).show();


    }

    public String getMonth(int _month) {
        return new DateFormatSymbols().getMonths()[_month];
    }

}
