package rleblanc.ca.weighttracker;

import android.graphics.Color;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;

/**
 * Created by Robert LeBlanc - Aug 18, 2015
 *
 */
public class MyLineDataSet extends LineDataSet {
    public MyLineDataSet(List<Entry> yVals, String label){
        super(yVals, label);
    }

    public void stylize(){
        this.setAxisDependency(YAxis.AxisDependency.LEFT);
        this.setColor(ColorTemplate.getHoloBlue());
        this.setCircleColor(Color.RED);
        this.setLineWidth(2f);
        this.setCircleSize(4f);
        this.setFillAlpha(65);
        this.setFillColor(ColorTemplate.getHoloBlue());
        this.setHighLightColor(Color.GREEN);
        this.setDrawHorizontalHighlightIndicator(true);
        this.setDrawVerticalHighlightIndicator(true);
        this.setHighlightEnabled(true);
        this.setValueTextColor(Color.BLACK);
        this.setValueTextSize(9f);
        this.setHighlightEnabled(true);
        this.setDrawValues(true);
    }
}
