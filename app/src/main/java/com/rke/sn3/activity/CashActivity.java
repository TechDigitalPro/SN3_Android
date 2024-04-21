package com.rke.sn3.activity;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.rke.sn3.R;
import com.rke.sn3.adapter.CartAdapter;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class CashActivity extends AppCompatActivity {


    private SN3Application appl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(CashActivity.this);
        setContentView(R.layout.activity_cash);
        appl = SN3Application.Me;


    }

}
