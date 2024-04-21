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
import com.rke.sn3.adapter.ServiceAdapter;
import com.rke.sn3.adapter.ServiceDetailAdapter;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {


    private SN3Application appl;
    private ServiceAdapter serviceAdapter;
    private ServiceDetailAdapter serviceDetailAdapter;
    private RecyclerView rv_services_detail;
    private RecyclerView rv_services;

    private boolean grid_type = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(ServiceActivity.this);

        setContentView(R.layout.activity_service);
        appl = SN3Application.Me;

        findViewById(R.id.sort_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDlgSortCategory();
            }
        });
        findViewById(R.id.grid_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid_type = !grid_type;
                if (grid_type) {
//                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductActivity.this, 3, LinearLayoutManager.VERTICAL, false);
//                    rv_products_detail.setLayoutManager(gridLayoutManager);
                }
                else {
                    LinearLayoutManager mLayoutManager_horizontal
                            = new LinearLayoutManager(ServiceActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv_services_detail.setLayoutManager(mLayoutManager_horizontal);
                }

            }
        });

        initService();
        initServiceDetail();
    }

    private void showDlgSortCategory(){


        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_category_sort, null);
        final RadioButton option_1 = dialogView.findViewById(R.id.option_1) ;
        final RadioButton option_2 = dialogView.findViewById(R.id.option_2) ;

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    private void initService(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");


        serviceAdapter = new ServiceAdapter(ServiceActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(ServiceActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv_services = findViewById(R.id.rv_services);
        rv_services.setLayoutManager(mLayoutManager_horizontal);
        rv_services.setItemAnimator(new DefaultItemAnimator());
        rv_services.setAdapter(serviceAdapter);
    }
    private void initServiceDetail(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");


        serviceDetailAdapter = new ServiceDetailAdapter(ServiceActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(ServiceActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_services_detail = findViewById(R.id.rv_services_detail);
        rv_services_detail.setLayoutManager(mLayoutManager_horizontal);
        rv_services_detail.setItemAnimator(new DefaultItemAnimator());
        rv_services_detail.setAdapter(serviceDetailAdapter);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}
