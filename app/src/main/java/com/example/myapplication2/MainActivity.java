package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

import android.util.Log;
import android.widget.Toast;





public class MainActivity extends AppCompatActivity {

    TextView HP, Ten;
    Button btnNext;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        HP = findViewById(R.id.txtfbHP);
        Ten = findViewById(R.id.txtfbTen);
        btnDangNhap = findViewById((R.id.btnDangNhap));
        btnNext = findViewById((R.id.btnNext));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(myintent);
            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Lấy document đầu tiên trong collection "users"
        db.collection("Thu Ao Cua T")
                .document("id")
                .get()
                .addOnSuccessListener(document -> {
                    if (document.exists()) {
                        String ten = document.getString("name");
                        String hp = String.valueOf(document.getLong("hp")); // vì hp là số

                        Ten.setText(ten != null ? ten : "Không có tên");
                        HP.setText(hp != null ? hp : "Không có dữ liệu");
                    } else {
                        Toast.makeText(MainActivity.this, "Không tìm thấy document", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "Lỗi khi đọc dữ liệu", e);
                    Toast.makeText(MainActivity.this, "Lỗi khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
                });



    }

}