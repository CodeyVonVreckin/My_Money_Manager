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

}
