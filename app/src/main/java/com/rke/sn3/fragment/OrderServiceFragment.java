package com.rke.sn3.fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.rke.sn3.adapter.NotificationAdapter;
import com.rke.sn3.adapter.OrderServiceAdapter;
import com.rke.sn3.common.Util;

import java.util.ArrayList;

public class OrderServiceFragment extends Fragment {

    private Context context;
    private SwipeRefreshLayout layoutRefresh;
    private OrderServiceAdapter orderServiceAdapter;
    private RecyclerView orderService_RV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_service, container, false);

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


        initOrderService(view);


    }

    private void initOrderService(View view){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");


        orderServiceAdapter = new OrderServiceAdapter(context, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        orderService_RV = view.findViewById(R.id.rv_order_service);
        orderService_RV.setLayoutManager(mLayoutManager_horizontal);
        orderService_RV.setItemAnimator(new DefaultItemAnimator());
        orderService_RV.setAdapter(orderServiceAdapter);
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
