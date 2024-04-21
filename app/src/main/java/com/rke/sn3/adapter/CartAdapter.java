package com.rke.sn3.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mList;
    private Context mContext;
    private View.OnClickListener mListener;

    public CartAdapter(Context context, View.OnClickListener listener, ArrayList<String> list) {

        mList = list;
        mContext = context;
        mListener = listener;
    }
    public void setMList(ArrayList<String> list){
        mList.clear();
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        if (viewType == 1){
            view = inflater.inflate(R.layout.item_cart_product, parent, false);
            return new ProductItemHolder(view);
        }
        else {
            view = inflater.inflate(R.layout.item_cart_service, parent, false);
            return new ServiceItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemViewType(int position) {
        return  position%2;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ProductItemHolder extends RecyclerView.ViewHolder {
        public ProductItemHolder(View itemView) {
            super(itemView);

        }
        public void setData(){

        }
    }

    class ServiceItemHolder extends RecyclerView.ViewHolder {
        public ServiceItemHolder(View itemView) {
            super(itemView);

        }
        public void setData(){

        }
    }
}


