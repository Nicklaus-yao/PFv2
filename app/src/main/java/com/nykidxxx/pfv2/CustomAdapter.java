package com.nykidxxx.pfv2;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends CursorAdapter {


    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView viewID = (TextView) view.findViewById(R.id.viewID);
        TextView viewAmount=(TextView) view.findViewById(R.id.viewAmount);
        TextView viewCategory=(TextView) view.findViewById(R.id.viewCategory);
        TextView viewMonth=(TextView) view.findViewById(R.id.viewMonth);
        Button buttonDelete = (Button) view.findViewById(R.id.buttonDelete);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String amount = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
        String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
        String month = cursor.getString(cursor.getColumnIndexOrThrow("month"));

        viewID.setText(String.valueOf(id));
        viewAmount.setText(amount);
        viewCategory.setText(category);
        viewMonth.setText(month);

    }


}
