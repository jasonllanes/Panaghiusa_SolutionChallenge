package com.sldevs.panaghiusa.ContributionSteps_Organic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.shuhart.stepview.StepView;
import com.sldevs.panaghiusa.Home_Screen;
import com.sldevs.panaghiusa.R;

import java.util.ArrayList;

public class O_S1 extends AppCompatActivity {

    public StepView stepView;
    ImageView btnBackS1;
    CheckBox cbFruits,cbVegetables,cbLeaves,cbPaper,cbChoiceDay1,cbChoiceDay2,cbChoiceDay3,cbChoiceDay4,cbChoiceDay5;
    Button btnNextS1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os1);
        stepView = findViewById(R.id.step_view);
        cbFruits = findViewById(R.id.cbFruits);
        cbVegetables = findViewById(R.id.cbVegetables);
        cbLeaves = findViewById(R.id.cbLeaves);
        cbPaper = findViewById(R.id.cbPaper);
        cbChoiceDay1 = findViewById(R.id.cbChoiceDay1);
        cbChoiceDay2 = findViewById(R.id.cbChoiceDay2);
        cbChoiceDay3 = findViewById(R.id.cbChoiceDay3);
        cbChoiceDay4 = findViewById(R.id.cbChoiceDay4);
        cbChoiceDay5 = findViewById(R.id.cbChoiceDay5);
        btnBackS1 = findViewById(R.id.btnBackS1);
        btnNextS1 = findViewById(R.id.btnNextS1);

        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .steps(new ArrayList<String>() {{
                    add("First Step");
                    add("Second Step");
                    add("Third Step");
                }})
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
        btnBackS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnNextS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "Selected Organic Waste";
                if(cbFruits.isChecked()){
                    result += "\nFruits";
                }
                if(cbVegetables.isChecked()){
                    result += "\nVegetables";
                }
                if(cbLeaves.isChecked()){
                    result += "\nLeaves";
                }
                if(cbPaper.isChecked()){
                    result += "\nPaper/Cardboard";
                }
                if(cbFruits.isChecked()==false && cbVegetables.isChecked()==false && cbLeaves.isChecked()==false && cbPaper.isChecked()==false){
                    Toast.makeText(getApplicationContext(), "Please make sure you selected at least one.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(O_S1.this, O_S2.class);
                    startActivity(i);
                }


            }
        });

    }
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbFruits:
                str = checked?"Fruits Selected":"Fruits Deselected";
                break;
            case R.id.cbVegetables:
                str = checked?"Vegetables Selected":"Vegetables Deselected";
                break;
            case R.id.cbLeaves:
                str = checked?"Leaves Selected":"Leaves Deselected";
                break;
            case R.id.cbPaper:
                str = checked?"Paper Selected":"Paper Deselected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}