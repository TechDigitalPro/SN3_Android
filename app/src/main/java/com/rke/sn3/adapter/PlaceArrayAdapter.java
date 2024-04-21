package com.rke.sn3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;
import com.rke.sn3.model.PlaceModel;
import com.rke.sn3.util.OnItemClickListener;

import java.util.List;

/**
 * Created by linux102 on 3/4/18.
 */

public class PlaceArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlaceModel> placeList;
    private Context context;
    private OnItemClickListener.OnItemClickCallback onItemClickCallback;

    public PlaceArrayAdapter(Context context, List<PlaceModel> placeList, OnItemClickListener.OnItemClickCallback onItemClickCallback) {
        super();
        this.placeList = placeList;
        this.context = context;
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_place, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CustomViewHolder holder = (CustomViewHolder) viewHolder;
        holder.tvTitle.setText(placeList.get(position).getMainText());
        holder.tvAddress.setText(placeList.get(position).getSecondryText());
        holder.itemView.setOnClickListener(new OnItemClickListener(position, onItemClickCallback));
        Log.v("PlaceArrayAdapter "," PlaceArrayAdapter ");
    }

    @Override
    public int getItemCount() {
        return (null != placeList ? placeList.size() : 0);
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAddress;

        public CustomViewHolder(View view) {
            super(view);
            tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvAddress = (TextView)view.findViewById(R.id.tv_address);
        }
    }
}
