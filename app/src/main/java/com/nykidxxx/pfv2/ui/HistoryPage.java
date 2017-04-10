package com.nykidxxx.pfv2.ui;
//Created March 6th 2017

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;

import com.nykidxxx.pfv2.Adapters.CustomAdapter;
import com.nykidxxx.pfv2.model.DBHandlerNY;
import com.nykidxxx.pfv2.R;
import com.nykidxxx.pfv2.model.TransactionProvider;

public class HistoryPage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    CustomAdapter cAdapter;
    DBHandlerNY dbHandler;
    ListView listviewHistory;
    Button buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history_page);
        dbHandler = new DBHandlerNY(this, null, null, 1);

        listviewHistory = (ListView)findViewById(R.id.listviewHistory);
        cAdapter = new CustomAdapter(this, null, 0);
        listviewHistory.setAdapter(cAdapter);

        getLoaderManager().initLoader(0, null, this);

        buttonGoBack = (Button)findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = { DBHandlerNY.COLUMN_ID,
                                DBHandlerNY.COLUMN_PAYEE,
                                DBHandlerNY.COLUMN_AMOUNT,
                                DBHandlerNY.COLUMN_CATEGORY,
                                DBHandlerNY.COLUMN_MONTH };
        return new CursorLoader(this, TransactionProvider.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cData) {
        cAdapter.swapCursor(cData);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cAdapter.swapCursor(null);
    }
}










