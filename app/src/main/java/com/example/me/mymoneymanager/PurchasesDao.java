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
    double GetTotalAmountsInIntervals(Date startDate, Date endDate);

    @Query("SELECT AVG(Amount) FROM Purchases WHERE DateOfPurchase between :startDate and :endDate")
    double GetAverageAmountsInIntervals(Date startDate, Date endDate);

    @Query("SELECT AVG(Amount), DateOfPurchase FROM Purchases Where DateOfPurchase between :startDate and :endDate Group By DateOfPurchase Order By AVG(Amount) asc")
    Date GetMostSpentOnAvgPerInterval(Date startDate, Date endDate);

    @Insert
    void MakePurchase(Purchases... purchases);

    @Delete
    void RemovePurchase(Purchases purchases);
}
