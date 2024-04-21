package com.rke.sn3.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rke.sn3.R;
import com.rke.sn3.adapter.CustomerReviewAdapter;
import com.rke.sn3.adapter.HotOfferAdapter;
import com.rke.sn3.adapter.ProductAdapter;
import com.rke.sn3.adapter.RecommendServiceAdapter;
import com.rke.sn3.adapter.ServiceAdapter;
import com.rke.sn3.adapter.SliderAdapter;
import com.rke.sn3.adapter.TopPerformsAdapter;
import com.rke.sn3.common.Util;
import com.rke.sn3.model.SliderModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Context context;
    private SwipeRefreshLayout layoutRefresh;

    private HotOfferAdapter hotOfferAdapter;
    private ServiceAdapter serviceAdapter;
    private ProductAdapter productAdapter;
    private RecommendServiceAdapter recommendServiceAdapter;
    private TopPerformsAdapter topPerformsAdapter;
    private CustomerReviewAdapter customerReviewAdapter;

    private RecyclerView hot_RV;
    private RecyclerView service_RV;
    private RecyclerView product_RV;
    private RecyclerView recommend_RV;
    private RecyclerView topPerforms_RV;
    private RecyclerView customerReview_RV;

    SliderView sliderView;
    private SliderAdapter sliderAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

 //        layoutRefresh = view.findViewById(R.id.layout_refresh);
//        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getEstimates();
//            }
//        });



        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSlider(view);
        initHotOffer(view);
        initService(view);
        initProduct(view);
        initTopPerforms(view);
        initRecommendService(view);
        initCustomerReview(view);


    }
    private void initSlider(View view) {
        sliderView = view.findViewById(R.id.imageSlider);


        sliderAdapter = new SliderAdapter(context);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(context.getResources().getColor(R.color.grey_white));
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
    private void initHotOffer(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");

        hotOfferAdapter = new HotOfferAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        hot_RV = view.findViewById(R.id.rv_hot_offers);
        hot_RV.setLayoutManager(mLayoutManager_horizontal);
        hot_RV.setItemAnimator(new DefaultItemAnimator());
        hot_RV.setAdapter(hotOfferAdapter);
    }

    private void initService(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");

        serviceAdapter = new ServiceAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        service_RV = view.findViewById(R.id.rv_service);
        service_RV.setLayoutManager(mLayoutManager_horizontal);
        service_RV.setItemAnimator(new DefaultItemAnimator());
        service_RV.setAdapter(serviceAdapter);
    }
    private void initProduct(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");

        productAdapter = new ProductAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        product_RV = view.findViewById(R.id.rv_products);
        product_RV.setLayoutManager(mLayoutManager_horizontal);
        product_RV.setItemAnimator(new DefaultItemAnimator());
        product_RV.setAdapter(productAdapter);
    }
    private void initTopPerforms(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");

        topPerformsAdapter = new TopPerformsAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        topPerforms_RV = view.findViewById(R.id.rv_topPerforms);
        topPerforms_RV.setLayoutManager(mLayoutManager_horizontal);
        topPerforms_RV.setItemAnimator(new DefaultItemAnimator());
        topPerforms_RV.setAdapter(topPerformsAdapter);
    }
    private void initRecommendService(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");

        recommendServiceAdapter = new RecommendServiceAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recommend_RV = view.findViewById(R.id.rv_recommend_services);
        recommend_RV.setLayoutManager(mLayoutManager_horizontal);
        recommend_RV.setItemAnimator(new DefaultItemAnimator());
        recommend_RV.setAdapter(recommendServiceAdapter);
    }
    private void initCustomerReview(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");

        customerReviewAdapter = new CustomerReviewAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        customerReview_RV = view.findViewById(R.id.rv_customer_review);
        customerReview_RV.setLayoutManager(mLayoutManager_horizontal);
        customerReview_RV.setItemAnimator(new DefaultItemAnimator());
        customerReview_RV.setAdapter(customerReviewAdapter);
    }
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private void getEstimates() {
        if (!Util.isNetworkConnected(context)) {
            //showMessage(R.string.error_network);
            return;
        }

    }
}
