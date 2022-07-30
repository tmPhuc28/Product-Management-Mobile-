package com.example.productmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button them_button;

    Database myDB;
    ArrayList<String> MaSP, TenSP,NhaSanXuat, SoLuong, GiaBan;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        them_button = findViewById(R.id.them_button);
        them_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemActivity.class);
                startActivity(intent);
            }
        });
        myDB = new Database(MainActivity.this);
        MaSP = new ArrayList<>();
        TenSP = new ArrayList<>();
        NhaSanXuat = new ArrayList<>();
        SoLuong = new ArrayList<>();
        GiaBan = new ArrayList<>();

        ProductArray();
        customAdapter = new CustomAdapter(MainActivity.this, this, MaSP, TenSP, NhaSanXuat, SoLuong, GiaBan);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            recreate();
        }
    }

    // Lấy dữ liệu trong Array
    void ProductArray(){
        Cursor cursor = myDB.SelectProduct();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "Chưa có sản phẩm nào !", Toast.LENGTH_LONG).show();
        }else {
            while(cursor.moveToNext()){
                MaSP.add(cursor.getString(0));
                TenSP.add(cursor.getString(1));
                NhaSanXuat.add(cursor.getString(2));
                SoLuong.add(cursor.getString(3));
                GiaBan.add(cursor.getString(4));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.xoa_tatca){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    // Tạo hàm thực hiện hộp thoại trước khi xóa (AlertDialog)
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa tất cả ?");
        builder.setMessage("Bạn có chắc muốn xóa tất cả ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db = new Database(MainActivity.this);
                db.deleteAllProducts();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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