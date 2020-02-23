package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.os.Vibrator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView timesTableListView;
    int timesTable;

    public void generateTimesTable(int timesTable){
        ArrayList<String> timeTableContent = new ArrayList<String>();

        for (int i = 1; i <=100; i++){
            timeTableContent.add(i + " * " + timesTable + " = " + Integer.toString(i * timesTable));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeTableContent);

        timesTableListView.setAdapter(arrayAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTableSeekBar = (SeekBar)findViewById(R.id.timesTableSeekBar);
        timesTableListView = (ListView)findViewById(R.id.timesTableListView);
        timesTable = 10;
        timesTableSeekBar.setMax(20);
        timesTableSeekBar.setProgress(10);



        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;

                if (progress < 1){
                    timesTableSeekBar.setProgress(min);
                }else{
                    timesTableSeekBar.setProgress(progress);
                }
                generateTimesTable(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(75, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        generateTimesTable(timesTable);


    }
}
