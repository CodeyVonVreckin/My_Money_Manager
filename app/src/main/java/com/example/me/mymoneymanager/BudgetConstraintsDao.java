package com.example.me.mymoneymanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface BudgetConstraintsDao {

    @Query("SELECT * FROM BudgetConstraints")
    BudgetConstraints GetConstraints();

    @Update
    int UpdateConstraints(BudgetConstraints budgetConstraints);

    @Insert
    void AddBudgetContraint(BudgetConstraints... budgetConstraints);
}
