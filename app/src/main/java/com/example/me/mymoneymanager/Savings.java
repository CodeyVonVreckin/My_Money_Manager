package com.example.me.mymoneymanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Savings")
public class Savings {
    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String Uid;

    @ColumnInfo(name="Cash")
    private double Cash;

    @ColumnInfo(name="Stocks_Bonds")
    private double Stocks_Bonds;

    @ColumnInfo(name="Realestate")
    private double Realestate;

    @ColumnInfo(name="Trip_Vacation")
    private double Trip_Vacation;

    @ColumnInfo(name="Hobbies_BTI")
    private double Hobbies_BTI;

    public String Uid(){return Uid;}
    public void Uid(String val){Uid = val;}

    public double Cash(){return Cash;}
    public void Cash(double val){Cash =val;}

    public double Stocks_Bonds(){return Stocks_Bonds;}
    public void Stocks_Bonds(double val){Stocks_Bonds =val;}

    public double Realestate(){return Realestate;}
    public void Realestate(double val){Realestate =val;}

    public double Trip_Vacation(){return Trip_Vacation;}
    public void Trip_Vacation(double val){Trip_Vacation =val;}

    public double Hobbies_BTI(){return Hobbies_BTI;}
    public void Hobbies_BTI(double val){Hobbies_BTI =val;}
}
