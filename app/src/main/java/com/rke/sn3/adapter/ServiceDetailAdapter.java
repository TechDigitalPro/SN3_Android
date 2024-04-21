package com.rke.sn3.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;

import java.util.ArrayList;


public class ServiceDetailAdapter extends RecyclerView.Adapter<ServiceDetailAdapter.ItemHolder> {

    private ArrayList<String> mList;
    private Context mContext;
    private View.OnClickListener mListener;

    public ServiceDetailAdapter(Context context, View.OnClickListener listener, ArrayList<String> list) {

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
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_service_detail, parent, false);

        return new ItemHolder(view);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(@NonNull final ServiceDetailAdapter.ItemHolder viewHolder, final int i) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View itemView) {
            super(itemView);

        }
    }
}


