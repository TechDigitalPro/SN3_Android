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
import com.rke.sn3.adapter.CartAdapter;
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

public class ServiceProviderActivity extends AppCompatActivity {


    private SN3Application appl;
    private RecyclerView review_rv;
    private ReviewAdapter reviewAdapter;
    SliderView sliderView;
    private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(ServiceProviderActivity.this);

        setContentView(R.layout.activity_service_provider);
        appl = SN3Application.Me;
        initSlider();
        initReviews();

    }
    private void initSlider() {
        sliderView = findViewById(R.id.imageSlider);

        sliderAdapter = new SliderAdapter(ServiceProviderActivity.this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(getResources().getColor(R.color.grey_white));
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });


        List<SliderModel> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderModel sliderItem = new SliderModel();
            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://www.easyhometurkey.com/files/easyhome-cleaning-services-home-service.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
            } else {
                sliderItem.setImageUrl("https://usercontent.one/wp/www.amtmuscat.com/wp-content/uploads/2020/12/house-cleaning-service.png?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        sliderAdapter.renewItems(sliderItemList);
    }
    private void initReviews(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");



        reviewAdapter = new ReviewAdapter(ServiceProviderActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(ServiceProviderActivity.this, LinearLayoutManager.VERTICAL, false);
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
