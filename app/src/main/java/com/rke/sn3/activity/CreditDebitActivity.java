package com.rke.sn3.activity;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.rke.sn3.R;
import com.rke.sn3.common.SN3Application;

public class CreditDebitActivity extends AppCompatActivity {


    private SN3Application appl;
    private PieChart pie_chart;
    private Typeface tf;
    private LineChart line_chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(CreditDebitActivity.this);
        setContentView(R.layout.activity_credit_debit);
        appl = SN3Application.Me;

    }

}
