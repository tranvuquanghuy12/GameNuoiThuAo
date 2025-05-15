package com.example.myapplication2;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtMyLv;
    private final int[] imageArrayTam = {R.drawable.tam1, R.drawable.tam2, R.drawable.tam3, R.drawable.tam4};
    private final int[] imageArrayAn = {R.drawable.an1, R.drawable.an2, R.drawable.an3, R.drawable.an4};

    private final int[] imageArrayButton = {R.drawable.button1, R.drawable.button2, R.drawable.button3, R.drawable.button2, R.drawable.button1,R.drawable.button};
    private final int[] imageArrayBG = {
            R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5,
            R.drawable.bg6, R.drawable.bg7, R.drawable.bg8, R.drawable.bg9, R.drawable.bg10,
            R.drawable.bg11, R.drawable.bg12, R.drawable.bg13, R.drawable.bg14, R.drawable.bg15,
            R.drawable.bg16
    };

    private final Handler handler1 = new Handler();
    private final Handler handler2 = new Handler();
    private Runnable runnable1;
    private Runnable runnable2;
    private ProgressBar thanhEXP;
    private int currentEXP = 0;
    private final int maxEXP = 100;
    private int lv = 1;
    boolean isActive = false; // Mặc định là "rảnh"
    boolean isDaAnNut = false; // Đã ấn nút xong



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Khởi tạo các view
        txtMyLv = findViewById(R.id.txtMyLv); // Chỉnh lại chỗ này
        thanhEXP = findViewById(R.id.thanhEXP); // Khởi tạo ProgressBar
        thanhEXP.setMax(maxEXP);

        ImageView imageViewBtnAn = findViewById(R.id.imageViewBtnAn);
        ImageView imageViewBtnTam = findViewById(R.id.imageViewBtnTam);
        ImageView imageViewBtnChienDau = findViewById(R.id.imageViewBtnChienDau);
        ImageView imageViewBtnChiSo = findViewById(R.id.imageViewBtnChiSo);

        imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);

        FrameLayout btnTam = findViewById(R.id.btnTam);
        FrameLayout btnAn = findViewById(R.id.btnAn);
        FrameLayout btnChienDau = findViewById(R.id.btnChienDau);
        FrameLayout btnChiSo = findViewById(R.id.btnChiSo);

        TextView btnAnText = findViewById((R.id.btnAnText));
        TextView btnTamText = findViewById((R.id.btnTamText));
        TextView btnChienDauText = findViewById((R.id.btnChienDauText));
        TextView btnChiSoText = findViewById((R.id.btnChiSoText));

        // Lấy giá trị từ TextView txtMyLv
        String levelText = txtMyLv.getText().toString();
        lv = Integer.parseInt(levelText);  // Chuyển string sang int


        imageView.setImageResource(R.drawable.basic);
        startAnimation(imageView2, imageArrayBG, 200, handler2);

        btnTam.setOnClickListener(v -> {
            if (!isActive) {
                startAnimationButton(imageViewBtnTam, imageArrayButton, 100, handler2, btnTamText);
                isActive = true;
                startAnimation(imageView, imageArrayTam, 500, handler1);

                handler1.postDelayed(() -> stopAnimation(imageView, handler1, runnable1), 2000);
                currentEXP += 30;
                checkEXP();

            } else {
                new AlertDialog.Builder(MainActivity2.this)
                        .setTitle("Thông báo")
                        .setMessage("Đang bận làm việc khác, chờ chút điii?")
                        .setPositiveButton("OK", (dialog, which) -> Toast.makeText(getApplicationContext(), "Đồng ý nhé", Toast.LENGTH_SHORT).show())
                        .setNegativeButton("Hủy", null)
                        .show();

            }
        });

        btnAn.setOnClickListener(v -> {
            if (!isActive) {
                startAnimationButton(imageViewBtnAn, imageArrayButton, 100, handler2, btnAnText);
                isActive = true;
                startAnimation(imageView, imageArrayAn, 500, handler1);

                handler1.postDelayed(() -> stopAnimation(imageView, handler1, runnable1), 2000);
                currentEXP += 30;
                checkEXP();

            } else {
                new AlertDialog.Builder(MainActivity2.this)
                        .setTitle("Thông báo")
                        .setMessage("Đang bận làm việc khác, chờ chút điii?")
                        .setPositiveButton("OK", (dialog, which) -> Toast.makeText(getApplicationContext(), "Đồng ý nhé", Toast.LENGTH_SHORT).show())
                        .setNegativeButton("Hủy", null)
                        .show();

            }
        });


//        btnChiSo.setOnClickListener(v -> {
//            startAnimationButton(imageViewBtnChiSo, imageArrayButton, 100, handler2, btnChiSoText);
//            if (isDaAnNut = true)
//            {
//                Intent myintent = new Intent(MainActivity2.this,MainChiSo.class);
//                startActivity(myintent);
//                isDaAnNut = false;
//            }
//
//        });

        btnChienDau.setOnClickListener(v -> {
            startAnimationButton(imageViewBtnChienDau, imageArrayButton, 100, handler2, btnChienDauText);
            if (isDaAnNut = true)
            {
                Intent myintent = new Intent(MainActivity2.this,MainChienDau.class);
                startActivity(myintent);
                isDaAnNut = false;
            }

        });
    }





    private void startAnimation(final ImageView targetView, final int[] imageArray, final long delayMs, final Handler handler) {
        runnable1 = new Runnable() {
            int currentIndex = 0;

            @Override
            public void run() {
                targetView.setImageResource(imageArray[currentIndex]);
                currentIndex = (currentIndex + 1) % imageArray.length;
                handler.postDelayed(this, delayMs);
            }
        };
        handler.post(runnable1);
    }

    private void startAnimationButton(final ImageView targetView, final int[] imageArray, final long delayMs, final Handler handler, final TextView textView) {
        runnable2 = new Runnable() {
            int currentIndex = 0;

            @Override
            public void run() {
                targetView.setImageResource(imageArray[currentIndex]);
                float translateY = 0f;
                switch (currentIndex) {
                    case 0:
                        translateY = 10f;
                        break;
                    case 1:
                        translateY = 20f;
                        break;
                    case 2:
                        translateY = 25f;
                        break;
                    case 3:
                        translateY = 20f;
                        break;
                    case 4:
                        translateY = 10f;
                        break;
                    case 5:
                        translateY = 0f;
                        break;
                    default:
                        translateY = 0f;
                        break;
                }
                ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "translationY", textView.getTranslationY(), translateY);
                animator.setDuration(delayMs);
                animator.start();

                currentIndex = (currentIndex + 1) % imageArray.length;
                if (currentIndex == 0) {
                    handler.removeCallbacks(this);  // Dừng animation khi đã chạy hết các phần tử trong mảng
                } else {
                    // Nếu chưa hết, tiếp tục gọi run sau delayMs
                    handler.postDelayed(this, delayMs);
                }
                if (currentIndex == 5)
                    {
                        isDaAnNut = true ;
                    }

            }
        };
        handler.post(runnable2);
    }

    private void stopAnimation(final ImageView targetView, Handler handler, Runnable runnable) {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
            targetView.setImageResource(R.drawable.basic);
            isActive = false;
        }
    }

    private void checkEXP() {
        if (currentEXP >= maxEXP) {
            currentEXP = currentEXP % maxEXP;
            lv++;
            Toast.makeText(this, "Level Up! New Level: " + lv, Toast.LENGTH_SHORT).show();
        }
        txtMyLv.setText("" + lv); // Cập nhật level
        thanhEXP.setProgress(currentEXP);
    }
}

