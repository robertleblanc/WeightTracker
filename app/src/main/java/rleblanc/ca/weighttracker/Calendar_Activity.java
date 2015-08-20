package rleblanc.ca.weighttracker;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by littlebox on 8/19/2015.
 */
public class Calendar_Activity extends Activity {

    Cal_Fragment calFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.cal_activity_layout);

        /* Add the graph fragment to the layout */
        calFragment =  new Cal_Fragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.cal_activity_layout, calFragment, "calFragment").commit();
    }


}
