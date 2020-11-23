package com.example.menukelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMenu extends AppCompatActivity {

    private EditText userNameedt, itemNameedt, quantityedt, priceedt, moneyPaidedt;
    private Button processbtn, resetbtn, exitbtn;
    private TextView userNametxt, itemNametxt, quantitytxt, pricetxt, moneyPaidtxt, totalPaymenttxt, changeMoneytxt, infotxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_menu);
        getSupportActionBar().setTitle("Buy Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        process();
        reset();
        exit();

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

    private void exit() {
        exitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onSupportNavigateUp();
            }
        });
    }

    private void reset() {
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNametxt.setText(" ");
                itemNametxt.setText(" ");
                quantitytxt.setText("Total Payment");
                pricetxt.setText(" ");
                moneyPaidtxt.setText(" ");
                changeMoneytxt.setText("Change Money");
                totalPaymenttxt.setText(" ");
                infotxt.setText("Information");

                Toast.makeText(getApplicationContext(),"Data Reset", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void process() {
        processbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameedt.getText().toString().trim();
                String itemName = itemNameedt.getText().toString().trim();
                String qty = quantityedt.getText().toString().trim();
                String price = priceedt.getText().toString().trim();
                String moneyPaid = moneyPaidedt.getText().toString().trim();

                double dMoneyPaid = Double.parseDouble(moneyPaid);

                double total = Double.parseDouble(qty) * Double.parseDouble(price);
                totalPaymenttxt.setText("Total Belanja : " + total);

                double changeMoney = (dMoneyPaid - total);

                if (dMoneyPaid < total){
                    infotxt.setText("Information : Need Rp " + (-changeMoney) + " to Process Transaction");
                    changeMoneytxt.setText("Change Money : Error");
                }else{
                    infotxt.setText("Information : Payment Sucess!");
                    changeMoneytxt.setText("Change Money : " + changeMoney);
                }
            }
        });
    }

    private void init() {
        userNameedt = (EditText) findViewById(R.id.name);
        itemNameedt = (EditText) findViewById(R.id.itemName);
        quantityedt = (EditText) findViewById(R.id.qty);
        priceedt = (EditText) findViewById(R.id.price);
        moneyPaidedt = (EditText) findViewById(R.id.moneyPaid);
        processbtn = (Button) findViewById(R.id.process);
        resetbtn = (Button) findViewById(R.id.reset);
        exitbtn = (Button) findViewById(R.id.exit);
        userNametxt = (TextView) findViewById(R.id.name);
        itemNametxt = (TextView) findViewById(R.id.itemName);
        quantitytxt = (TextView) findViewById(R.id.qty);
        pricetxt = (TextView) findViewById(R.id.price);
        moneyPaidtxt = (TextView) findViewById(R.id.moneyPaid);
        totalPaymenttxt = (TextView) findViewById(R.id.totalPayment);
        changeMoneytxt = (TextView) findViewById(R.id.changeMoney);
        infotxt = (TextView) findViewById(R.id.info);
    }


}