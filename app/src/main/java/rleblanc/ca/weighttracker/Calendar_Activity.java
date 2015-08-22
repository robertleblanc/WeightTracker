package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by littlebox on 8/19/2015.
 */
public class Calendar_Activity extends MyActionBarActivity {

    Cal_Fragment calFragment;
    Button btn_prev_month;
    Button btn_next_month;
    Button btn_prev_year;
    Button btn_next_year;
    TextView tv_month;
    TextView tv_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.cal_activity_layout);

        /* Add the graph fragment to the layout */
        calFragment =  new Cal_Fragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.rl_calendar, calFragment, "calFragment").commit();

        btn_next_month = (Button) findViewById(R.id.btn_next_month);
        btn_prev_month = (Button) findViewById(R.id.btn_prev_month);
        btn_next_year = (Button) findViewById(R.id.btn_next_year);
        btn_prev_year = (Button) findViewById(R.id.btn_prev_year);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_year = (TextView) findViewById(R.id.tv_year);

        tv_month.setText(calFragment.getMonthName());
        tv_year.setText(calFragment.getYear()+"");

        btn_next_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calFragment.nextMonth();
                tv_month.setText(calFragment.getMonthName());
            }
        });

        btn_prev_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calFragment.prevMonth();
                tv_month.setText(calFragment.getMonthName());
            }
        });

        btn_next_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calFragment.nextYear();
                tv_year.setText(calFragment.getYear() + "");
            }
        });

        btn_prev_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calFragment.prevYear();
                tv_year.setText(calFragment.getYear() + "");
            }
        });

    }



}
