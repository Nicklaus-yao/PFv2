package com.nykidxxx.pfv2;
//Created March 6th 2017

import static com.nykidxxx.pfv2.Constants.FIRST_COLUMN;
import static com.nykidxxx.pfv2.Constants.SECOND_COLUMN;
import static com.nykidxxx.pfv2.Constants.THIRD_COLUMN;
import static com.nykidxxx.pfv2.Constants.FOURTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.Toast;

public class HistoryPage extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;
    TextView viewHistory;
    DBHandlerNY dbHandler;
    ListView listviewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        listviewHistory = (ListView)findViewById(R.id.listviewHistory);

        list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp;
        dbHandler = new DBHandlerNY(this, null, null, 1);

        int itemCount = dbHandler.countTransactions();
        Toast.makeText(HistoryPage.this, "Total Transaction Count is " + itemCount, Toast.LENGTH_SHORT).show();

        for(int i=0; i<itemCount; i++){
            temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN, dbHandler.getSpecificData(i, "_id"));
            temp.put(SECOND_COLUMN, dbHandler.getSpecificData(i, "amount"));
            temp.put(THIRD_COLUMN, dbHandler.getSpecificData(i, "category"));
            temp.put(FOURTH_COLUMN, dbHandler.getSpecificData(i, "month"));
            list.add(temp);
        }

        CustomAdapter adapter = new CustomAdapter(this, list);
        listviewHistory.setAdapter(adapter);

        listviewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos = position + 1;
                Toast.makeText(HistoryPage.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
            }
        });

//        viewHistory = (TextView)findViewById(R.id.viewHistoryTitle);
//        dbHandler = new DBHandlerNY(this, null, null, 1);

        //ListAdapter itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
 //       ListAdapter itemsAdapter = new CustomAdapter(this, items);
 //       listviewHistory = (ListView) findViewById(R.id.listviewHistory);
 //       listviewHistory.setAdapter(itemsAdapter);

    }

    public void getSpecificData(int _id, String itemColumnName){
        String dbString = dbHandler.getSpecificData(_id, itemColumnName);
    }
}
