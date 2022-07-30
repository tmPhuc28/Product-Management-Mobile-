package com.example.productmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "ManagementProduct.db"; // tên database
    private static final int DATABASE_VERSION = 1;                      // version database

    private static final String TEN_BANG = "SanPham";                  // tên bảng

    private static final String COT_MA = "MaSP";                       //Mã sản phẩm
    private static final String COT_TEN = "TenSP";                     // Tên sản phẩm
    private static final String COT_NSX = "NhaSanXuat";                // Nhà sản xuất
    private static final String COT_SOLUONG = "SoLuong";               // Số lượng
    private static final String COT_GIA = "GiaBan";                    // Giá bán

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    // create table ( Hàm thực hiện tạo bảng)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TEN_BANG + " (" + COT_MA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COT_TEN + " TEXT, "
                + COT_NSX + " TEXT, "
                + COT_SOLUONG + " TEXT,"
                + COT_GIA + " TEXT);";
         db.execSQL(query);
    }
    // Drop table(Hàm kiểm tra bảng tồn tại)
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS " + TEN_BANG);
    onCreate(db);
    }
    // Create product(Thêm sản phẩm)
    void createProduct(String tsp, String nsx, String sl, String gb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COT_TEN, tsp);
        cv.put(COT_NSX, nsx);
        cv.put(COT_SOLUONG, sl);
        cv.put(COT_GIA, gb);
        long result =  db.insert(TEN_BANG, null, cv);
        if(result == -1){
            Toast.makeText(context, "Lỗi!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        }
    }
    // Select Products(Lấy dữ liệu sản phẩm)
    Cursor SelectProduct(){
        String query = "SELECT * FROM " + TEN_BANG;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    // Update Product(Cập nhật sản phẩm)
    void updateProduct(String msp, String tsp, String nsx, String sl, String gb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COT_TEN, tsp);
        cv.put(COT_NSX, nsx);
        cv.put(COT_SOLUONG, sl);
        cv.put(COT_GIA, gb);

        long result =  db.update(TEN_BANG, cv, "MaSP=?", new String[]{msp});
        if(result == -1){
            Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        }
    }
    // Delete Product(Xóa 1 sản phẩm)
    void deleteProduct(String msp){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TEN_BANG, "MaSP=?", new String[]{msp});
        if(result == -1){
            Toast.makeText(context, "Lỗi! Không thể xóa!!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
        }
    }
    //Delete All Products (Xóa tất cả sản phẩm)
    void deleteAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TEN_BANG);
    }

}
