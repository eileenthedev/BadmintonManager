package com.example.eileen.badmintonmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class GetPlayersActivity extends AppCompatActivity {

    TextView txtName;
    SeekBar sbLevel;
    Switch swGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_players);
    }
}
