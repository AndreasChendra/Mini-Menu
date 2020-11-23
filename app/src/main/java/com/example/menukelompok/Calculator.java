package com.example.menukelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculator extends AppCompatActivity {

    private int[] numericBtn = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};
    private int[] operatorBtn = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
    private TextView txtView;
    private boolean isNumber; //last key num/not
    private boolean currentState; //current state error/not
    private boolean comma; //true cannot add comma

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().setTitle("Calculator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.txtView = (TextView) findViewById(R.id.txtView); //Initialization TextView
        setNumericOnClickListener();
        setOperatorOnClickListener();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if first input and second input
                Button button = (Button) v;
                if (currentState) {
                    txtView.setText(button.getText());
                    currentState = false;
                } else {
                    txtView.append(button.getText());
                }
                isNumber = true;
            }
        };
        for (int id : numericBtn) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    //Operator
    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNumber && !currentState) {
                    Button button = (Button) v;
                    txtView.append(button.getText());
                    isNumber = false;
                    comma = false; //reset comma
                }
            }
        };
        for (int id : operatorBtn) {
            findViewById(id).setOnClickListener(listener);
        }
        // Decimal point
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNumber && !currentState && !comma) {
                    txtView.append(".");
                    isNumber = false;
                    comma = true;
                }
            }
        });
        // Clear btn
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText("");  // Clear the screen
                // Reset all
                isNumber = false;
                currentState = false;
                comma = false;
            }
        });
        // Equal btn
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    private void onEqual() {
        if (isNumber && !currentState) {
            String txt = txtView.getText().toString();
            //using exp4j
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                txtView.setText(Double.toString(result));
                comma = true;
            } catch (ArithmeticException ex) {
                //Error msg
                txtView.setText("Error");
                currentState = true;
                isNumber = false;
            }
        }
    }
}