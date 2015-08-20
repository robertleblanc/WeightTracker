package rleblanc.ca.weighttracker;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Robert LeBlanc - Aug 17, 2015
 */
public class Chart extends LineChart {

    public static final String TAG = Chart.class.getSimpleName();
    public final static float MAX_Y_VISIBLE = 250f;
    public static final GregorianCalendar cal = new GregorianCalendar();


    public Chart(Context _context){
        super(_context);
    }

    //Gets called automatically somewhere in this heriarchy
    @Override
    public void init(){
        super.init();
        this.setDescription("");
        this.setNoDataText("No Data Present to be Graphed");
        this.setHighlightEnabled(true);
        this.setTouchEnabled(true);
        this.setDragEnabled(false);
        this.setScaleEnabled(false);
        this.setDrawGridBackground(true);
        this.setPinchZoom(false);

        LineData data = new LineData();
        data.setValueTextColor(Color.BLACK);

        this.setData(data);

        Legend l = this.getLegend();

        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.BLACK);

        XAxis x1 = this.getXAxis();
        x1.setTextColor(Color.BLACK);
        x1.setPosition(XAxis.XAxisPosition.BOTTOM);
        x1.setDrawGridLines(true);
        //x1.setLabelsToSkip(0);
       // x1.setSpaceBetweenLabels(15);
        x1.setGridColor(Color.BLACK);

        YAxis y1 = this.getAxisLeft();
        y1.setTextColor(Color.BLACK);
        //y1.setAxisMaxValue(MAX_Y_VISIBLE);

        // y1.setDrawGridLines(true);
        y1.setGridColor(Color.BLACK);
        YAxis y12 = this.getAxisRight();
        y12.setEnabled(false);
    }

    public void displayMonthView(int month){
        this.clearAllJobs();

        cal.set(Calendar.MONTH, month);
        String month_name = getMonth(month);
        int days_in_month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String[] xvals = new String[days_in_month];


        for(int i = 1; i <= days_in_month; i++){
            //this.getLineData().addXValue(i+"");
            xvals[i-1] = "Day " + (i);
        }

        LineData data = new LineData(xvals);
        this.setData(data);
        this.getXAxis().setAvoidFirstLastClipping(true);
        this.setVisibleXRangeMaximum(10);
        this.setDescription(month_name);

        this.notifyDataSetChanged();
        this.invalidate();
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }
}
