package com.example.me.mymoneymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PurchasePage extends AppCompatActivity {

    private static MyMoneyManager _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_page);
        _db = MyMoneyManager.getAppDatabase(getApplicationContext());

    }


}
