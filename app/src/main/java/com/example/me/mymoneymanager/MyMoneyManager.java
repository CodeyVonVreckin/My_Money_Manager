package com.example.me.mymoneymanager;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

@Database(entities = {Purchases.class, Savings.class, BudgetConstraints.class}, version = 1)
public abstract class MyMoneyManager extends RoomDatabase{
    private static MyMoneyManager INSTANCE;
    public abstract PurchasesDao purchaseDOA();
    public abstract BudgetConstraintsDao budgetConstraintsDao();
    public abstract SavingsDao savingsDao();

    public static MyMoneyManager getAppDatabase(Context context){
        if(INSTANCE == null){
            //  apparently it is not a good idea to allow main thread queries ona  real application. I should look into why.
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyMoneyManager.class, "MyMoneyManagerDB").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void DestroyInstance(){
        INSTANCE =null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
}
