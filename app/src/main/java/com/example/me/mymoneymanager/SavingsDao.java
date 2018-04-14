package com.example.me.mymoneymanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SavingsDao {

    @Query("SELECT * FROM Savings")
    Savings GetSavings();

    @Update
    int AddToSavings(Savings savings);

}
