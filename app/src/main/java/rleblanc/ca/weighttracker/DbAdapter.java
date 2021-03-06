package rleblanc.ca.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert LeBlanc - Aug 18, 2015
 */
public class DbAdapter {

    private DbHelper helper;
    private static final String TAG = DbAdapter.class.getSimpleName();

    public DbAdapter(Context context) {
        helper = new DbHelper(context);
    }

    public void deleteDayData(int day, int month, int year){
        SQLiteDatabase db = helper.getWritableDatabase();
        String WHERE = DbContract.Weights.COL_DAY + " =? and "
                + DbContract.Weights.COL_MONTH + " =? and "
                + DbContract.Weights.COL_YEAR + " =?";

        String[] WHEREARGS = {day + "", month +"", year +""};
        db.delete(DbContract.Weights.TABLE_NAME,WHERE,WHEREARGS);
    }

    public long insertWeight(int day,int month, int year, float weight){
        SQLiteDatabase dbb = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.Weights.COL_DAY, day);
        values.put(DbContract.Weights.COL_MONTH, month);
        values.put(DbContract.Weights.COL_YEAR, year);
        values.put(DbContract.Weights.COL_WEIGHT, weight);

        return dbb.insert(DbContract.Weights.TABLE_NAME, null, values);
    }

    public MyLineDataSet getWeightsForMonth(int _month){
        SQLiteDatabase db = helper.getReadableDatabase();
        String cols[] = {
                DbContract.Weights.COL_DAY,
                DbContract.Weights.COL_MONTH,
                DbContract.Weights.COL_YEAR,
                DbContract.Weights.COL_WEIGHT
        };


        Cursor c = db.query(DbContract.Weights.TABLE_NAME, cols, DbContract.Weights.COL_MONTH + " =?", new String[]{_month + ""}, null, null,DbContract.Weights.COL_DAY + " ASC");

        StringBuffer buffer = new StringBuffer();
        LineDataSet dataSet;
        List<Entry> entryList = new ArrayList<>();

        while(c.moveToNext()){
            //int UIDindex = c.getColumnIndex(DbContract.Weights._ID);
            int DAYindex = c.getColumnIndex(DbContract.Weights.COL_DAY);
            int MONTHIndex = c.getColumnIndex(DbContract.Weights.COL_MONTH);
            int YEARindex = c.getColumnIndex(DbContract.Weights.COL_YEAR);
            int WEIGHTindex = c.getColumnIndex(DbContract.Weights.COL_WEIGHT);

            //int UID = c.getInt(UIDindex);
            float weight = c.getFloat(WEIGHTindex);
            int day = c.getInt(DAYindex);
            int month = c.getInt(MONTHIndex);
            int year = c.getInt(YEARindex);

            entryList.add(new Entry(weight,day - 1));
        }

        if(entryList.size() == 0){
            Log.i(TAG, "No Data");
            return new MyLineDataSet(null, "Weights");
        }
        else{
            return new MyLineDataSet(entryList, getMonth(_month));
        }
    }

    public float getDayData(int day, int month, int year){
        SQLiteDatabase db = helper.getReadableDatabase();
        String cols[] = {
                DbContract.Weights.COL_DAY,
                DbContract.Weights.COL_MONTH,
                DbContract.Weights.COL_YEAR,
                DbContract.Weights.COL_WEIGHT
        };


        Cursor c = db.query(DbContract.Weights.TABLE_NAME, cols, "day =? and month=? and year=?", new String[]{day + "", month + "", year+""}, null, null,DbContract.Weights.COL_DAY + " ASC");

        float weight = 0.0f;
        while(c.moveToNext()){
            int WEIGHTindex = c.getColumnIndex(DbContract.Weights.COL_WEIGHT);
            weight = c.getFloat(WEIGHTindex);
        }

        return weight;
    }

    public void clearMonth(int month){
        SQLiteDatabase db = helper.getWritableDatabase();
        String WHERE = DbContract.Weights.COL_MONTH + " = ?";
        String[] WHEREARGS = {month + ""};
        db.delete(DbContract.Weights.TABLE_NAME,WHERE,WHEREARGS);
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    public static class DbHelper extends SQLiteOpenHelper {
        public static final String TAG = DbHelper.class.getSimpleName();

        public DbHelper(Context context) {
            super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DbContract.Weights.CREATE_TABLE);
                Log.i(TAG, "Created " + DbContract.Weights.TABLE_NAME);
            } catch (SQLException e) {
                Log.e(TAG, "Error creating table");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DbContract.Weights.DROP_TABLE);
            this.onCreate(db);
        }
    }


}
