package com.rke.sn3.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;
import com.rke.sn3.adapter.ReviewAdapter;
import com.rke.sn3.adapter.SliderAdapter;
import com.rke.sn3.common.SN3Application;
import com.rke.sn3.model.SliderModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {


    private SN3Application appl;
    private RecyclerView review_rv;
    private ReviewAdapter reviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(ReviewActivity.this);

        setContentView(R.layout.activity_review);
        appl = SN3Application.Me;
        initReviews();

    }
    private void initReviews(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");


        reviewAdapter = new ReviewAdapter(ReviewActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(ReviewActivity.this, LinearLayoutManager.VERTICAL, false);
        review_rv = findViewById(R.id.review_rv);
        review_rv.setLayoutManager(mLayoutManager_horizontal);
        review_rv.setItemAnimator(new DefaultItemAnimator());
        review_rv.setAdapter(reviewAdapter);
    }
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
