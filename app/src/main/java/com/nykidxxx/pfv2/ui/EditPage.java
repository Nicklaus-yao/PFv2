package com.nykidxxx.pfv2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.nykidxxx.pfv2.Adapters.CustomAdapter;
import com.nykidxxx.pfv2.R;
import com.nykidxxx.pfv2.model.DBHandlerNY;

public class EditPage extends AppCompatActivity {

    DBHandlerNY db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        // Get the Intent that started this activity and extract the string
        final Intent intent = getIntent();
        final String id = intent.getStringExtra(CustomAdapter.EXTRA_ID);
        final String payee = intent.getStringExtra(CustomAdapter.EXTRA_PAYEE);
        final String amount = intent.getStringExtra(CustomAdapter.EXTRA_AMOUNT);
        final String category = intent.getStringExtra(CustomAdapter.EXTRA_CATEGORY);
        final String month = intent.getStringExtra(CustomAdapter.EXTRA_MONTH);

        db = new DBHandlerNY(this, null, null, 2);

        // Capture the layout's TextViews
        EditText inputEditAmount = (EditText) findViewById(R.id.inputEditAmount);
        TextView inputEditMonth = (TextView) findViewById(R.id.inputEditMonth);

        inputEditAmount.setText(amount);
        inputEditMonth.setText(month);

        // Create and Set Spinner Adapter for Payee
        Spinner inputEditPayeeSpinner = (Spinner) findViewById(R.id.inputEditPayeeSpinner);
        ArrayAdapter<CharSequence> adapterPayees = ArrayAdapter.createFromResource(
                this, R.array.payees, R.layout.custom_pvf2_spinner_item);
        adapterPayees.setDropDownViewResource(R.layout.custom_pvf2_spinner_dropdown_item);
        inputEditPayeeSpinner.setAdapter(adapterPayees);

        if (!payee.equals(null)){
            int spinnerPositionPayee = adapterPayees.getPosition(payee);
            inputEditPayeeSpinner.setSelection(spinnerPositionPayee);
        }
        else {
            inputEditPayeeSpinner.setSelection(0);
        }

        // Create and Set Spinner Adapter for Category
        Spinner inputEditCategorySpinner = (Spinner) findViewById(R.id.inputEditCategorySpinner);
        ArrayAdapter<CharSequence> adapterCategories = ArrayAdapter.createFromResource(
                this, R.array.categories, R.layout.custom_pvf2_spinner_item);
        adapterCategories.setDropDownViewResource(R.layout.custom_pvf2_spinner_dropdown_item);
        inputEditCategorySpinner.setAdapter(adapterCategories);

        if (!category.equals(null)){
            int spinnerPositionCategory = adapterCategories.getPosition(category);
            inputEditCategorySpinner.setSelection(spinnerPositionCategory);
        }
        else {
            inputEditCategorySpinner.setSelection(0);
        }

        Button buttonDelete = (Button)findViewById(R.id.buttonDelete);
        final View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteTransaction(id);
                finish();
            }
        };
        buttonDelete.setOnClickListener(deleteListener);

    }
}
