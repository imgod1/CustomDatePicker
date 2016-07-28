package com.example.gk.customdatepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gk.customdatepicker.views.CustomDatePicker;
import com.example.gk.customdatepicker.views.CustomDatePickerDialog;

public class MainActivity extends AppCompatActivity {

    private CustomDatePicker mdp_main;
    private Button btn_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    public void initView() {
        mdp_main = (CustomDatePicker) findViewById(R.id.mdp_main);
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
    }

    public void initEvent() {
        //限制能选择的最小时间和最大时间
        mdp_main.setDateRange(1, 1, 1999, 1, 1, 2017);
        mdp_main.setDefaultDate(2016, 7, 27);
        mdp_main.setOnSelectChangeListener(new CustomDatePicker.OnSelectChangeListener() {
            @Override
            public void changed(int year, int month, int day) {
                Log.d("initEvent", "date change:" + "year:" + year + "\tmonth:" + month + "\tday:" + day);
            }

        });

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        CustomDatePickerDialog customDatePickerDialog = new CustomDatePickerDialog(this);
        customDatePickerDialog.setDefaultDate(2015, 5, 5);
        customDatePickerDialog.setOnDialogButtonClickListener(new CustomDatePickerDialog.OnDialogButtonClickListener() {
            @Override
            public void positiveClick(int year, int month, int day) {
                Toast.makeText(MainActivity.this, "year:" + year + "month:" + month + "day:" + day, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void negativeClick() {

            }
        });
        customDatePickerDialog.show();
    }
}
