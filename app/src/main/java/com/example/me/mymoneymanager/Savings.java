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
    private double Cash = 0.00;

    @ColumnInfo(name="Stocks_Bonds")
    private double Stocks_Bonds = 0.00;

    @ColumnInfo(name="Realestate")
    private double Realestate = 0.00;

    @ColumnInfo(name="Trip_Vacation")
    private double Trip_Vacation = 0.00;

    @ColumnInfo(name="Hobbies_BTI")
    private double Hobbies_BTI = 0.00;

    @ColumnInfo(name="Last_Added")
    private long Last_Added;

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

    public long Last_Added(){return Last_Added;}
    public void Last_Added(long val){Last_Added = val;}
}
