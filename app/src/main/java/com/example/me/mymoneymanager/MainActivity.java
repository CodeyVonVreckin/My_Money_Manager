package com.example.me.mymoneymanager;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myToggle;
    public static final String EXTRA_MESSAGE="com.example.myapplication.MESSAGE";
    private static MyMoneyManager _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _db = MyMoneyManager.getAppDatabase(getApplicationContext());
        myDrawerLayout = (DrawerLayout)findViewById(R.id.mainPage);
        myToggle = new ActionBarDrawerToggle(this,myDrawerLayout,R.string.open, R.string.open);
        myDrawerLayout.addDrawerListener(myToggle);
        myToggle.syncState();
        // back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView =(NavigationView)findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void DisplayAmounts(View view){

        TextView amountAvailable = (TextView)findViewById(R.id.amountAvailable);
        TextView amountSpent = (TextView)findViewById(R.id.amountSpent);
        try{
            Date start = new Date();
          //  double spent = _db.purchaseDOA().GetTotalAmountsInIntervals()
        }catch(Exception e){

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(myToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //  Click event for each of the Nav Bar Tabs
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.budgetPlan){
            //  i like the Toast thing so remember that
            SetToast("This is Budget Plan");
            //  Redirects the user to the Budget Plan page
            ChangePage(BudgetPlan.class);

        }
        if(id ==R.id.savingsPlan){
            SetToast("This is Savings Plan");
            //  Redirects the user to the Savings Plan page
            ChangePage(SavingsPlan.class);
        }
        if(id == R.id.purchaseTrends){
           SetToast("This is Purchase Trends");
            //  Redirects the user to the Purchase Trends page
           ChangePage(PurchaseTrends.class);
        }

        return false;
    }

    //  click event for the Make Purchase Button
    public void MakePurchase(View view){
        ChangePage(PurchasePage.class);
    }

    //  would like for this event to be automatically called on the 26th
    //  Click event for the End Pay Period Button (button is mainly for testing)
    public void EndPayPeriod(View view){
       ChangePage(EndPayPeriodPage.class);
    }

    public void ChangePage(Class className){
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }

    public void SetToast(String message){
         Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
