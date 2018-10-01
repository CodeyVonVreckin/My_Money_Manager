package com.example.me.mymoneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

public class PurchasePage extends AppCompatActivity {

    private static MyMoneyManager _db;
    private RadioGroup radioGroup;
    public static final String SPENT = "Amount_Spent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_page);
        _db = MyMoneyManager.getAppDatabase(getApplicationContext());

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

    }

    public void AddPurchase(View view){
        RadioButton rb = (RadioButton)radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        EditText amountSpent = findViewById(R.id.spent);
        TextView validation = findViewById(R.id.validation);
        Purchases purchase = new Purchases();

        try{
            if(rb.getText() == null || rb.getText() == "" || amountSpent.getText() == null || amountSpent.getText().toString() == ""){
                validation.setText("Enter an Amount and Select a Category");
            }
            else{
                Date now = new Date();
                purchase.Amount(Double.parseDouble(amountSpent.getText().toString()));
                purchase.Category(rb.getText().toString());
                purchase.DateOfPurchase(now.getTime());
                _db.purchaseDOA().MakePurchase(purchase);

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra(SPENT, amountSpent.getText().toString());
                startActivity(intent);
            }
        }catch (Exception e){
            validation.setText(e.getMessage());
        }
    }


}
