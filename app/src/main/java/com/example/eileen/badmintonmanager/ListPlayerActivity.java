package com.example.eileen.badmintonmanager;

import android.app.ActionBar.LayoutParams;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import BadmintonManagerDAL.BadmintonManagerContract.UserProfile;
import BadmintonManagerDAL.BadmintonManagerDbHelper;

public class ListPlayerActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>
{

    // This is the Adapter being used to display the list's data
    SimpleCursorAdapter mAdapter;

    // These are the Contacts rows that we will retrieve
    static final String[] PROJECTION = new String[] {UserProfile._ID,
                                                     UserProfile.COLUMN_NAME_PLAYER_NAME};

    // This is the select criteria
    static final String SELECTION = "((" +
            UserProfile.COLUMN_NAME_PLAYER_NAME + " NOTNULL) AND (" +
            UserProfile.COLUMN_NAME_PLAYER_NAME + " != '' ))";

    //DB instance
    BadmintonManagerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_player);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {UserProfile.COLUMN_NAME_PLAYER_NAME};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        // Prepare the loader. Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);

    }

    // Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, null, PROJECTION, SELECTION, null, null )
        {
            @Override
            public Cursor loadInBackground()
            {
                mDbHelper = new BadmintonManagerDbHelper(getContext());
                // You can use any query that returns a cursor.
                return mDbHelper.getReadableProfileDb().query(UserProfile.TABLE_NAME, getProjection(), getSelection(), getSelectionArgs(), null, null, getSortOrder(), null );
            }
        };
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in. (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed. We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //TODO: SHOW LEVEL WHEN LIST ITEM IS CLICKED
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
