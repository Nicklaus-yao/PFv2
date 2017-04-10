package com.nykidxxx.pfv2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nykidxxx.pfv2.R;
import com.nykidxxx.pfv2.model.DBHandlerNY;
import com.nykidxxx.pfv2.ui.EditPage;

import static com.nykidxxx.pfv2.model.DBHandlerNY.DATABASE_VERSION;

public class CustomAdapter extends CursorAdapter {

    DBHandlerNY db;
    public static final String EXTRA_ID = "com.nykidxxx.pfv2.ID";
    public static final String EXTRA_PAYEE = "com.nykidxxx.pfv2.PAYEE";
    public static final String EXTRA_AMOUNT = "com.nykidxxx.pfv2.AMOUNT";
    public static final String EXTRA_CATEGORY = "com.nykidxxx.pfv2.CATEGORY";
    public static final String EXTRA_MONTH = "com.nykidxxx.pfv2.MONTH";

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        TextView viewPayee = (TextView) view.findViewById(R.id.viewPayee);
        TextView viewAmount = (TextView) view.findViewById(R.id.viewAmount);
        TextView viewCategory = (TextView) view.findViewById(R.id.viewCategory);
        TextView viewMonth = (TextView) view.findViewById(R.id.viewMonth);
        Button buttonEdit = (Button) view.findViewById(R.id.buttonEdit);

        db = new DBHandlerNY(context, null, null, DATABASE_VERSION);

        final String id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))+"";
        final String payee = cursor.getString(cursor.getColumnIndexOrThrow("payee"));
        final String amount = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
        final String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
        final String month = cursor.getString(cursor.getColumnIndexOrThrow("month"));

        viewPayee.setText(payee);
        viewAmount.setText(amount);
        viewCategory.setText(category);
        viewMonth.setText(month);

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Value of id is: " + id, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, EditPage.class);
                intent.putExtra(EXTRA_ID, id);
                intent.putExtra(EXTRA_PAYEE, payee);
                intent.putExtra(EXTRA_AMOUNT, amount);
                intent.putExtra(EXTRA_CATEGORY, category);
                intent.putExtra(EXTRA_MONTH, month);
                context.startActivity(intent);
            }
        };
        buttonEdit.setOnClickListener(listener);
    }

}