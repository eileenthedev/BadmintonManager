package com.example.eileen.badmintonmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import BadmintonManagerDAL.BadmintonManagerDbHelper;

public class GetPlayersActivity extends AppCompatActivity {

    TextView txtName;
    SeekBar sbLevel;
    Switch swGender;
    TextView txtSbProgress;
    Button btnAddPlayer;
    NavigationView navigationView;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_players);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        sbLevel = (SeekBar) findViewById(R.id.sbLevel);
        txtSbProgress = (TextView) findViewById(R.id.txtSbProgress);
        txtName = (TextView) findViewById(R.id.txtName);
        swGender = (Switch) findViewById(R.id.swGender);
        btnAddPlayer = (Button) findViewById(R.id.btnAddPlayer);
        navigationView = findViewById(R.id.nav_view);
        setOnLoadListeners();

    }

    private void setOnLoadListeners() {

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        switch (menuItem.getTitle().toString()){
                            case "Get Player List":
                                Intent myIntent = new Intent(GetPlayersActivity.this, ListPlayerActivity.class);
                                GetPlayersActivity.this.startActivity(myIntent);
                                break;
                                default: break;
                        }


                        return true;
                    }
                });


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Seek Bar for Level
        sbLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtSbProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Switch for Gender
        swGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swGender.setText(R.string.lbGenderMale);
                } else {
                    swGender.setText(R.string.lbGenderFemale);
                }
            }
        });

        //Button for Adding a new Player
        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveNewPlayer();
                BadmintonManagerDbHelper mDbHelper = new BadmintonManagerDbHelper(v.getContext());
                mDbHelper.saveNewProfile(v.getContext(), txtName.getText().toString(), sbLevel.getProgress(), swGender.getText().toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_get_player_list) {
            Intent activity_about = new Intent(this, ListPlayerActivity.class);
            startActivity(activity_about);
        }


        //mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}