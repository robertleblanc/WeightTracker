package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by littlebox on 8/21/2015.
 */
public class MyActionBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weight__tracker__gui, menu);
         menu.findItem(R.id.actions_clear_month).setVisible(false);
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
            Weight_Graph_Fragment graph_fragment = (Weight_Graph_Fragment)getFragmentManager().findFragmentByTag("graph_fragment");
            graph_fragment.clearMonth();
        }

        if(id == R.id.actions_cal_activity){
            Intent i = new Intent(this, Calendar_Activity.class);
            startActivity(i);
            this.finish();
        }

        if(id == R.id.actions_graph_activity){
            Intent i = new Intent(this, Weight_Tracker_GUI_Activity.class);
            startActivity(i);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
