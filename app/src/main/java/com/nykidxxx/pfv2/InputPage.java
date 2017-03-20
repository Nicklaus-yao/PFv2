package com.nykidxxx.pfv2;
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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InputPage extends AppCompatActivity implements View.OnTouchListener{

    EditText inputAmount;
    TextView inputMonth;
    Button buttonLastMonth;
    Button buttonNextMonth;
    Spinner spinCategories;
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
        dbHandler = new DBHandlerNY(this, null, null, 1);

        gCalendar = new GregorianCalendar();
        today = gCalendar.getTime();
        sdf = new java.text.SimpleDateFormat("MMMM");
        inputMonth.setText(sdf.format(today));

        findViewById(R.id.activity_transaction_input).setOnTouchListener(this);
        findViewById(R.id.inputMonth).setOnTouchListener(this);

        //Spinner
        spinCategories = (Spinner) findViewById(R.id.spinCategories);
        ArrayAdapter<CharSequence> adapterCategories = ArrayAdapter.createFromResource(
                this, R.array.categories, android.R.layout.simple_spinner_dropdown_item
        );
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategories.setAdapter(adapterCategories);
    }

    public void buttonSaveInputClicked(View view){
        Transactions transaction = new Transactions(
                inputAmount.getText().toString(),
                spinCategories.getSelectedItem().toString(),
                inputMonth.getText().toString()
        );
        dbHandler.addTransaction(transaction);
        inputAmount.setText("");
        inputMonth.setText(sdf.format(new Date()));
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













