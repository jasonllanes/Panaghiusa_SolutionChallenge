package com.sldevs.panaghiusa.ProfilePane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sldevs.panaghiusa.BottomNavFragments.Home;
import com.sldevs.panaghiusa.ContributionSteps_Organic.O_S3;
import com.sldevs.panaghiusa.Home_Screen;
import com.sldevs.panaghiusa.R;

public class MyContribution extends FragmentActivity {

    TextView tvTBC,tvTBP,tvC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contribution);

        tvTBC = findViewById(R.id.btnTBC);
        tvTBP = findViewById(R.id.btnTBP);
        tvC = findViewById(R.id.btnC);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contributionFrame,new TBC()).commit();

        tvTBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contributionFrame,new TBC()).commit();
            }
        });

        tvTBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contributionFrame,new TBP()).commit();
            }
        });

        tvC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contributionFrame,new C()).commit();
            }
        });





    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(MyContribution.this, Home_Screen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}