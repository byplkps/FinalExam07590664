package com.example.finalexam07590664.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ledger")
public class LedgerItem {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    public int id;

    @ColumnInfo(name = "username")
    @SerializedName("username")
    public String username;

    @ColumnInfo(name = "password")
    @SerializedName("password")
    public String password;

    public LedgerItem(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
