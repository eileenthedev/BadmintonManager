package com.example.eileen.badmintonmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import BadmintonManagerDAL.BadmintonManagerContract.UserProfile;
import BadmintonManagerDAL.BadmintonManagerDbHelper;


public class ListPlayerActivity extends AppCompatActivity {

    BadmintonManagerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_player);

        getPlayerList();
    }

    private void getPlayerList() {
        mDbHelper = new BadmintonManagerDbHelper(this.getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.COLUMN_NAME_PLAYER_NAME,
                UserProfile.COLUMN_NAME_PLAYER_LEVEL,
                UserProfile.COLUMN_NAME_PLAYER_GENDER
        };

        // Filter results WHERE "title" = 'My Title'
        //String selection = UserProfile.COLUMN_NAME_PLAYER_LEVEL + " = ?";
        //String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.COLUMN_NAME_PLAYER_NAME + " ASC";

        Cursor cursor = db.query(
                UserProfile.TABLE_NAME,   // The table to query
                 projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(UserProfile._ID));
            itemIds.add(itemId);
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }



}
