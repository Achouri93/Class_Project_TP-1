package com.gridev.projectlinearylyout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    // Views
    private EditText poids;
    private EditText taille;
    private EditText age;

    private RadioButton femme;
    private RadioButton homme;

    private Button calcule;
    private TextView result;
    private ImageView image;

    // properties
    private double poidsValue = 0;
    private double tailleValue = 0;
    private double ageValue = 0;
    private int checkedValue = 0;

    private int max;
    private int min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       init();

       calcule.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getValues();
               double value = calculateMG();
               getResult(value);
               
                // Toast.makeText(MainActivity.this, "Message", Toast.LENGTH_SHORT).show();
               // Log.i(TAG, "onClick: message");
           }
       });
    }

    /**
     *  Method for Initialize Views
     */
    private void init () {
        poids = findViewById(R.id.poids);
        taille = findViewById(R.id.taille);
        age = findViewById(R.id.age);

        femme = findViewById(R.id.femme);
        homme = findViewById(R.id.homme);

        calcule = findViewById(R.id.calcule);
        result = findViewById(R.id.result);
        image = findViewById(R.id.image);
    }

    /**
     *  Method for getting Input values
     */
    private void getValues() {
        poidsValue = Double.valueOf(poids.getText().toString());
        tailleValue = Double.valueOf(taille.getText().toString());
        ageValue = Double.valueOf(age.getText().toString());
        if (femme.isChecked()) {
            min = 15;
            max = 30;
            checkedValue = 0;
        }
        else {
            min = 10;
            max = 15;
            checkedValue = 1;
        }
    }

    /**
     * calculate @MG
     * @return @MG
     */
    private double calculateMG() {
        return (1.2 * poidsValue / (tailleValue * tailleValue)) +
                (0.23 * ageValue) - (10.83 * checkedValue) - 5.4;
    }

    private void getResult(double mg) {
        if(mg < min) {
            result.setText("tres faible");
            image.setImageResource(R.drawable.triste);
        }
        else if (mg > max) {
            result.setText("trop eleve");
            image.setImageResource(R.drawable.gras);
        }
        else {
            result.setText("Normal");
            image.setImageResource(R.drawable.bin);
        }
    }
}
