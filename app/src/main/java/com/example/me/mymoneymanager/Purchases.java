package com.example.me.mymoneymanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "Purchases")
public class Purchases {
    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String Uid;

    @ColumnInfo(name = "Category")
    private String Category = "";

    @ColumnInfo(name = "Amount")
    private double Amount = 0.00;

    @ColumnInfo(name="DateOfPurchase")
    private double DateOfPurchase = 0;


    public String Uid(){return Uid;}
    public void Uid(String val){Uid = val;}

    public String Category(){return Category;}
    public void Category(String val){Category = val;}

    public double Amount (){return Amount;}
    public void Amount(double val){Amount = val;}

    public double DateOfPurchase() {return DateOfPurchase;}
    public void DateOfPurchase(double val ){DateOfPurchase = val;}

}
