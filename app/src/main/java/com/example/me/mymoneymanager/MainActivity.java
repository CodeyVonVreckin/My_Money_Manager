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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myToggle;
    //public static final String EXTRA_MESSAGE="com.example.myapplication.MESSAGE";
    private static MyMoneyManager _db;
    public static final String MESSAGE ="Amount_Under_Budget";

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

        DisplayCurrentPayPeriod();

        Intent intent = getIntent();
        String StartingAmount = intent.getStringExtra(EndPayPeriodPage.AVAILABLE);
        String AmountJustSpent = intent.getStringExtra((PurchasePage.SPENT));
       //   if being directed back from the EndPayPeriodPage
        if(StartingAmount != null){
            //  set the Amount available to Starting Amount
            RefreshAmounts(StartingAmount);
            //  clear the Extra in case it persists
            intent.removeExtra(EndPayPeriodPage.AVAILABLE);
        }else if(AmountJustSpent !=null){
            //  if so then update amounts based on new amount. and remove extra
            UpdateAmounts(AmountJustSpent);
            //  clear the Extra in case it persists
            intent.removeExtra((PurchasePage.SPENT));
        }else{
            //  need to add the display amounts
        }

        //  else the user is just opening the app so call the DisplayAmounts function.

        //  will want one for coming back to main page after making a purchase
        //String UpdateAmount = intent
    }

    public void DisplayAmounts(View view){

        TextView amountAvailable = (TextView)findViewById(R.id.amountAvailable);
        TextView amountSpent = (TextView)findViewById(R.id.amountSpent);
        try{
            //  need to grab the contraints
            Date start = new Date();
            amountAvailable.setText("0.00");
            amountSpent.setText(("0.00"));
          //  double spent = _db.purchaseDOA().GetTotalAmountsInIntervals()
        }catch(Exception e){

        }
    }

    public void TestDate(View view ){
        TextView start = (TextView)findViewById((R.id.amountAvailable));
        TextView end = (TextView)findViewById((R.id.amountSpent));
        try{
            Calendar startDate = Calendar.getInstance();
            startDate.add(Calendar.MONTH, -1);
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.MONTH, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
            String formatStart = sdf.format(startDate.getTime());
            String formatEnd = sdf.format((endDate.getTime()));
            start.setText(formatStart);
            end.setText(formatEnd);
        }catch(Exception e){
            start.setText(e.getMessage());
        }
    }

    public void UpdateAmounts(String amount){
        TextView amountAvailable = (TextView)findViewById(R.id.amountAvailable);
        TextView amountSpent = (TextView)findViewById(R.id.amountSpent);
        try{
            double amountIncrement = Double.parseDouble(amount);
            double updatedAmountAvailable = (Double.parseDouble(amountAvailable.getText().toString()) - amountIncrement) ;
            double updatedAmountSpent = (Double.parseDouble(amountSpent.getText().toString()) + amountIncrement);

            amountAvailable.setText(Double.toString(updatedAmountAvailable));
            amountSpent.setText((Double.toString(updatedAmountSpent)));

        }catch (Exception e){

        }

    }

    // displays the current pay period (26th-26th) would like for this to be automated
    //  as well as to trigger some sort of refresh function on the amount available and spent
    public void DisplayCurrentPayPeriod(){
        TextView payPeriod = (TextView)findViewById((R.id.payPeriod));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        TextView amountAvailable = (TextView)findViewById(R.id.amountAvailable);
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        String formatStart;
        String formatEnd;
        try{
            //  if today is a pay day or later in the month set the starting month
            //  equal to the current month and set the end month equal to the following month
            if(start.get(Calendar.DAY_OF_MONTH) >= 26){

                start.set(Calendar.DAY_OF_MONTH, 26);
                end.add(Calendar.MONTH, 1);
                end.set(Calendar.DAY_OF_MONTH, 26);
                //  would add the Refresh pay period function here!!!
                //  not a great implementation but w/e
                 if(Double.parseDouble(amountAvailable.getText().toString()) < 1000 )
                     EndPayPeriod();

            }else{
                //  otherwise set the month of the start to the previous month,
                //  and make the end of the period equal the current month
                start.add(Calendar.MONTH, -1);
                start.set(Calendar.DAY_OF_MONTH, 26);
                end.set(Calendar.DAY_OF_MONTH, 26);
            }
            formatStart = sdf.format(start.getTime());
            formatEnd = sdf.format((end.getTime()));
            payPeriod.setText(formatStart + " - "+ formatEnd);

        }catch (Exception e){
            payPeriod.setText(e.getMessage());
        }
    }

    public void EndPayPeriod(){
        TextView amountAvailable = (TextView)findViewById(R.id.amountAvailable);
        try{

            Intent intent = new Intent(this,EndPayPeriodPage.class);
            String message =amountAvailable.getText().toString();
            intent.putExtra(MESSAGE, message);
            startActivity(intent);

        }catch (Exception e){
            // add error handling and perhaps send some notification...
        }
    }

    public void RefreshAmounts(String amount){
        TextView amountAvailable = (TextView)findViewById(R.id.amountAvailable);
        TextView amountSpent = (TextView)findViewById(R.id.amountSpent);
        try{
            //  double check to make sure the value doesnt get passed in a null or nothing
            if(amount != null && amount != "0.00"){
                amountAvailable.setText(amount);
                amountSpent.setText(("0.00"));
            }
        }catch(Exception e){

        }
    }

    //  click event for the Make Purchase Button
    public void MakePurchase(View view){
        ChangePage(PurchasePage.class);
    }

    public void ChangePage(Class className){
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }

    public void SetToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
}
