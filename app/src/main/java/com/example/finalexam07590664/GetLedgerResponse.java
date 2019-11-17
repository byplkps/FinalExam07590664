package com.example.finalexam07590664;

import com.example.finalexam07590664.db.LedgerItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLedgerResponse extends BaseResponse {

    @SerializedName("data_list")
    public List<LedgerItem> ledgerItemList;

}