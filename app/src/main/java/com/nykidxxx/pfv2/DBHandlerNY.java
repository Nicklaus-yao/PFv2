package com.nykidxxx.pfv2;
//Created March 6th 2017

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.support.v7.widget.ScrollingTabContainerView;

public class DBHandlerNY extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "transactions.db";
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COLUMN_ID = "_id";
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

//TODO: Temporary code to test with, come back and make a proper display using custom list rows
    // Method to print the database as a string to allow us to display it
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE 1 ";// + COLUMN_ID + "=" + _id;

        Cursor c = db.rawQuery(sqlQuery, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("amount"))!=null){
                dbString += c.getString(c.getColumnIndex("amount"));
                dbString += " \t ";
                dbString += c.getString(c.getColumnIndex("category"));
                dbString += " \t ";
                dbString += c.getString(c.getColumnIndex("month"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        c.close();

        db.close();
        return dbString;
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

    // Method to return one row of data
    public String returnRow(int _id){
        String dbRow = "";
        SQLiteDatabase db = getWritableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE " + COLUMN_ID + "=" + _id;

        Cursor c = db.rawQuery(sqlQuery, null);
        c.moveToFirst();

        if(c.getString(c.getColumnIndex("amount"))!=null){
            dbRow += c.getString(c.getColumnIndex("amount"));
            dbRow += " \t ";
            dbRow += c.getString(c.getColumnIndex("category"));
            dbRow += " \t ";
            dbRow += c.getString(c.getColumnIndex("month"));
            dbRow += "\n";
        }
        c.close();

        db.close();
        return dbRow;
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

















