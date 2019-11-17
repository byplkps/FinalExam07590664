package com.example.finalexam07590664;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalexam07590664.db.LedgerItem;
import com.example.finalexam07590664.db.LedgerItem2;
import com.example.finalexam07590664.db.LedgerRepository;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        private void insertLedger(LedgerItem2 item) {
            LedgerRepository repo = new LedgerRepository(LoginActivity.this);
            repo.insertLedger(item, new LedgerRepository.InsertCallback() {
                @Override
                public void onInsertSuccess() {
                    finish();
                }
            });
        }

    private void insertLedger(LedgerItem2 item) {
        LedgerRepository repo = new LedgerRepository(RegisterActivity.this);
        repo.insertLedger(item, new LedgerRepository.InsertCallback() {
            @Override
            public void onInsertSuccess() {
                finish();
            }
        });
    }

    Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            EditText fullnameEditext = findViewById(R.id.full_name_edit_text);
            String fullname =  fullnameEditext.getText().toString();

            EditText usernameEditText = findViewById(R.id.username_edit_text);
            String username =  usernameEditText.getText().toString();

            EditText passwordEditText = findViewById(R.id.password_edit_text);
            String password =  passwordEditText.getText().toString();

            LedgerItem2 item = new LedgerItem2(0, fullname, username, password );

            insertLedger(item); // กรณีเก็บข้อมูลในฐานข้อมูล SQLite บนมือถือ
        }
    });




}


}
}