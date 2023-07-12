package com.example.alphaapp;




import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Android.db";
    private static final String TABLE_NAME = "Alphabets";
    private static final String COLUMN_INDEX = "ind";
    private static final String COLUMN_ANS = "answer";

    private static final String COLUMN_CORRECT = "correct";

    private static final String COLUMN_QUES = "question";





    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_INDEX + " INTEGER,"
                + COLUMN_CORRECT + " TEXT,"
                + COLUMN_QUES + " TEXT,"
                + COLUMN_ANS + " TEXT"
                + ")";
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(String ans, String correct, String ques){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CORRECT, correct);
        values.put(COLUMN_ANS, ans);
        values.put(COLUMN_QUES, ques);


        db.insert(TABLE_NAME, null, values);
    }

    public List<String> getResult(){
        List<String> answers = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToLast()) {


            @SuppressLint("Range") String ans = cursor.getString(cursor.getColumnIndex(COLUMN_ANS));
            @SuppressLint("Range") String correct = cursor.getString(cursor.getColumnIndex(COLUMN_CORRECT));
            @SuppressLint("Range") String ques = cursor.getString(cursor.getColumnIndex(COLUMN_QUES));

            answers.add(ans);
            answers.add(correct);
            answers.add(ques);


        }

        cursor.close();
        db.close();

        return answers;
    }

    public List<String> getfiveResult(){
        int count = 0;
        List<String> answers = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToLast()) {

            do {
                count+=1;
                @SuppressLint("Range") String ans = cursor.getString(cursor.getColumnIndex(COLUMN_ANS));
                @SuppressLint("Range") String correct = cursor.getString(cursor.getColumnIndex(COLUMN_CORRECT));
                @SuppressLint("Range") String ques = cursor.getString(cursor.getColumnIndex(COLUMN_QUES));

                answers.add(ans);
                answers.add(correct);
                answers.add(ques);

            }while (cursor.moveToPrevious() && count<3);
        }






 
            @Override
            public void onCreate(SQLiteDatabase db) {
                String createTableQuery = "CREATE TABLE " + TABLE_RESULTS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_QUESTIONS + " TEXT, " +
                        COLUMN_SELECTIONS + " TEXT, " +
                        COLUMN_CORRECT_ANSWERS + " TEXT, " +
                        COLUMN_SCORE + " INTEGER)";
                db.execSQL(createTableQuery);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
                onCreate(db);
            }

            public void addTestResult(String questions, String selections, String correctAnswers, int score) {
                SQLiteDatabase db = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(COLUMN_QUESTIONS, questions);
                values.put(COLUMN_SELECTIONS, selections);
                values.put(COLUMN_CORRECT_ANSWERS, correctAnswers);
                values.put(COLUMN_SCORE, score);
                db.insert(TABLE_RESULTS, null, values);
                db.close();
            }

            public Cursor getAllResults() {
                SQLiteDatabase db = getReadableDatabase();
                return db.rawQuery("SELECT * FROM " + TABLE_RESULTS, null);
            }
        }


        cursor.close();
        db.close();

        return answers;
    }
}


