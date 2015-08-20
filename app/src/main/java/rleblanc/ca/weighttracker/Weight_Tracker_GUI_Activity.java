package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Weight_Tracker_GUI_Activity extends Activity {

    public static final String TAG = Weight_Tracker_GUI_Activity.class.getSimpleName();
    private Weight_Graph_Fragment chartFragment;
    private Button btn_submit;
    private EditText et_weight;
    private Spinner sp_months;
    private Spinner sp_years;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_tracker_gui_layout);
        Log.i(TAG, "OnCreate called");

        /* Add the graph fragment to the layout */
        chartFragment =  new Weight_Graph_Fragment();
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.gui_layout, chartFragment, "graph_fragment").commit();

        btn_submit =        (Button) findViewById(R.id.btn_submit);
        et_weight =         (EditText) findViewById(R.id.et_weight);
        sp_months =         (Spinner) findViewById(R.id.sp_months);
        sp_years =          (Spinner) findViewById(R.id.sp_years);

        /* Setup the spiners */
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                                                R.array.Months,
                                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_months.setAdapter(adapter);
        sp_months.setSelection(new GregorianCalendar().get(Calendar.MONTH));
        sp_months.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int check = 0;
                Log.i(TAG, "Trying month: " + position);
                chartFragment.displayMonth(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {   }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(et_weight.getText().length() != 0) {
                    chartFragment.submit(Float.parseFloat(et_weight.getText().toString()));
                    et_weight.getText().clear();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weight__tracker__gui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.actions_clear_month){
            chartFragment.clearMonth();
        }

        if(id == R.id.actions_cal_activity){
            Intent i = new Intent(this, Calendar_Activity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy called");

    }
}