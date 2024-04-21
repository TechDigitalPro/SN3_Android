package com.rke.sn3.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rke.sn3.R;
import com.rke.sn3.common.SN3Application;

public class RegisterActivity extends AppCompatActivity {


    private SN3Application appl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(RegisterActivity.this);
        setContentView(R.layout.activity_register);
        appl = SN3Application.Me;


    }

}
