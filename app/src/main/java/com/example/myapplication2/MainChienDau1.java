package com.example.myapplication2;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainChienDau1 extends AppCompatActivity {

    private static final String TAG = "MainChienDau1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_chien_dau1);

        // Thiết lập padding cho system bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lấy monster_id từ Intent (nếu không có thì là -1)
        int monsterId = getIntent().getIntExtra("monster_id", -1);
        FrameLayout vitriMonster = findViewById(R.id.vitriMonster);


        if (monsterId != -1) {
            DBHelper dbHelper = new DBHelper(this);
            List<Monster> monsterList = dbHelper.getFirstNMonsters(3); // Lấy 2 con quái

            int[] textViewIds = {R.id.txtMonsterName, R.id.txtMonsterName1, R.id.txtMonsterName2};
            int[] imageViewIds = {R.id.ivMonsterImage, R.id.ivMonsterImage1, R.id.ivMonsterImage2};
            int[] frameLayoutIds = {R.id.vitriMonster, R.id.vitriMonster1, R.id.vitriMonster2};


            TextView txtHP = findViewById((R.id.txtMonsterHP));
            TextView txtTen = findViewById(R.id.thontinMonsterName);
            TextView txtMana = findViewById(R.id.txtMonsterMana);
            TextView txtTanCong = findViewById(R.id.txtMonsterAttack);
            TextView txtPhongNgu = findViewById(R.id.txtMonsterDef);

            for (int i = 0; i < monsterList.size() && i < textViewIds.length; i++) {
                Monster monster = monsterList.get(i);

                TextView nameText = findViewById(textViewIds[i]);
                nameText.setText(monster.name);

                ImageView imageView = findViewById(imageViewIds[i]);
                imageView.setImageResource(monster.imageResId);


                FrameLayout frameLayout = findViewById(frameLayoutIds[i]);


                frameLayout.setOnClickListener(v -> {
                    txtTen.setText("Tên: " + monster.name);
                    txtHP.setText("HP: " + monster.hp);
                    txtMana.setText("Mana: " + monster.mana);
                    txtTanCong.setText("Tấn công: " + monster.attack);
                    txtPhongNgu.setText("Phòng ngự: " + monster.defense);
 });

            }
        } else {
            Log.e(TAG, "Không nhận được monster_id từ Intent");
        }
    }
}
