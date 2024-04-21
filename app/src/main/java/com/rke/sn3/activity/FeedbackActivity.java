package com.rke.sn3.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rke.sn3.R;
import com.rke.sn3.common.SN3Application;

public class FeedbackActivity extends AppCompatActivity {


    private SN3Application appl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(FeedbackActivity.this);
        setContentView(R.layout.activity_feedback);
        appl = SN3Application.Me;
        findViewById(R.id.txtFeedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFeedbackDialog();
            }
        });
    }
    private void  showFeedbackDialog(){
        final Dialog dialog = new Dialog(FeedbackActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_feedback_done);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView  txtOk= (TextView) dialog.findViewById(R.id.txtOk);

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (!isFinishing())  dialog.show();
    }
}
