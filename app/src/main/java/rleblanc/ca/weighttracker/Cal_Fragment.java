package rleblanc.ca.weighttracker;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by littlebox on 8/19/2015.
 */
public class Cal_Fragment extends Fragment {

    public GridView gridView;
    public GregorianCalendar calendar;
    public ArrayList<String> days_in_month;
    public int month;
    public int year;
    public MyGridAdapter grid_adapter;

    public Cal_Fragment(){
        super();
        calendar = new GregorianCalendar(); //Sets calendar to today right now
        days_in_month = get_Days_In_Month();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cal_fragment_layout, container, false);

        gridView = (GridView) v.findViewById(R.id.gv_calendar);
        gridView.setBackgroundColor(0xfff0f0f0);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), Day_Entry_Activity.class);
                i.putExtra("month_name", getMonthName());
                i.putExtra("month", getMonth());
                i.putExtra("day", position+1);
                i.putExtra("year", year);
                startActivity(i);
                getActivity().finish();
            }
        });
        //gv.setVerticalSpacing(1);
        //gv.setHorizontalSpacing(1);

        grid_adapter = new MyGridAdapter(getActivity(), days_in_month);
        gridView.setAdapter(grid_adapter);



        return v;
    }

    public ArrayList<String> get_Days_In_Month(){
        ArrayList<String> days = new ArrayList<String>();

        for(int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            days.add(i+"");
        }
        return days;
    }

    public void nextMonth(){
        calendar.set(Calendar.MONTH, month+1 );
        month = calendar.get(Calendar.MONTH);
        days_in_month = get_Days_In_Month();
        gridView.setAdapter(new MyGridAdapter(getActivity(), days_in_month));
    }

    public void prevMonth(){
        calendar.set(Calendar.MONTH, month-1 );
        month = calendar.get(Calendar.MONTH);
        days_in_month = get_Days_In_Month();
        gridView.setAdapter(new MyGridAdapter(getActivity(), days_in_month));
    }

    public void nextYear(){
        calendar.set(Calendar.YEAR, year+1 );
        year = calendar.get(Calendar.YEAR);
        days_in_month = get_Days_In_Month();
        gridView.setAdapter(new MyGridAdapter(getActivity(), days_in_month));

    }

    public void prevYear(){
        calendar.set(Calendar.YEAR, year-1 );
        year = calendar.get(Calendar.YEAR);
        days_in_month = get_Days_In_Month();
        gridView.setAdapter(new MyGridAdapter(getActivity(), days_in_month));
    }


    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public String getMonthName(){
        return getMonth(getMonth());
    }

    public String getMonth(int _month) {
        return new DateFormatSymbols().getMonths()[_month];
    }

}

class MyGridAdapter extends BaseAdapter{
    ArrayList<String> days_in_month;
    Context context;

    public MyGridAdapter(Context _context, ArrayList<String> _days_in_month){
        days_in_month = _days_in_month;
        context = _context;
    }
    @Override
    public int getCount() {
        return days_in_month.size();
    }

    @Override
    public Object getItem(int position) {
        return days_in_month.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView tv_day;
        ViewHolder(View view){
            tv_day = (TextView) view.findViewById(R.id.tv_day_in_month);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.day_view, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();

        }

        holder.tv_day.setText(days_in_month.get(position));
        return row;
    }

}
