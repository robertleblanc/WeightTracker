package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_tracker_gui_layout);

        /* Add the graph fragment to the layout */
        chartFragment =  new Weight_Graph_Fragment();
        getFragmentManager()
            .beginTransaction()
            .add(R.id.gui_layout, chartFragment, "graph_fragment").commit();

        btn_submit =        (Button) findViewById(R.id.btn_submit);
        et_weight =         (EditText) findViewById(R.id.et_weight);
        sp_months =         (Spinner) findViewById(R.id.sp_months);
        sp_years =          (Spinner) findViewById(R.id.sp_years);

        /* Setup the spiners */
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                                                R.array.Months,
                                                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        sp_months.setAdapter(adapter);
        sp_months.setSelected(false);
        sp_months.setSelection(new GregorianCalendar().get(Calendar.MONTH));
        sp_months.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int check = 0;



                    Log.i(TAG, "Tying month: " + position);
                    chartFragment.displayMonth(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            chartFragment.submit(Float.parseFloat(et_weight.getText().toString()));
            et_weight.setText("");
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

        return super.onOptionsItemSelected(item);
    }
}