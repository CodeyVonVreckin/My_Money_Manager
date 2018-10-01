package com.example.me.mymoneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class EndPayPeriodPage extends AppCompatActivity {

    private static MyMoneyManager _db;
   // public static final String SPENT = "Amount_Spent";
    public static final String AVAILABLE = "Amount_Available";
    BudgetConstraints CONSTRAINTS;
    EditText MONTHLYPAY= findViewById(R.id.monthlyPay);
    EditText FIXEDSAVINGS = findViewById(R.id.savingAmount);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_pay_period_page);
        _db = MyMoneyManager.getAppDatabase(getApplicationContext());
        //  grabs the message sent from the main activity. IE the amount left over at the
        //  end of the pay period
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE);
        //  sets the message at the top of the page showing the user how much they
        //  went under budget
        TextView endPeriodMessage = findViewById(R.id.underBudgetNote);
        endPeriodMessage.setText("YAY!!! Its Payday! You went $"+ message+" under budget this month.");
        AddToSavings(Double.parseDouble(message));
        //  adds the budget contraint for monthly pay to the text box

        EditText monthlyPay = findViewById(R.id.monthlyPay);
        try{
            CONSTRAINTS = _db.budgetConstraintsDao().GetConstraints();
            if(CONSTRAINTS.MonthlyPay() == 0.00 ){
                monthlyPay.setText("Add Monthly Pay");
            }
            else{
                monthlyPay.setText(Double.toString(CONSTRAINTS.MonthlyPay()));
            }
        }catch (Exception e){
            monthlyPay.setText(e.getMessage());
        }

}



    public void StartNewPeriod (View view){
        EditText allowance = findViewById(R.id.allowanceAmount);
       // EditText savings = findViewById(R.id.savingAmount);
        TextView overBudgetNote = findViewById(R.id.overBudgetNote);
        try{
            //BudgetConstraints constraints = _db.budgetConstraintsDao().GetConstraints();
            double total = (Double.parseDouble(allowance.getText().toString()) + Double.parseDouble(FIXEDSAVINGS.getText().toString()));

            if( total > Double.parseDouble(MONTHLYPAY.getText().toString())){
                overBudgetNote.setText("Invalid Inputs. Allowance and Savings must be less than or equal to the monthly budget ($"+MONTHLYPAY.getText()+")");
            }
            else{
                AddToSavings(Double.parseDouble(FIXEDSAVINGS.getText().toString()));
                UpdateConstraints();
                //  go back to main page with refreshed values.
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra(AVAILABLE, allowance.getText().toString());
                startActivity(intent);
            }
        }
        catch(Exception e){

        }
    }

    public void AddToSavings(Double savings){
        //  create a Savings Object
        Savings newRow = new Savings();
        Date now = new Date();
        try{
            newRow.Cash(savings);
            newRow.Last_Added(now.getTime());
            _db.savingsDao().AddToSavings(newRow);
        }catch (Exception e){

        }

    }
    public void UpdateConstraints(){
        BudgetConstraints constraints = new BudgetConstraints();
        try{
            constraints.MonthlyPay(Double.parseDouble(MONTHLYPAY.getText().toString()));
            constraints.FixedSavings(Double.parseDouble(FIXEDSAVINGS.getText().toString()));

            if(CONSTRAINTS.MonthlyPay() == 0.00){
                _db.budgetConstraintsDao().AddBudgetContraint(constraints);
            }
            else{
               _db.budgetConstraintsDao().UpdateConstraints(constraints);
            }
        }catch (Exception e){

        }
    }
}
