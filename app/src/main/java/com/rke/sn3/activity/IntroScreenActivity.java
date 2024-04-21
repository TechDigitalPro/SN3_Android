package com.rke.sn3.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroCustomLayoutFragment;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;
import com.github.appintro.model.SliderPage;
import com.rke.sn3.R;

import static com.github.appintro.AppIntroPageTransformerType.*;

public class IntroScreenActivity extends AppIntro {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDoneTextTypeface(R.font.robotoslab_bold);
        setSkipTextTypeface(R.font.robotoslab_bold);
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout1));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout2));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout3));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout4));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_custom_layout5));


    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent i = new Intent(IntroScreenActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent i = new Intent(IntroScreenActivity.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}