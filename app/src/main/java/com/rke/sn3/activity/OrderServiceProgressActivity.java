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
import com.rke.sn3.common.SN3Application;
import com.vinay.stepview.HorizontalStepView;
import com.vinay.stepview.VerticalStepView;
import com.vinay.stepview.models.Step;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceProgressActivity extends AppCompatActivity {


    private SN3Application appl;
    private PieChart pie_chart;
    private Typeface tf;
    private LineChart line_chart;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(OrderServiceProgressActivity.this);
        setContentView(R.layout.activity_order_service_progress);
        appl = SN3Application.Me;
        mContext = OrderServiceProgressActivity.this;
        VerticalStepView verticalStepView = findViewById(R.id.step_view);

        List<Step> stepList = new ArrayList<>();
        stepList.add(new Step(getString(R.string.order_created) + " - 12.04.2020", Step.State.COMPLETED));
        stepList.add(new Step(getString(R.string.appointment_validated) + " - 12.04.2020", Step.State.COMPLETED));
        stepList.add(new Step(getString(R.string.payment_received) + " - 12.04.2020", Step.State.COMPLETED));
        stepList.add(new Step(getString(R.string.appointment) + " - 12.04.2020", Step.State.COMPLETED));
        stepList.add(new Step(getString(R.string.travelling) + " - 12.04.2020", Step.State.COMPLETED));
        stepList.add(new Step(getString(R.string.started_work) + " - 12.04.2020"));
        stepList.add(new Step(getString(R.string.deliveried) + " - 12.04.2020"));


        verticalStepView // Also applies to VerticalStepView
                // Drawables
                .setReverse(false)
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
                .setTextSize(13) // Default: 14sp
                // Drawable radius (in dp)
                .setCircleRadius(14) // Default: ~11.2dp
                // Length of lines separating steps (in dp)
                .setLineLength(45);
        verticalStepView.setSteps(stepList);
    }

}
