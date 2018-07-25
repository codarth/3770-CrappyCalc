package com.example.zimquo.crappycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText first;
    EditText second;
    TextView calcResult;
    Button equals;
    Spinner operation;

    double num1, num2, calcTotal;
    String op;
    ArrayAdapter<CharSequence> operators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables from view ids
        first = (EditText) findViewById(R.id.num1);
        second = (EditText) findViewById(R.id.num2);
        calcResult = (TextView) findViewById(R.id.compRes);
        operation = (Spinner) findViewById(R.id.opperator);
        equals = (Button) findViewById(R.id.equal);

        // Setup Spinner
        operation = (Spinner) findViewById(R.id.opperator);
        operators = ArrayAdapter.createFromResource(this, R.array.ops, android.R.layout.simple_spinner_item);
        operators.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operation.setAdapter(operators);
        operation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is Selected", Toast.LENGTH_SHORT).show();

                op = ((Spinner) findViewById(R.id.opperator)).getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Do the calculation based on spinner selection when '=' is clicked
    public void onClick(View view) {
        // Check if Text views are empty
        if (first.getText().toString().equals("")) {
            Toast.makeText(this, "Enter the first number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (second.getText().toString().equals("")) {
            Toast.makeText(this, "Enter the second number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert input from input to doubles
        num1 = Double.parseDouble(first.getText().toString());
        num2 = Double.parseDouble(second.getText().toString());

        // Check division by 0
        if(op.equals("/")){
            if (num2 == 0){
                calcResult.setText(getResources().getString(R.string.ERROR));
                Toast.makeText(this, "Dividing by ZERO not allowed", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Do calculation
        switch (op) {
            case "+":
                calcTotal = num1 + num2;
                break;
            case "-":
                calcTotal = num1 - num2;
                break;
            case "*":
                calcTotal = num1 * num2;
                break;
            case "/":
                calcTotal = num1 / num2;
                break;
        }

        // Set TextView to the resulting calculation
        String ans = Double.toString(calcTotal);
        calcResult.setText(ans);

    }

}
