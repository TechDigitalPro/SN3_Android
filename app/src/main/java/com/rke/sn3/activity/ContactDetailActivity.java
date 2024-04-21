package com.rke.sn3.activity;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.rke.sn3.R;
import com.rke.sn3.common.SN3Application;

public class ContactDetailActivity extends AppCompatActivity {


    private SN3Application appl;
    private CalendarView calendarView;
    private LinearLayout ly_left, ly_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(ContactDetailActivity.this);
        setContentView(R.layout.activity_contract_detail);
        appl = SN3Application.Me;

        calendarView = findViewById(R.id.calendarView);
        ly_left =  findViewById(R.id.layout_left);
        ly_right = findViewById(R.id.layout_right);
        ly_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.scrollToPre(true);
            }
        });

        ly_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.scrollToNext(true);
            }
        });
        calendarView.scrollToCurrent(true);

    }
    //calendar method
    public void calendarListener() {
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {}

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {

            }
        });
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {

            }
        });
    }
}
