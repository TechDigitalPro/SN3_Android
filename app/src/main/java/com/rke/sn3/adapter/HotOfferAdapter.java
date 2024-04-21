package com.rke.sn3.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;

import java.util.ArrayList;
import java.util.List;


public class HotOfferAdapter extends RecyclerView.Adapter<HotOfferAdapter.ItemHolder> {

    private ArrayList<String> mList;
    private Context mContext;
    private View.OnClickListener mListener;

    public HotOfferAdapter(Context context, View.OnClickListener listener, ArrayList<String> list) {

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
        View view = inflater.inflate(R.layout.item_hot_offer, parent, false);

        return new ItemHolder(view);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(@NonNull final HotOfferAdapter.ItemHolder viewHolder, final int i) {

        viewHolder.txtPrice2.setBackground(mContext.getDrawable(R.drawable.strikethrough_shape));


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
        private  TextView txtPrice2;
        public ItemHolder(View itemView) {
            super(itemView);
            txtPrice2 = itemView.findViewById(R.id.txtPrice2);

        }
    }
}


