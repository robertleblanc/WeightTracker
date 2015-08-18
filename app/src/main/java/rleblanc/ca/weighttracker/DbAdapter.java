package rleblanc.ca.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Robert LeBlanc - Aug 18, 2015
 */
public class DbAdapter {

    private DbHelper helper;

    public DbAdapter(Context context) {
        helper = new DbHelper(context);
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
