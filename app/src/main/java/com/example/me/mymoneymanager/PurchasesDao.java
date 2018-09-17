package com.example.me.mymoneymanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface PurchasesDao {

    @Query("SELECT * FROM Purchases")
    List<Purchases> GetAllPurchases();

    @Query("SELECT SUM(Amount) FROM Purchases")
    double GetTotalAmountSpent();

    @Query("SELECT SUM(Amount) FROM Purchases WHERE DateOfPurchase between :startDate and :endDate")
    double GetTotalAmountsInIntervals(double startDate, double endDate);

    @Query("SELECT AVG(Amount) FROM Purchases WHERE DateOfPurchase between :startDate and :endDate")
    double GetAverageAmountsInIntervals(double startDate, double endDate);

    @Query("SELECT AVG(Amount) as Amount, DateOfPurchase, Uid FROM Purchases Where DateOfPurchase between :startDate and :endDate Group By DateOfPurchase Order By AVG(Amount) asc")
    Purchases GetMostSpentOnAvgPerInterval(double startDate, double endDate);

    @Insert
    void MakePurchase(Purchases... purchases);

    @Delete
    void RemovePurchase(Purchases purchases);
}
