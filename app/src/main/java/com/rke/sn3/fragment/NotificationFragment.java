package com.rke.sn3.fragment;

import android.content.Context;
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
import com.rke.sn3.adapter.NotificationAdapter;
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

public class NotificationFragment extends Fragment {

    private Context context;
    private SwipeRefreshLayout layoutRefresh;
    private NotificationAdapter notificationAdapter;
    private RecyclerView notification_RV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

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


        initNotification(view);


    }

    private void initNotification(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");


        notificationAdapter = new NotificationAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        notification_RV = view.findViewById(R.id.rv_notification);
        notification_RV.setLayoutManager(mLayoutManager_horizontal);
        notification_RV.setItemAnimator(new DefaultItemAnimator());
        notification_RV.setAdapter(notificationAdapter);
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
