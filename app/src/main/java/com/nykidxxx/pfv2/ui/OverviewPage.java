package com.nykidxxx.pfv2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;

import com.nykidxxx.pfv2.Adapters.CustomAdapterOverview;
import com.nykidxxx.pfv2.model.DBHandlerNY;
import com.nykidxxx.pfv2.R;
import com.nykidxxx.pfv2.model.TransactionProvider;

import static com.nykidxxx.pfv2.model.DBHandlerNY.DATABASE_VERSION;

public class OverviewPage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    CustomAdapterOverview cAdapterO;
    DBHandlerNY dbHandler;
    ListView listviewOverview;
    Button buttonGoBackFromOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_page);

        dbHandler = new DBHandlerNY(this, null, null, DATABASE_VERSION);
        listviewOverview = (ListView)findViewById(R.id.listviewOverview);

        cAdapterO = new CustomAdapterOverview(this, null, 0);
        listviewOverview.setAdapter(cAdapterO);
        
        getLoaderManager().initLoader(0, null, this);

        buttonGoBackFromOverview = (Button)findViewById(R.id.buttonGoBackFromOverview);
        buttonGoBackFromOverview.setOnClickListener(new View.OnClickListener() {
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
                                DBHandlerNY.COLUMN_PMAMOUNT,
                                DBHandlerNY.COLUMN_CMAMOUNT,
                                DBHandlerNY.COLUMN_NMAMOUNT };
        return new CursorLoader(this, TransactionProvider.CONTENT_URI_O, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cData) {
        cAdapterO.swapCursor(cData);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cAdapterO.swapCursor(null);
    }
}










