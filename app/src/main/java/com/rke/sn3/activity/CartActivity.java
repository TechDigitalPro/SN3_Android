package com.rke.sn3.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;
import com.rke.sn3.adapter.CartAdapter;
import com.rke.sn3.adapter.ProductAdapter;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rv_cart;
    private CartAdapter cartAdapter;

    private SN3Application appl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(CartActivity.this);
        setContentView(R.layout.activity_cart);
        appl = SN3Application.Me;
        initCart();

    }
    private void initCart(){
        ArrayList<String> testlist = new ArrayList<>();
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");
        testlist.add("1");



        cartAdapter = new CartAdapter(CartActivity.this, mListener,testlist);
        LinearLayoutManager mLayoutManager_horizontal
                = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_cart = findViewById(R.id.rv_cart);
        rv_cart.setLayoutManager(mLayoutManager_horizontal);
        rv_cart.setItemAnimator(new DefaultItemAnimator());
        rv_cart.setAdapter(cartAdapter);
    }
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}
