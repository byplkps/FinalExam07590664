package com.example.finalexam07590664.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LedgerDao {

    @Query("SELECT * FROM ledger")
    List<LedgerItem> getAll();

    @Insert
    void insert(LedgerItem ledgerItem);
}
