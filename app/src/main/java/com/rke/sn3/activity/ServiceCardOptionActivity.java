package com.rke.sn3.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;
import com.rke.sn3.adapter.ServiceCardOptionAdapter;
import com.rke.sn3.adapter.TicketsAdapter;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class ServiceCardOptionActivity extends AppCompatActivity {


    private SN3Application appl;
    private ServiceCardOptionAdapter serviceCardOptionAdapter;
    private RecyclerView rv_server_card_option;

    private boolean grid_type = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(ServiceCardOptionActivity.this);

        setContentView(R.layout.activity_service_card_option);
        appl = SN3Application.Me;


        initTickets();
    }



    private void initTickets(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");

        serviceCardOptionAdapter = new ServiceCardOptionAdapter(ServiceCardOptionActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_vertical
                = new LinearLayoutManager(ServiceCardOptionActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_server_card_option = findViewById(R.id.rv_service_card_option);
        rv_server_card_option.setLayoutManager(mLayoutManager_vertical);
        rv_server_card_option.setItemAnimator(new DefaultItemAnimator());
        rv_server_card_option.setAdapter(serviceCardOptionAdapter);
    }


    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}
