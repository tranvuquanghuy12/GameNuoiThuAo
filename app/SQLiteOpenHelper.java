package com.example.myapplication2;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyData.db";
    private static final int DB_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cập nhật CSDL nếu cần
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
