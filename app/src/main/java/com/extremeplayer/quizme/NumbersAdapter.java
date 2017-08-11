package com.extremeplayer.quizme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.button;
import static java.security.AccessController.getContext;

/**
 * Created by Abishaik on 12-07-2017.
 */

public class NumbersAdapter extends ArrayAdapter<Integer>{

    private ArrayList<Integer> mArrayList;

    public NumbersAdapter(Context context, ArrayList<Integer> arrayList) {
        super(context, R.layout.custom_numbers,arrayList);
        this.mArrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater xmlInflator = LayoutInflater.from(getContext());
        View customView = xmlInflator.inflate(R.layout.custom_numbers,parent,false);

        int number = getItem(position);
        TextView singleText = (TextView)customView.findViewById(R.id.numbers);

        singleText.setText(String.valueOf(number));
        return customView;
    }
}
