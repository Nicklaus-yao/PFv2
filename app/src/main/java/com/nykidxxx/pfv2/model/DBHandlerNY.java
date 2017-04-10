package com.nykidxxx.pfv2.model;
//Created March 6th 2017

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class DBHandlerNY extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "transactions.db";
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PAYEE = "payee";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_MONTH = "month";
    private SQLiteDatabase mDB;

    public DBHandlerNY(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_TRANSACTIONS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAYEE + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_MONTH + " TEXT " +
                ")";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        onCreate(db);
    }

    //Add a new row of information into the database
    public void addTransaction(Transactions transaction){
        ContentValues cValues = new ContentValues();
        cValues.put(COLUMN_PAYEE, transaction.get_payee());
        cValues.put(COLUMN_AMOUNT, transaction.get_amount());
        cValues.put(COLUMN_CATEGORY, transaction.get_category());
        cValues.put(COLUMN_MONTH, transaction.get_month());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TRANSACTIONS, null, cValues);
        db.close();
    }

    //Delete a row from the database
    public int deleteTransaction (String _id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_TRANSACTIONS, COLUMN_ID + "=" + _id, null);
    }

    //Return a cursor with all data
    public Cursor createCursor() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor itemCursor = db.rawQuery("SELECT * FROM " + TABLE_TRANSACTIONS, null);
        return itemCursor;
    }

    public int countTransactions(){
        int itemCount;
        SQLiteDatabase db = getWritableDatabase();
        String sqlQuery = "SELECT COUNT("+COLUMN_ID+") AS countColumn FROM " + TABLE_TRANSACTIONS;
        Cursor c = db.rawQuery(sqlQuery, null);
        c.moveToFirst();

        itemCount = c.getInt(c.getColumnIndex("countColumn"));
        c.close();

        db.close();
        return itemCount;
    }

    public String getSpecificData(int _id, String itemColumnName){
        String specificData = "";
        SQLiteDatabase db = getWritableDatabase();

        String sqlQuery = "SELECT * FROM " + TABLE_TRANSACTIONS +
                          " WHERE " + COLUMN_ID + "=" + _id;

        Cursor c = db.rawQuery(sqlQuery, null);
        if(c !=null && c.moveToFirst())
            specificData = c.getString(c.getColumnIndex(itemColumnName));
        c.close();

        db.close();
        return specificData;
    }

}

















