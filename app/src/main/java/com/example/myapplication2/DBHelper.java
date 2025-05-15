package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "monster.db";
    private static final int DB_VERSION = 6;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Monsters
        db.execSQL("CREATE TABLE Monsters (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "imageResId INTEGER," +
                "hp INTEGER," +
                "mana INTEGER," +
                "attack INTEGER," +
                "defense INTEGER)");

        // Thêm dữ liệu mẫu
        db.execSQL("INSERT INTO Monsters (name, imageResId, hp, mana, attack, defense) " +
                "VALUES ('Goblin', " + R.drawable.goblin + ", 100, 40, 15, 5)");

        db.execSQL("INSERT INTO Monsters (name, imageResId, hp, mana, attack, defense) " +
                "VALUES ('Dragon', " + R.drawable.dragon + ", 120, 50, 20, 15)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Monsters");
        onCreate(db);
    }

    // ✅ Hàm lấy Monster theo ID
    public Monster getMonsterById(int monsterId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Monsters WHERE id = ?", new String[]{String.valueOf(monsterId)});

        Monster monster = null;
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow("imageResId"));
            int hp = cursor.getInt(cursor.getColumnIndexOrThrow("hp"));
            int mana = cursor.getInt(cursor.getColumnIndexOrThrow("mana"));
            int attack = cursor.getInt(cursor.getColumnIndexOrThrow("attack"));
            int defense = cursor.getInt(cursor.getColumnIndexOrThrow("defense"));

            monster = new Monster(id, name, imageResId, hp, mana, attack, defense);
            cursor.close();
        }

        return monster;
    }

    public List<Monster> getFirstNMonsters(int limit) {
        List<Monster> monsters = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Monsters LIMIT ?", new String[]{String.valueOf(limit)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow("imageResId"));
                int hp = cursor.getInt(cursor.getColumnIndexOrThrow("hp"));
                int mana = cursor.getInt(cursor.getColumnIndexOrThrow("mana"));
                int attack = cursor.getInt(cursor.getColumnIndexOrThrow("attack"));
                int defense = cursor.getInt(cursor.getColumnIndexOrThrow("defense"));

                monsters.add(new Monster(id, name, imageResId, hp, mana, attack, defense));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return monsters;
    }


    public void addMonster(Monster monster) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", monster.name);
        values.put("imageResId", monster.imageResId);
        values.put("hp", monster.hp);
        values.put("mana", monster.mana);
        values.put("attack", monster.attack);
        values.put("defense", monster.defense);
        db.insert("Monsters", null, values);
        db.close();
    }


}
