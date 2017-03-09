package com.nykidxxx.pfv2;
//Created March 6th 2017

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class InputPage extends AppCompatActivity {

    EditText inputAmount;
    EditText inputCategory;
    EditText inputMonth;
    DBHandlerNY dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);

        inputAmount = (EditText)findViewById(R.id.inputAmount);
        inputCategory = (EditText)findViewById(R.id.inputCategory);
        inputMonth = (EditText)findViewById(R.id.inputMonth);
        dbHandler = new DBHandlerNY(this, null, null, 1);

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM");
        inputMonth.setText(sdf.format(new Date()));
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
        inputMonth.setText("");
    }

    public void historyButtonClicked(View view){
        Intent intent = new Intent(this, HistoryPage.class);
        startActivity(intent);
    }

}













