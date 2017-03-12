package com.nykidxxx.pfv2;
//Created March 6th 2017

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class HistoryPage extends AppCompatActivity {

    DBHandlerNY dbHandler;
    ListView listviewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        dbHandler = new DBHandlerNY(this, null, null, 1);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        Cursor itemCursor = db.rawQuery("SELECT * FROM transactions", null);

        listviewHistory = (ListView)findViewById(R.id.listviewHistory);

        CustomAdapter cAdapter = new CustomAdapter(this, itemCursor, 0);
        listviewHistory.setAdapter(cAdapter);

    }

}
