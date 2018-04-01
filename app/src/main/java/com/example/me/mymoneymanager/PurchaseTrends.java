package com.example.me.mymoneymanager;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class PurchaseTrends extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_trends);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_trend_options, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        if(id== R.id.days){
            SetToast("Daily");
        }
        if (id==R.id.weeks){
            SetToast("Weekly");
        }
        if (id==R.id.months){
            SetToast("Monthy");
        }
        if (id==R.id.years){
            SetToast("Yearly");
        }
        if (id==R.id.year_to_date){
            SetToast("YTD Totals");
        }

       return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.days){
            //  i like the Toast thing so remember that
            SetToast("This is Budget Plan");

        }
        if(id ==R.id.weeks){
            SetToast("This is Savings Plan");

        }
        if(id == R.id.months){
            SetToast("This is Purchase Trends");
        }
        if(id == R.id.year_to_date){
            SetToast("This is Purchase Trends");
        }
        return false;
    }

    public void SetToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
