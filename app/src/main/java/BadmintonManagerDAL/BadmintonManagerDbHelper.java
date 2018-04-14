package BadmintonManagerDAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import BadmintonManagerDAL.BadmintonManagerContract.UserProfile;

public class BadmintonManagerDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + BadmintonManagerContract.UserProfile.TABLE_NAME + " (" +
                BadmintonManagerContract.UserProfile._ID + " INTEGER PRIMARY KEY," +
                UserProfile.COLUMN_NAME_PLAYER_NAME + " TEXT," +
                UserProfile.COLUMN_NAME_PLAYER_LEVEL + " INTEGER," +
                UserProfile.COLUMN_NAME_PLAYER_GENDER + " TEXT)";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BadmintonManager.db";

    public BadmintonManagerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BadmintonManagerContract.UserProfile.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void saveNewProfile(Context context, String playerName, int playerLevel, String playerGender){
        BadmintonManagerDbHelper mDbHelper = new BadmintonManagerDbHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserProfile.COLUMN_NAME_PLAYER_NAME, playerName);
        values.put(UserProfile.COLUMN_NAME_PLAYER_LEVEL, playerLevel);
        values.put(UserProfile.COLUMN_NAME_PLAYER_GENDER, playerGender);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserProfile.TABLE_NAME, null, values);
    }
}
