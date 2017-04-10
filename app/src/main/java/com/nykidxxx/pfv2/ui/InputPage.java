package com.nykidxxx.pfv2.ui;
//Created March 6th 2017

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nykidxxx.pfv2.model.DBHandlerNY;
import com.nykidxxx.pfv2.R;
import com.nykidxxx.pfv2.model.Transactions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InputPage extends AppCompatActivity implements View.OnTouchListener{

    Spinner inputPayeeSpinner;
    EditText inputAmount;
    Spinner inputCategorySpinner;
    TextView inputMonth;
    Button buttonLastMonth;
    Button buttonNextMonth;

    DBHandlerNY dbHandler;

    GregorianCalendar gCalendar;
    Date today;
    java.text.SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);

        inputAmount = (EditText)findViewById(R.id.inputAmount);
        inputMonth = (TextView)findViewById(R.id.inputMonth);
        buttonLastMonth = (Button) findViewById(R.id.buttonLastMonth);
        buttonNextMonth = (Button) findViewById(R.id.buttonNextMonth);
        //Spinner for Payee
        inputPayeeSpinner = (Spinner) findViewById(R.id.inputPayeeSpinner);
        ArrayAdapter<CharSequence> adapterPayees = ArrayAdapter.createFromResource(
                this, R.array.payees, R.layout.custom_pvf2_spinner_item);
        adapterPayees.setDropDownViewResource(R.layout.custom_pvf2_spinner_dropdown_item);
        inputPayeeSpinner.setAdapter(adapterPayees);
        //Spinner for Category
        inputCategorySpinner = (Spinner) findViewById(R.id.inputCategorySpinner);
        ArrayAdapter<CharSequence> adapterCategories = ArrayAdapter.createFromResource(
                this, R.array.categories, R.layout.custom_pvf2_spinner_item);
        adapterCategories.setDropDownViewResource(R.layout.custom_pvf2_spinner_dropdown_item);
        inputCategorySpinner.setAdapter(adapterCategories);

        dbHandler = new DBHandlerNY(this, null, null, 2);

        gCalendar = new GregorianCalendar();
        today = gCalendar.getTime();
        sdf = new java.text.SimpleDateFormat("MMMM");
        inputMonth.setText(sdf.format(today));

        //This piece of code closes the on-screen keyboard when tapped outside
        //the related EditText field.
        findViewById(R.id.activity_transaction_input).setOnTouchListener(this);
        findViewById(R.id.inputMonth).setOnTouchListener(this);

    }

    public void buttonSaveInputClicked(View view){
        if((inputCategorySpinner.getSelectedItemPosition() == 0)
                || (inputPayeeSpinner.getSelectedItemPosition() == 0)){
            Toast.makeText(this, "ALERT! No category was selected.", Toast.LENGTH_LONG).show();
        }
        else {
            Transactions transaction = new Transactions(
                    inputPayeeSpinner.getSelectedItem().toString(),
                    inputAmount.getText().toString(),
                    inputCategorySpinner.getSelectedItem().toString(),
                    inputMonth.getText().toString()
            );
            dbHandler.addTransaction(transaction);
            inputPayeeSpinner.setSelection(0);
            inputAmount.setText("");
            inputCategorySpinner.setSelection(0);
            inputMonth.setText(sdf.format(new Date()));
        }
    }

    public void buttonGoToHistoryClicked(View view){
        Intent intent = new Intent(this, HistoryPage.class);
        startActivity(intent);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void buttonNextMonthClicked(View view){
        gCalendar.add(Calendar.MONTH, 1);
        inputMonth.setText(sdf.format(gCalendar.getTime()));
    }

    public void buttonLastMonthClicked(View view){
        gCalendar.add(Calendar.MONTH, -1);
        inputMonth.setText(sdf.format(gCalendar.getTime()));
    }

}













