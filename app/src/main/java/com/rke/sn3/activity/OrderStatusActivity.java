package com.rke.sn3.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.rke.sn3.R;
import com.rke.sn3.adapter.OrderServiceAdapter;
import com.rke.sn3.common.SN3Application;
import com.vinay.stepview.HorizontalStepView;
import com.vinay.stepview.models.Step;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusActivity extends AppCompatActivity {


    private SN3Application appl;
    private PieChart pie_chart;
    private Typeface tf;
    private LineChart line_chart;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(OrderStatusActivity.this);
        setContentView(R.layout.activity_order_status);
        appl = SN3Application.Me;
        mContext = OrderStatusActivity.this;
        HorizontalStepView horizontalStepView = findViewById(R.id.step_view);

        List<Step> stepList = new ArrayList<>();
        stepList.add(new Step(getString(R.string.inprocessing), Step.State.COMPLETED));
        stepList.add(new Step(getString(R.string.shipping), Step.State.COMPLETED));
        stepList.add(new Step(getString(R.string.delivery))); // State defaults to NOT_COMPLETED

        horizontalStepView // Also applies to VerticalStepView
                // Drawables
                .setCompletedStepIcon(AppCompatResources.getDrawable(mContext, R.drawable.ic_step_view_complted))
                .setNotCompletedStepIcon(AppCompatResources.getDrawable(mContext, R.drawable.ic_step_view_default_icon))
                .setCurrentStepIcon(AppCompatResources.getDrawable(mContext, R.drawable.attention))
                // Text colors
                .setCompletedStepTextColor(ContextCompat.getColor(mContext, R.color.dark_text_color)) // Default: Color.WHITE
                .setNotCompletedStepTextColor(ContextCompat.getColor(mContext, R.color.light_text_color)) // Default: Color.WHITE
                .setCurrentStepTextColor(ContextCompat.getColor(mContext, R.color.dark_text_color)) // Default: Color.WHITE
                // Line colors
                .setCompletedLineColor(ContextCompat.getColor(mContext, R.color.colorStepViewLine)) // Default: Color.WHITE
                .setNotCompletedLineColor(ContextCompat.getColor(mContext, R.color.light_text_color)) // Default: Color.WHITE
                // Text size (in sp)
                .setTextSize(10) // Default: 14sp
                // Drawable radius (in dp)
                .setCircleRadius(12) // Default: ~11.2dp
                // Length of lines separating steps (in dp)
                .setLineLength(110); // Default: ~34dp

        horizontalStepView.setSteps(stepList);

    }

}
