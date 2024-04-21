package com.rke.sn3.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rke.sn3.R;
import com.rke.sn3.common.Constant;
import com.rke.sn3.common.SN3Application;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    static final int PERM_REQUEST_CODES = 100;

    private SN3Application appl;
    String[] perms = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(SplashActivity.this);
        setContentView(R.layout.activity_splash);
        appl = SN3Application.Me;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        checkPerm(getApplicationContext());


    }

    public void checkPerm(Context context)
    {
        //int len = perms.length;
        List<String> permlist = new ArrayList<>();

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String perm : perms) {

                if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("main", "perm " + perm + " not granted" + "\n");

                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                        Log.i("main", "perm " + perm + " should request" + "\n");
                    }
                    permlist.add(perm);

                }
            }

            if (permlist.size() > 0) {
                ActivityCompat.requestPermissions(this, permlist.toArray(new String[permlist.size()]), PERM_REQUEST_CODES);
            }
            else {
                nextScreen();
            }
        }


        //chinese phone
        try {
            Intent intent = new Intent();
            String manufacturer = android.os.Build.MANUFACTURER;
            if ("xiaomi".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            } else if ("oppo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
            } else if ("vivo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
            } else if ("oneplus".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListAct‌​ivity"));
            }

            List<ResolveInfo> list = this.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (list.size() > 0) {
                this.startActivity(intent);
            }
        } catch (Exception e) {
            Log.d("Error", "" + e.toString());
        }
    }

    private void nextScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!appl.mPrefs.containsKey(Constant.PREF_LANG)) {
                    choose_language();
                }
                else{
                    show_mainScreen();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    private void show_mainScreen(){
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void show_introScreen(){
        Intent i = new Intent(SplashActivity.this, IntroScreenActivity.class);
        startActivity(i);
        finish();
    }
    private void choose_language(){


        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_language, null);
        final RadioButton option_1 = dialogView.findViewById(R.id.option_1) ;
        final RadioButton option_2 = dialogView.findViewById(R.id.option_2) ;

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        appl.putPref(Constant.PREF_LANG, Constant.DEF_LANG);
                        show_introScreen();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(option_1.isChecked()){
                            //english
                            appl.putPref(Constant.PREF_LANG, "en");
                        }
                        if(option_2.isChecked()){
                            appl.putPref(Constant.PREF_LANG, "ar");
                        }
                        show_introScreen();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }
    @Override
    public void onRequestPermissionsResult(int reqCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(reqCode, permissions, grantResults);
        if (reqCode == PERM_REQUEST_CODES && grantResults.length > 0) {
            for (int k = 0; k < grantResults.length; k++) {
                if (grantResults[k] == PackageManager.PERMISSION_DENIED) {
                    //Toast.makeText(getApplicationContext(), "you have no permissions!", Toast.LENGTH_LONG).show();
                    Log.i("permission", "result " + permissions[k] + " should request" + "\n");
                    finish();
                    return;
                }
            }
            nextScreen();
        }
    }
}
