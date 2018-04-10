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
    TextView txtSbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_players);

        sbLevel = (SeekBar) findViewById(R.id.sbLevel);
        txtSbProgress = (TextView) findViewById(R.id.txtSbProgress);
        txtName = (TextView) findViewById(R.id.txtName);
        swGender = (Switch) findViewById(R.id.swGender);

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
    }
}
