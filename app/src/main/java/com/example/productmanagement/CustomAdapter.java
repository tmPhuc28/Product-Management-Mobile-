package com.example.productmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList MaSP, TenSP,NhaSanXuat, SoLuong, GiaBan;
    private Context context;
    private Activity activity;

    CustomAdapter(Activity activity,Context context, ArrayList MaSP,  ArrayList TenSP, ArrayList NhaSanXuat, ArrayList SoLuong, ArrayList GiaBan ){
        this.activity = activity;
        this.context = context;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.NhaSanXuat = NhaSanXuat;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.txt_masp.setText(String.valueOf(MaSP.get(position)));
        holder.txt_tensp.setText(String.valueOf(TenSP.get(position)));
        holder.txt_nsx.setText(String.valueOf(NhaSanXuat.get(position)));
        holder.txt_soluong.setText(String.valueOf(SoLuong.get(position)));
        holder.txt_giaban.setText(String.valueOf(GiaBan.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CapNhatActivity.class);
                intent.putExtra("msp",String.valueOf(MaSP.get(position)));
                intent.putExtra("tsp",String.valueOf(TenSP.get(position)));
                intent.putExtra("nsx",String.valueOf(NhaSanXuat.get(position)));
                intent.putExtra("sl",String.valueOf(SoLuong.get(position)));
                intent.putExtra("gb",String.valueOf(GiaBan.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MaSP.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_masp, txt_tensp, txt_nsx, txt_soluong, txt_giaban;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_masp = itemView.findViewById(R.id.txt_masp);
            txt_tensp = itemView.findViewById(R.id.txt_tensp);
            txt_nsx = itemView.findViewById(R.id.txt_nsx);
            txt_soluong = itemView.findViewById(R.id.txt_soluong);
            txt_giaban = itemView.findViewById(R.id.txt_giaban);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
