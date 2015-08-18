package rleblanc.ca.weighttracker;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class Weight_Graph_Fragment extends Fragment {

    private Chart mChart;
    private int day_scroller = 0;
    public DbAdapter db;
    public static final GregorianCalendar cal = new GregorianCalendar();


    public Weight_Graph_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.weight_tracker_graph_layout, container, false);

        mChart = new Chart(getActivity());
        db = new DbAdapter(getActivity());

        //mChart = (LineChart) layout.findViewById(R.id.chart);

        // programmatically create a LineChart
        RelativeLayout rl = (RelativeLayout) layout.findViewById(R.id.graph_layout);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.MATCH_PARENT);
        rl.addView(mChart, layoutParams);

        mChart.displayMonthView();

        return layout;
    }

    public void submit(float weight) {
        LineData lineData = mChart.getData();

        if (lineData != null) {
            Entry entry = new Entry(weight,day_scroller ++);
            lineData.addEntry(entry, 0);
            mChart.moveViewToX(day_scroller - 8);
        }
        mChart.invalidate();
        mChart.notifyDataSetChanged();

        /* Add data to db */
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        db.insertWeight(day_scroller-1,month,year,weight);
    }
}
