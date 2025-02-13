package com.example.quanliduan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class manhinhchao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);  // XML layout cho màn hình chào

        // Đặt delay 2 giây rồi chuyển sang màn hình đăng nhập
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Mở màn hình đăng nhập (MainActivity)
                Intent intent = new Intent(manhinhchao.this, dangnhap.class);
                startActivity(intent);
                finish();  // Đóng màn hình chào
            }
        }, 1200);  // Đợi 2000ms (2 giây)
    }
}

