package rleblanc.ca.weighttracker;

import android.provider.BaseColumns;

/**
 * Created by Robert LeBlanc - Aug 18, 2015
 */
public final class DbContract {

    public static final String DATABASE_NAME = "weights.db";
    public static final int DATABASE_VERSION = 1;



    public static final class Weights implements BaseColumns {
        /* Do not allow this class to be instantiated */
        private Weights() {}

        public static final String TABLE_NAME = "Weights";
        public static final String COL_DAY = "Day";
        public static final String COL_MONTH = "Month";
        public static final String COL_YEAR = "Year";
        public static final String COL_WEIGHT = "Weight";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " ( "
                + _ID + " INTEGER PRIMARY KEY, "
                + COL_DAY + " INTEGER, "
                + COL_MONTH + " TEXT, "
                + COL_YEAR + " TEXT, "
                + COL_WEIGHT + " TEXT "
                + " );";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SELECT_BY_MONTH = "WHERE MONTH = ";

    }
}
