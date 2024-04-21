package com.rke.sn3.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rke.sn3.R;

import java.util.ArrayList;
import java.util.List;

import stepview.HorizontalStepView;
import stepview.bean.StepBean;


public class OrderServiceAdapter extends RecyclerView.Adapter<OrderServiceAdapter.ItemHolder> {

    private ArrayList<String> mList;
    private Context mContext;
    private View.OnClickListener mListener;

    public OrderServiceAdapter(Context context, View.OnClickListener listener, ArrayList<String> list) {

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
        View view = inflater.inflate(R.layout.item_order_service, parent, false);

        return new ItemHolder(view);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(@NonNull final OrderServiceAdapter.ItemHolder viewHolder, final int i) {

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

        private HorizontalStepView stepView;
        public ItemHolder(View itemView) {
            super(itemView);
            HorizontalStepView stepView = (HorizontalStepView) itemView.findViewById(R.id.step_view);
            List<StepBean> stepsBeanList = new ArrayList<>();
            StepBean stepBean0 = new StepBean(mContext.getString(R.string.order_made),1);
            StepBean stepBean1 = new StepBean(mContext.getString(R.string.confirmed),1);
            StepBean stepBean2 = new StepBean(mContext.getString(R.string.appointment),1);
            StepBean stepBean3 = new StepBean(mContext.getString(R.string.started_work),0);
            StepBean stepBean4 = new StepBean(mContext.getString(R.string.completed),-1);
            stepsBeanList.add(stepBean0);
            stepsBeanList.add(stepBean1);
            stepsBeanList.add(stepBean2);
            stepsBeanList.add(stepBean3);
            stepsBeanList.add(stepBean4);


            stepView.setStepViewTexts(stepsBeanList)//总步骤
                    .setTextSize(10)//set textSize
                    .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(mContext, R.color.colorStepViewLine))//设置StepsViewIndicator完成线的颜色
                    .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(mContext, R.color.light_text_color))//设置StepsViewIndicator未完成线的颜色
                    .setStepViewComplectedTextColor(ContextCompat.getColor(mContext, R.color.light_text_color))//设置StepsView text完成线的颜色
                    .setStepViewUnComplectedTextColor(ContextCompat.getColor(mContext, R.color.light_text_color))//设置StepsView text未完成线的颜色
                    .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(mContext, R.drawable.ic_step_view_complted))//设置StepsViewIndicator CompleteIcon
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(mContext, R.drawable.ic_step_view_default_icon))//设置StepsViewIndicator DefaultIcon
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(mContext, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
        }
    }
}


