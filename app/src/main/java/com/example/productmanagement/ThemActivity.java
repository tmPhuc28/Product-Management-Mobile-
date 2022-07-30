package com.example.productmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class ThemActivity extends AppCompatActivity {
    TextInputEditText etxt_tensanpham, etxt_nsx, etxt_soluong, etxt_giaban;
    Button btn_them;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        etxt_tensanpham = findViewById(R.id.etxt_tensanpham);
        etxt_nsx = findViewById(R.id.etxt_nsx);
        etxt_soluong = findViewById(R.id.etxt_soluong);
        etxt_giaban = findViewById(R.id.etxt_giaban);
        btn_them = findViewById(R.id.btn_them);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database myDB = new Database(ThemActivity.this);
                myDB.createProduct(etxt_tensanpham.getText().toString().trim(),
                        etxt_nsx.getText().toString().trim(),
                        etxt_soluong.getText().toString().trim(),
                        etxt_giaban.getText().toString().trim());
            }
        });
    }
}