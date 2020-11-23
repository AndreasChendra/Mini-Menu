package com.example.menukelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Main Menu");
        exit();
    }

    private void exit() {
        btnExit = (Button) findViewById(R.id.button3);

        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                moveTaskToBack(true);
            }
        });
    }

    public void moveCalc(View view) {
        Intent intent = new Intent(MainActivity.this, Calculator.class);
        startActivity(intent);
    }

    public void moveBuy(View view) {
        Intent intent = new Intent(MainActivity.this, BuyMenu.class);
        startActivity(intent);
    }

}