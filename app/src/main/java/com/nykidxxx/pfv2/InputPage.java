package com.nykidxxx.pfv2;
//Created March 6th 2017

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Date;

public class InputPage extends AppCompatActivity implements View.OnTouchListener{

    EditText inputAmount;
    EditText inputCategory;
    EditText inputMonth;
    DBHandlerNY dbHandler;
    java.text.SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);

        inputAmount = (EditText)findViewById(R.id.inputAmount);
        inputCategory = (EditText)findViewById(R.id.inputCategory);
        inputMonth = (EditText)findViewById(R.id.inputMonth);
        dbHandler = new DBHandlerNY(this, null, null, 1);

        sdf = new java.text.SimpleDateFormat("MMMM");
        inputMonth.setText(sdf.format(new Date()));

        findViewById(R.id.activity_transaction_input).setOnTouchListener(this);
    }

    public void saveButtonClicked(View view){
        Transactions transaction = new Transactions(
                inputAmount.getText().toString(),
                inputCategory.getText().toString(),
                inputMonth.getText().toString()
        );
        dbHandler.addTransaction(transaction);
        inputAmount.setText("");
        inputCategory.setText("");
        inputMonth.setText(sdf.format(new Date()));
    }

    public void historyButtonClicked(View view){
        Intent intent = new Intent(this, HistoryPage.class);
        startActivity(intent);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

}













