package com.nykidxxx.pfv2;

import static com.nykidxxx.pfv2.Constants.FIRST_COLUMN;
import static com.nykidxxx.pfv2.Constants.SECOND_COLUMN;
import static com.nykidxxx.pfv2.Constants.THIRD_COLUMN;
import static com.nykidxxx.pfv2.Constants.FOURTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView viewID;
    TextView viewAmount;
    TextView viewCategory;
    TextView viewMonth;

    public CustomAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){
            convertView=inflater.inflate(R.layout.custom_row, null);

            viewID = (TextView) convertView.findViewById(R.id.viewID);
            viewAmount=(TextView) convertView.findViewById(R.id.viewAmount);
            viewCategory=(TextView) convertView.findViewById(R.id.viewCategory);
            viewMonth=(TextView) convertView.findViewById(R.id.viewMonth);
        }

        HashMap<String, String> map=list.get(position);
        viewID.setText(map.get(FIRST_COLUMN));
        viewAmount.setText(map.get(SECOND_COLUMN));
        viewCategory.setText(map.get(THIRD_COLUMN));
        viewMonth.setText(map.get(FOURTH_COLUMN));

        return convertView;
    }

}

















/*============================================================================

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter {

    DBHandlerNY dbHandler;

    public CustomAdapter(Context context, String[] items) {
        super(context, R.layout.custom_row, items);
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater pfv2Inflater = LayoutInflater.from(getContext());
        View customView = convertView;
        if(customView == null)
                customView = pfv2Inflater.inflate(R.layout.custom_row, parent, false);

        String singleItem = getItem(position).toString();
        TextView viewAmount = (TextView) customView.findViewById(R.id.viewAmount);
        TextView viewCategory = (TextView) customView.findViewById(R.id.viewCategory);
        TextView viewMonth = (TextView) customView.findViewById(R.id.viewMonth);
        ImageView imageHinata = (ImageView) customView.findViewById(R.id.imageHinata);

        viewAmount.setText();
        viewCategory.setText();
        viewMonth.setText();
        imageHinata.setImageResource();

        return customView;

    }


}
*/