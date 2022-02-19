package com.sldevs.panaghiusa.ContributionSteps_Plastic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shuhart.stepview.StepView;
import com.sldevs.panaghiusa.R;

public class P_S2 extends AppCompatActivity {
    public StepView stepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps2);

        stepView = findViewById(R.id.step_view);
        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(4)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
        stepView.go(1,true);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.steps_frame,new S1()).commit();

    }
}