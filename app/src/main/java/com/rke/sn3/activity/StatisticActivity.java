package com.rke.sn3.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rke.sn3.R;
import com.rke.sn3.common.Constant;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class StatisticActivity extends AppCompatActivity implements OnChartValueSelectedListener {


    private SN3Application appl;
    private PieChart pie_chart;
    private Typeface tf;
    private LineChart line_chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(StatisticActivity.this);
        setContentView(R.layout.activity_statistic);
        appl = SN3Application.Me;

        pie_chart = findViewById(R.id.pie_chart);
        initPieChartUI();

        line_chart = findViewById(R.id.line_chart);

        LineData data = getLineChartData(7, 150);
        data.setValueTypeface(tf);

        // add some transparency to the color with "& 0x90FFFFFF"
        setUpLineChart(line_chart, data);
    }
    private void initPieChartUI(){
        pie_chart.setUsePercentValues(true);
        pie_chart.getDescription().setEnabled(false);

        pie_chart.setDragDecelerationFrictionCoef(0.95f);

        tf  = ResourcesCompat.getFont(this, R.font.robotoslab_regular);


        pie_chart.setCenterTextTypeface(ResourcesCompat.getFont(this, R.font.robotoslab_regular));
        pie_chart.setCenterText(generateCenterSpannableText());

        pie_chart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        pie_chart.setDrawHoleEnabled(true);
        pie_chart.setHoleColor(Color.WHITE);

        pie_chart.setTransparentCircleColor(Color.WHITE);
        pie_chart.setTransparentCircleAlpha(110);

        pie_chart.setHoleRadius(58f);
        pie_chart.setTransparentCircleRadius(61f);

        pie_chart.setDrawCenterText(true);

        pie_chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pie_chart.setRotationEnabled(true);
        pie_chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        pie_chart.setOnChartValueSelectedListener(this);



        pie_chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = pie_chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);


        setPieChartData();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("$3,200");
        s.setSpan(new RelativeSizeSpan(2.0f), 0, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length() , 0);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark_text_color)), 0, s.length() , 0);
        return s;
    }
    private void setPieChartData() {
        String[] parties = new String[] {
                getResources().getString(R.string.buy), getResources().getString(R.string.services), getResources().getString(R.string.cleaning), getResources().getString(R.string.tools)
        };
        float[] range = new float[]{ 33, 45, 12, 10};

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < parties.length; i++) {
            entries.add(new PieEntry(range[i], parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "SN3 Statistics");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(20f);
        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();



        colors.add(ColorTemplate.rgb("#0f9fdc"));
        colors.add(ColorTemplate.rgb("#63eac9"));

        colors.add(ColorTemplate.rgb("#10d0dd"));
        colors.add(ColorTemplate.rgb("#cee1e8"));



        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.4f);
        dataSet.setValueLinePart2Length(0.2f);
        dataSet.setValueLineColor(getResources().getColor(R.color.dark_text_color));

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(getResources().getColor(R.color.dark_text_color));
        data.setValueTypeface(tf);
        pie_chart.setData(data);

        // undo all highlights
        pie_chart.highlightValues(null);

        pie_chart.invalidate();
    }
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void setUpLineChart(LineChart chart, LineData data) {


        chart.setOnChartValueSelectedListener(this);

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

        // set an alternative background color
        chart.setBackgroundColor(Color.TRANSPARENT);

        // add data

        chart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(tf);
        l.setTextSize(11f);
        l.setTextColor(Color.RED);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        chart.setData(data);

    }
    private LineData getLineChartData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "Statistics");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.25f);
        set1.setCircleRadius(3f);
        set1.setCircleHoleRadius(1.5f);
        set1.setCircleHoleColor(getResources().getColor(R.color.colorPrimary));
        set1.setColor(getResources().getColor(R.color.colorPrimary));
        set1.setCircleColor(getResources().getColor(R.color.colorPrimary));
        set1.setHighLightColor(getResources().getColor(R.color.colorPrimary));
        set1.setDrawValues(false);

        // create a data object with the data sets
        return new LineData(set1);
    }

}
