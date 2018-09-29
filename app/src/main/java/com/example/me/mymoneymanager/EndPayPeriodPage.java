package com.example.me.mymoneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EndPayPeriodPage extends AppCompatActivity {

    private static MyMoneyManager _db;
   // public static final String SPENT = "Amount_Spent";
    public static final String AVAILABLE = "Amount_Available";

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
    }



    public void StartNewPeriod (View view){
        EditText allowance = findViewById(R.id.allowanceAmount);
        EditText savings = findViewById(R.id.savingAmount);
        TextView overBudgetNote = findViewById(R.id.overBudgetNote);
        try{
            BudgetConstraints constraints = _db.budgetConstraintsDao().GetConstraints();
            double total = (Double.parseDouble(allowance.getText().toString()) + Double.parseDouble(savings.getText().toString()));

            if( total > constraints.MonthlyPay()){
                overBudgetNote.setText("Invalid Inputs. Allowance and Savings must be less than or equal to the monthly budget ($"+constraints.MonthlyPay()+")");
            }
            else{
                //  go back to main page with refreshed values.
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra(AVAILABLE, allowance.toString());
                //intent.putExtra(SPENT, "0.00");
                startActivity(intent);
            }
        }
        catch(Exception e){

        }
    }
}
