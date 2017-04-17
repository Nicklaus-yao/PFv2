package com.nykidxxx.pfv2.Adapters;
// Created on 4/15/2017


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.nykidxxx.pfv2.R;
import com.nykidxxx.pfv2.model.DBHandlerNY;

import static com.nykidxxx.pfv2.model.DBHandlerNY.DATABASE_VERSION;

public class CustomAdapterOverview extends CursorAdapter {

    DBHandlerNY db;

    public CustomAdapterOverview(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_row_overview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView viewPayee = (TextView) view.findViewById(R.id.viewPayee);
        TextView viewPrevMonthAmount = (TextView) view.findViewById(R.id.viewPrevMonthAmount);
        TextView viewCurrentMonthAmount = (TextView) view.findViewById(R.id.viewCurrentMonthAmount);
        TextView viewNextMonthAmount = (TextView) view.findViewById(R.id.viewNextMonthAmount);

        db = new DBHandlerNY(context, null, null, DATABASE_VERSION);

        final String id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))+"";
        final String payee = cursor.getString(cursor.getColumnIndexOrThrow("payee"));
        final String prevMonthAmount = cursor.getString(cursor.getColumnIndexOrThrow("pma"));
        final String currentMonthAmount = cursor.getString(cursor.getColumnIndexOrThrow("cma"));
        final String nextMonthAmount = cursor.getString(cursor.getColumnIndexOrThrow("nma"));

        viewPayee.setText(payee);
        viewPrevMonthAmount.setText(prevMonthAmount);
        viewCurrentMonthAmount.setText(currentMonthAmount);
        viewNextMonthAmount.setText(nextMonthAmount);
    }
}













