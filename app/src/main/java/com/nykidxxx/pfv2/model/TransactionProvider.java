package com.nykidxxx.pfv2.model;
// Created on 3/15/2017.

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import static com.nykidxxx.pfv2.model.DBHandlerNY.DATABASE_VERSION;
import static com.nykidxxx.pfv2.model.DBHandlerNY.TABLE_OVERVIEW;
import static com.nykidxxx.pfv2.model.DBHandlerNY.TABLE_TRANSACTIONS;

//A Custom Content Provider to do the database operations.
public class TransactionProvider extends ContentProvider {

    private static final String AUTHORITY = "com.nykidxxx.pfv2.model.TransactionProvider";

    public static final String TRANSACTIONS_TABLE_NAME = "transactions";
    public static final String OVERVIEW_TABLE_NAME = "overview";

    public static final Uri CONTENT_URI_T = Uri.parse("content://" + AUTHORITY + "/" + TRANSACTIONS_TABLE_NAME);
    public static final Uri CONTENT_URI_O = Uri.parse("content://" + AUTHORITY + "/" + OVERVIEW_TABLE_NAME);

    public static final int T_ITEMS = 1;
    public static final int T_ITEM_ID = 2;
    public static final int O_ITEMS = 3;
    public static final int O_ITEM_ID = 4;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static { sURIMatcher.addURI(AUTHORITY, TABLE_TRANSACTIONS, T_ITEMS);
             sURIMatcher.addURI(AUTHORITY, TABLE_TRANSACTIONS + "/#", T_ITEM_ID);
             sURIMatcher.addURI(AUTHORITY, TABLE_OVERVIEW, O_ITEMS);
             sURIMatcher.addURI(AUTHORITY, TABLE_OVERVIEW + "/#", O_ITEM_ID);
    }
    private DBHandlerNY dbHandler;
    //private SQLiteDatabase mDB;

    @Override
    public boolean onCreate() {
        dbHandler = new DBHandlerNY(getContext(), null, null, DATABASE_VERSION);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case T_ITEMS:
                queryBuilder.setTables(DBHandlerNY.TABLE_TRANSACTIONS);
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_id ASC";
                break;
            case T_ITEM_ID:
                queryBuilder.setTables(DBHandlerNY.TABLE_TRANSACTIONS);
                // no filter
                queryBuilder.appendWhere(DBHandlerNY.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                //selection = selection + "_id = " + uri.getLastPathSegment();
                break;
            case O_ITEMS:
                queryBuilder.setTables(DBHandlerNY.TABLE_OVERVIEW);
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_id ASC";
                break;
            case O_ITEM_ID:
                queryBuilder.setTables(DBHandlerNY.TABLE_OVERVIEW);
                queryBuilder.appendWhere(DBHandlerNY.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Cursor cursor = queryBuilder.query(dbHandler.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();
        int rowsAffected = 0;
        String id;

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case T_ITEMS:
                Toast.makeText(getContext(), "Warning: You have tried to delete the entire database!", Toast.LENGTH_SHORT).show();
                break;
            case T_ITEM_ID:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    rowsAffected = dbHandler.deleteTransactionFromDB(id);
                    Toast.makeText(getContext(), "Deleted item with ID# " + id, Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getContext(), "Warning: Selection not setup. Nothing deleted.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        /*int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case ITEMS:
                id = sqlDB.insert(DBHandlerNY.TABLE_TRANSACTIONS,
                        null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "
                        + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(DBHandlerNY.TABLE_TRANSACTIONS + "/" + id);*/
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
}
















