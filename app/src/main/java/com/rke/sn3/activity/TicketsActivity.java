package com.rke.sn3.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;
import com.rke.sn3.adapter.ProductAdapter;
import com.rke.sn3.adapter.ProductDetailAdapter;
import com.rke.sn3.adapter.TicketsAdapter;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class TicketsActivity extends AppCompatActivity {


    private SN3Application appl;
    private TicketsAdapter ticketsAdapter;
    private RecyclerView rv_tickets;

    private boolean grid_type = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(TicketsActivity.this);

        setContentView(R.layout.activity_ticket);
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


        ticketsAdapter = new TicketsAdapter(TicketsActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_vertical
                = new LinearLayoutManager(TicketsActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_tickets = findViewById(R.id.rv_tickets);
        rv_tickets.setLayoutManager(mLayoutManager_vertical);
        rv_tickets.setItemAnimator(new DefaultItemAnimator());
        rv_tickets.setAdapter(ticketsAdapter);
    }


    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}
