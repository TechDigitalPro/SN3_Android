package com.rke.sn3.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;
import com.rke.sn3.adapter.NotificationAdapter;
import com.rke.sn3.adapter.ProductAdapter;
import com.rke.sn3.adapter.ProductDetailAdapter;
import com.rke.sn3.common.Constant;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {


    private SN3Application appl;
    private ProductDetailAdapter productDetailAdapter;
    private ProductAdapter productAdapter;
    private RecyclerView rv_products_detail;
    private RecyclerView rv_products;

    private boolean grid_type = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(ProductActivity.this);

        setContentView(R.layout.activity_product);
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
                            = new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv_products_detail.setLayoutManager(mLayoutManager_horizontal);
                }

            }
        });
        initProduct();
        initProductDetail();
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

    private void initProduct(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");


        productAdapter = new ProductAdapter(ProductActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv_products = findViewById(R.id.rv_products);
        rv_products.setLayoutManager(mLayoutManager_horizontal);
        rv_products.setItemAnimator(new DefaultItemAnimator());
        rv_products.setAdapter(productAdapter);
    }
    private void initProductDetail(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");


        productDetailAdapter = new ProductDetailAdapter(ProductActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_products_detail = findViewById(R.id.rv_products_detail);
        rv_products_detail.setLayoutManager(mLayoutManager_horizontal);
        rv_products_detail.setItemAnimator(new DefaultItemAnimator());
        rv_products_detail.setAdapter(productDetailAdapter);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}
