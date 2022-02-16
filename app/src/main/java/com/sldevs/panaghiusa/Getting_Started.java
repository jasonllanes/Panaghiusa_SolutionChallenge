package com.sldevs.panaghiusa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;

public class Getting_Started extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.createInstance(
                "Unity",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                R.drawable.unity,
                R.color.white,
                R.color.white,
                R.color.white,
                R.font.bbold,
                R.font.light,
                R.drawable.gradient_1

        ));
        addSlide(AppIntroFragment.createInstance(
                "Help",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                R.drawable.help,
                R.color.black,
                R.color.black,
                R.color.black,
                R.font.bbold,
                R.font.light,
                R.drawable.gradient_1

        ));
        addSlide(AppIntroFragment.createInstance(
                "Reduce",
                "This library offers developers the ability to add clean app intros at the start of their apps.",
                R.drawable.reduce,
                R.color.white,
                R.color.white,
                R.color.white,
                R.font.bbold,
                R.font.light,
                R.drawable.gradient_1

        ));

        setIndicatorColor(getResources().getColor(R.color.green),getResources().getColor(R.color.black));
        setTransformer(AppIntroPageTransformerType.Fade.INSTANCE);
        setImmersive(true);
        isColorTransitionsEnabled();
//        askForPermissions(
//                permissions = arrayOf(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                ),
//                slideNumber = 3,
//                required = false)
    }
    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent mainIntent = new Intent(Getting_Started.this,Sign_Up.class);
        startActivity(mainIntent);
        finish();
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent mainIntent = new Intent(Getting_Started.this,Sign_Up.class);
        startActivity(mainIntent);
        finish();

    }
}