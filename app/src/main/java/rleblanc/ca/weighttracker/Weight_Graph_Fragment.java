package rleblanc.ca.weighttracker;

import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

    public static final String TAG = Weight_Graph_Fragment.class.getSimpleName();
    private Chart mChart;
    private int day_scroller = 0;
    private int selected_month;
    private int today;
    public DbAdapter db;
    public GregorianCalendar cal;
    public Weight_Graph_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "OnCreateView Called");

        View layout = inflater.inflate(R.layout.weight_tracker_graph_layout, container, false);

        mChart = new Chart(getActivity());
        db = new DbAdapter(getActivity());
        cal = new GregorianCalendar();
        today = cal.get(Calendar.DAY_OF_MONTH);

        //mChart = (LineChart) layout.findViewById(R.id.chart);

        // programmatically create a LineChart
        RelativeLayout rl = (RelativeLayout) layout.findViewById(R.id.graph_layout);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.MATCH_PARENT);
        rl.addView(mChart, layoutParams);

        Log.i(TAG, "Month init: " + cal.get(Calendar.MONTH));
        //LineDataSet set = new LineDataSet(null, "weights");
        LineData data = new LineData();
        mChart.setData(data);

        mChart.displayMonthView(cal.get(Calendar.MONTH));

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.displayMonth(selected_month);
    }

    public void submit(float weight) {

        LineData lineData = mChart.getData();
        //day_scroller = lineData.getYValCount(); //Used this for testing input ofmultiple days rapidly
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        if (lineData != null) {
            Entry entry = new Entry(weight,today-1);

            lineData.addEntry(entry, 0);

            mChart.moveViewToX(today - 8);
            db.insertWeight(today, month, year, weight);
        }
        displayMonth(selected_month);
        mChart.notifyDataSetChanged();
    }

    /* 0 for jan, 1 for feb, etc */
    public void displayMonth(int month){
        selected_month = month;
        cal.set(Calendar.YEAR , 2015);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);


        mChart.displayMonthView(month);
        mChart.getLineData().removeDataSet(0);
        MyLineDataSet set = db.getWeightsForMonth(month);
        set.stylize();

        mChart.getLineData().addDataSet(set);
        mChart.invalidate();
        mChart.notifyDataSetChanged();

    }

    public void clearMonth(){
        db.clearMonth(selected_month);

        mChart.displayMonthView(selected_month);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy called");
    }
}
