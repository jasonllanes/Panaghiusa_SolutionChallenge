package com.sldevs.panaghiusa.ProfilePane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sldevs.panaghiusa.ContributionSteps_Organic.O_S3;
import com.sldevs.panaghiusa.Home_Screen;
import com.sldevs.panaghiusa.R;

public class MyContribution extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contribution);





    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(MyContribution.this, Home_Screen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}