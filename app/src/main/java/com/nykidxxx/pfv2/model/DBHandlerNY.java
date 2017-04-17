package com.nykidxxx.pfv2.model;
//Created March 6th 2017

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import com.nykidxxx.pfv2.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DBHandlerNY extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "pvf2.db";

    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PAYEE = "payee";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_MONTH = "month";

    public static final String TABLE_OVERVIEW = "overview";
    public static final String COLUMN_PMAMOUNT = "pma";
    public static final String COLUMN_CMAMOUNT = "cma";
    public static final String COLUMN_NMAMOUNT = "nma";

    GregorianCalendar gCalendar;
    Date today;
    java.text.SimpleDateFormat sdf;

    public DBHandlerNY(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQueryTransactions = "CREATE TABLE " + TABLE_TRANSACTIONS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAYEE + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_MONTH + " TEXT " +
                ")";
        db.execSQL(sqlQueryTransactions);

        String sqlQueryOverview = "CREATE TABLE " + TABLE_OVERVIEW + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAYEE + " TEXT, " +
                COLUMN_PMAMOUNT + " TEXT, " +
                COLUMN_CMAMOUNT + " TEXT, " +
                COLUMN_NMAMOUNT + " TEXT " +
                ")";
        db.execSQL(sqlQueryOverview);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OVERVIEW);
        onCreate(db);
    }

    //Add a new row of information into the database
    public long addTransaction(Transactions transaction){
        ContentValues cValues = new ContentValues();
        cValues.put(COLUMN_PAYEE, transaction.get_payee());
        cValues.put(COLUMN_AMOUNT, transaction.get_amount());
        cValues.put(COLUMN_CATEGORY, transaction.get_category());
        cValues.put(COLUMN_MONTH, transaction.get_month());

        SQLiteDatabase db = getWritableDatabase();
        long a = db.insert(TABLE_TRANSACTIONS, null, cValues);
        db.close();
        return a;
    }

    public void updateOverview(String mPayee, String pMonth, String cMonth, String nMonth){
        String pmAmount = "--";
        String cmAmount = "--";
        String nmAmount = "--";

        SQLiteDatabase db = getWritableDatabase();
        String sqlQueryPM = "SELECT " + COLUMN_AMOUNT + " FROM " + TABLE_TRANSACTIONS +
                " WHERE " + COLUMN_PAYEE + "=\"" + mPayee +
                  "\" AND " + COLUMN_MONTH + "=\"" + pMonth + "\"";
        String sqlQueryCM = "SELECT " + COLUMN_AMOUNT + " FROM " + TABLE_TRANSACTIONS +
                " WHERE " + COLUMN_PAYEE + "=\"" + mPayee +
                "\" AND " + COLUMN_MONTH + "=\"" + cMonth + "\"";
        String sqlQueryNM = "SELECT " + COLUMN_AMOUNT + " FROM " + TABLE_TRANSACTIONS +
                " WHERE " + COLUMN_PAYEE + "=\"" + mPayee +
                "\" AND " + COLUMN_MONTH + "=\"" + nMonth + "\"";

        Cursor cPM = db.rawQuery(sqlQueryPM, null);
        if(cPM !=null && cPM.moveToFirst())
            pmAmount = cPM.getString(cPM.getColumnIndex("amount"));
        cPM.close();
        Cursor cCM = db.rawQuery(sqlQueryCM, null);
        if(cCM !=null && cCM.moveToFirst())
            cmAmount = cCM.getString(cCM.getColumnIndex("amount"));
        cCM.close();
        Cursor cNM = db.rawQuery(sqlQueryNM, null);
        if(cNM !=null && cNM.moveToFirst())
            nmAmount = cNM.getString(cPM.getColumnIndex("amount"));
        cNM.close();

        ContentValues cValues = new ContentValues();
        cValues.put(COLUMN_PMAMOUNT, pmAmount);
        cValues.put(COLUMN_CMAMOUNT, cmAmount);
        cValues.put(COLUMN_NMAMOUNT, nmAmount);

        long a = db.update(TABLE_OVERVIEW, cValues, COLUMN_PAYEE + "=\"" + mPayee + "\"", null);

        db.close();
    }

    //Delete a row from the database
    public int deleteTransactionFromDB (String _id){
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

















