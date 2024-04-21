package com.rke.sn3.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;
import com.rke.sn3.adapter.TicketsAdapter;
import com.rke.sn3.adapter.TicketsContentAdapter;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class TicketsContentActivity extends AppCompatActivity {


    private SN3Application appl;
    private TicketsContentAdapter ticketsContentAdapter;
    private RecyclerView rv_tickets_content;

    private boolean grid_type = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(TicketsContentActivity.this);

        setContentView(R.layout.activity_ticket_content);
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


        ticketsContentAdapter = new TicketsContentAdapter(TicketsContentActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_vertical
                = new LinearLayoutManager(TicketsContentActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_tickets_content = findViewById(R.id.rv_tickets_content);
        rv_tickets_content.setLayoutManager(mLayoutManager_vertical);
        rv_tickets_content.setItemAnimator(new DefaultItemAnimator());
        rv_tickets_content.setAdapter(ticketsContentAdapter);
    }


    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}
