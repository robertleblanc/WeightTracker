package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Weight_Tracker_GUI extends Activity {

    private Weight_Graph_Fragment chartFragment;
    private Button btn_submit;
    private EditText et_weight;

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


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartFragment.submit(Float.parseFloat(et_weight.getText().toString()));
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
