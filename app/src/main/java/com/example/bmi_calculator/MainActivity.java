package com.example.bmi_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        String alertText = "Hi dear user!";
//        Toast.makeText(this,alertText , Toast.LENGTH_LONG).show();
        findViews();
        setupButtonClickListener();
        

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupButtonClickListener(){
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "from button", Toast.LENGTH_SHORT).show();
                double bmiResult =calculate_bmi();


                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age<18){
                    dsplayGuidance(bmiResult);
                }
                else{
                    displayResult(bmiResult);
                }
            }
        });
    }



    private double calculate_bmi() {

        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

       // resultText.setText("Age: "+ ageText + ", feet: "+ feetText + ", inches: "+ inchesText + ", weight: "+ weightText);


        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        double heightInMeters = totalInches * 0.0254;
        double bmi = weight / (heightInMeters * heightInMeters);

//        String ss = String.valueOf(bmi);
//        resultText.setText(ss);
        return bmi;

    }
       private void displayResult(double bmi) {
           DecimalFormat myDecimalFormater = new DecimalFormat("0.00");
           String bmiTextResult = myDecimalFormater.format(bmi);

           String finalResult;
           if (bmi < 18.5) {
               finalResult = "BMA is : " + bmiTextResult + " -> You are underweight";
           } else if (bmi > 25) {
               finalResult = "BMA is : " + bmiTextResult + " -> You are overweight";
           } else {
               finalResult = "BMA is : " + bmiTextResult + " -> You are healthy weight";
           }
           resultText.setText(finalResult);
       }

    private void dsplayGuidance(double bmi) {
        DecimalFormat myDecimalFormater = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormater.format(bmi);

        String revisedResult;
        if(maleButton.isChecked()) {
            if (bmi < 18.5) {
                revisedResult = "BMA is : " + bmiTextResult + "  -> As you are under 18, underweight and a boy, please eat more and more.";
            } else if (bmi > 25) {
                revisedResult = "BMA is : " + bmiTextResult + "  -> As you are under 18, overweight and a boy, please play more and more with boy";
            }
            else{
                revisedResult = "BMA is : " + bmiTextResult + "  -> As you are under 18, perfect health and a boy, please maintain this.";
            }
        }
        else if(femaleButton.isChecked()){
            if (bmi < 18.5) {
                revisedResult = "BMA is : " + bmiTextResult + "  -> As you are under 18, underweight and a girl, please eat more and more.";
            } else if (bmi > 25) {
                revisedResult = "BMA is : " + bmiTextResult + "  -> As you are under 18, overweight and a girl, please do some home exercise";
            }
            else{
                revisedResult = "BMA is : " + bmiTextResult + "  -> As you are under 18, perfect health and a girl, please maintain this.";
            }
        }
        else{
            revisedResult = "BMA is : " + bmiTextResult + "  -> As you are under 18 and common gender, please consult with anyone.";
        }

        resultText.setText(revisedResult);
    }

    private void findViews(){
         resultText = findViewById(R.id.text_view_result);
        //resultText.setText("I can manipulate result from here!");

         maleButton = findViewById(R.id.radio_button_male);
         femaleButton = findViewById(R.id.radio_button_female);

         ageEditText = findViewById(R.id.edit_text_age);
         feetEditText = findViewById(R.id.edit_text_feet);
         inchesEditText = findViewById(R.id.edit_text_inches);
         weightEditText = findViewById(R.id.edit_text_weight);
         calculateButton = findViewById(R.id.button_calculate);
        
    }
}