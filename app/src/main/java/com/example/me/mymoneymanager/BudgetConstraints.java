package com.example.me.mymoneymanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "BudgetConstraints")
public class BudgetConstraints {
    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String Uid;

    @ColumnInfo(name = "MonthlyPay")
    private double MonthlyPay;

    @ColumnInfo(name="FixedSavings")
    private double FixedSavings;

    @ColumnInfo(name="Rent_Bills")
    private double Rent_Bills;

    @ColumnInfo(name = "WeeklyAllowance")
    private double WeeklyAllowance;

    @ColumnInfo(name ="FoodConstraint")
    private double FoodConstraint;

    @ColumnInfo(name="EntertainmentConstraint")
    private double EntertainmentConstraint;

    @ColumnInfo(name="MiscExpendituresConstraint")
    private double MiscExpendituresConstraint;

    public String Uid(){return Uid;}
    public void Uid(String val){Uid =val;}

    public double MonthlyPay(){return MonthlyPay;}
    public void MonthlyPay(double val){MonthlyPay =val;}

    public double FixedSavings(){return FixedSavings;}
    public void FixedSavings(double val){FixedSavings =val;}

    public double Rent_Bills(){return Rent_Bills;}
    public void Rent_Bills(double val){Rent_Bills =val;}

    public double WeeklyAllowance(){return WeeklyAllowance;}
    public void WeeklyAllowance(double val){WeeklyAllowance =val;}

    public double FoodConstraint(){return FoodConstraint;}
    public void FoodConstraint(double val){FoodConstraint=val;}

    public double EntertainmentConstraint(){return EntertainmentConstraint;}
    public void EntertainmentConstraint(double val){EntertainmentConstraint =val;}

    public double MiscExpendituresConstraint(){return MiscExpendituresConstraint;}
    public void MiscExpendituresConstraint(double val){MiscExpendituresConstraint =val;}

    public void SetDefaultConstraints(MyMoneyManager db){

        BudgetConstraints constraints = new BudgetConstraints();
        String uuid = UUID.randomUUID().toString();
        constraints.Uid(uuid);
        constraints.MonthlyPay(3200.00);
        constraints.Rent_Bills(62.00);
        constraints.WeeklyAllowance(400.00);
        constraints.FixedSavings(1600.00);
        constraints.FoodConstraint(0.00);
        constraints.EntertainmentConstraint(0.00);
        constraints.MiscExpendituresConstraint(0.00);

        db.budgetConstraintsDao().AddBudgetContraint(constraints);
    }

}
