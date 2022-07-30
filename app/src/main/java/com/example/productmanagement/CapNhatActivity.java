package com.example.productmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CapNhatActivity extends AppCompatActivity {

    EditText etxt_tensanpham2, etxt_nsx2, etxt_soluong2, etxt_giaban2 ;
    Button btn_capnhat, btn_xoa;

    String msp, tsp,sl, nsx, gb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat);

        etxt_tensanpham2 = findViewById(R.id.etxt_tensanpham2);
        etxt_nsx2 = findViewById(R.id.etxt_nsx2);
        etxt_soluong2 = findViewById(R.id.etxt_soluong2);
        etxt_giaban2 = findViewById(R.id.etxt_giaban2);

        btn_capnhat = findViewById(R.id.btn_capnhat2);
        btn_xoa = findViewById(R.id.btn_xoa2);

        getAndSetIntentData();
        // Lấy tên sản phẩm để hiện trên ActivityLabel CapNhatActivity (Lấy tên sản phẩm làm tiêu đề)
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(tsp);
        }


        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database myDB = new Database(CapNhatActivity.this);
                tsp = etxt_tensanpham2.getText().toString().trim();
                nsx = etxt_nsx2.getText().toString().trim();
                sl = etxt_soluong2.getText().toString().trim();
                gb = etxt_giaban2.getText().toString().trim();
                myDB.updateProduct(msp, tsp, nsx, sl, gb);
                finish();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("msp") && getIntent().hasExtra("tsp") && getIntent().hasExtra("nsx") && getIntent().hasExtra("sl") && getIntent().hasExtra("gb")){
            msp = getIntent().getStringExtra("msp");
            tsp = getIntent().getStringExtra("tsp");
            nsx = getIntent().getStringExtra("nsx");
            sl = getIntent().getStringExtra("sl");
            gb = getIntent().getStringExtra("gb");


            etxt_tensanpham2.setText(tsp);
            etxt_nsx2.setText(nsx);
            etxt_soluong2.setText(sl);
            etxt_giaban2.setText(gb);

        }else{
            Toast.makeText(this, "Không có dữ liệu.", Toast.LENGTH_SHORT).show();
        }
    }
    // Tạo hàm thực hiện hộp thoại trước khi xóa
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa " + tsp + " ?");
        builder.setMessage("Bạn có chắc muốn xóa " + tsp + " ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db = new Database(CapNhatActivity.this);
                db.deleteProduct(msp);
                finish();//------
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}