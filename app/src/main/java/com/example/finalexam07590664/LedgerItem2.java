package com.example.finalexam07590664;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ledger2")
public class LedgerItem2 {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    public int id;


    @ColumnInfo(name = "fullname")
    @SerializedName("fullname")
    public String fullname;

    @ColumnInfo(name = "username")
    @SerializedName("username")
    public String username;

    @ColumnInfo(name = "password")
    @SerializedName("password")
    public String password;

    public LedgerItem2(int id, String fullname,String username, String password) {
        this.id = id;

        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }
}
