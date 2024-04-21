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
import com.rke.sn3.common.Util;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private Context context;
    private SwipeRefreshLayout layoutRefresh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

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
