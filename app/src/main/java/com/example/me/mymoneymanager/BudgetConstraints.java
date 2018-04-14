package com.example.me.mymoneymanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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

}
