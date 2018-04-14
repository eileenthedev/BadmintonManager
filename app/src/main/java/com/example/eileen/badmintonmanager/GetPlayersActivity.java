package com.example.eileen.badmintonmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import BadmintonManagerDAL.BadmintonManagerDbHelper;

public class GetPlayersActivity extends AppCompatActivity {

    TextView txtName;
    SeekBar sbLevel;
    Switch swGender;
    TextView txtSbProgress;
    Button btnAddPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_players);

        sbLevel = (SeekBar) findViewById(R.id.sbLevel);
        txtSbProgress = (TextView) findViewById(R.id.txtSbProgress);
        txtName = (TextView) findViewById(R.id.txtName);
        swGender = (Switch) findViewById(R.id.swGender);
        btnAddPlayer = (Button) findViewById(R.id.btnAddPlayer);
        setOnLoadListeners();


    }

    private void setOnLoadListeners(){
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
                if(isChecked){
                    swGender.setText(R.string.lbGenderMale);
                }
                else{
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
                mDbHelper.saveNewProfile(v.getContext(), txtName.getText().toString(), sbLevel.getProgress(),  swGender.getText().toString());
            }
        } );



    }


}
