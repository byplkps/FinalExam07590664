package com.example.finalexam07590664;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalexam07590664.db.LedgerItem;
import com.example.finalexam07590664.db.LedgerRepository;

import java.util.List;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.putExtra("type", 0);
        startActivity(intent);
    }
    });


        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditText = findViewById(R.id.username_edit_text);
                String username = usernameEditText.getText().toString();

                EditText passwordEditText = findViewById(R.id.password_edit_text);
                String password =  passwordEditText.getText().toString();

                LedgerItem item = new LedgerItem(0, username, password );

                insertLedger(item); // กรณีเก็บข้อมูลในฐานข้อมูล SQLite บนมือถือ
             }
        });
    }

}


    @Override
    protected void onResume() {
        super.onResume();

        reloadData();
    }

    private void reloadData() {
        LedgerRepository repo = new LedgerRepository(LoginActivity.this);

        repo.getLedger(new LedgerRepository.Callback() {
            @Override
            public void onGetLedger(List<LedgerItem> itemList) {
                int totalAmount = 0;

                for (LedgerItem item : itemList) {
                    // totalAmount += item.amount;
                }

            }
        });
    }

    private void insertLedger(LedgerItem item) {
        LedgerRepository repo = new LedgerRepository(LoginActivity.this);
        repo.insertLedger(item, new LedgerRepository.InsertCallback() {
            @Override
            public void onInsertSuccess() {
                finish();
            }
        });
    }

    private void insertLedgerOnServer(LedgerItem item) {
        Retrofit retrofit = ApiClient.getClient();
        WebServices services = retrofit.create(WebServices.class);

        Call<InsertLedgerResponse> call = services.insertLedger(item.description, item.amount);
        call.enqueue(new Callback<InsertLedgerResponse>() {
            @Override
            public void onResponse(Call<InsertLedgerResponse> call, Response<InsertLedgerResponse> response) {
                InsertLedgerResponse result = response.body();

                if (result.errorCode == 0) {  // อ่านข้อมูลจาก MySQL สำเร็จ
                    // แสดง Success message
                    Toast.makeText(LoginActivity.this, result.errorMessage, Toast.LENGTH_LONG).show();
                    // ปิดหน้า InsertActivity เพื่อกลับไปหน้า MainActivity
                    finish();

                } else { // เกิด error ในการเพิ่มข้อมูลลง MySQL (อาจเกิดตอน connect db หรือตอนรันคำสั่ง SQL)
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Error")
                            .setMessage(result.errorMessage)
                            .setPositiveButton("OK", null)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<InsertLedgerResponse> call, Throwable t) { // error เช่น มือถือไม่มีเน็ต, server ล่ม
                Log.e("InsertActivity", "Error: " + t.getMessage());

                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Error")
                        .setMessage("เกิดข้อผิดพลาดในการเชื่อมต่อเครือข่าย")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }

}
