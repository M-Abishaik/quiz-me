package com.extremeplayer.quizme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_number_popup);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .85), (int) (height * .55));

        ListAdapter numbersAdapter = new NumbersAdapter(this,getDataSet());
        GridView mListView = (GridView)findViewById(R.id.gridView);
        mListView.setAdapter(numbersAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                SharedPreferences sharedPreferences = NumberPopup.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.popup),Integer.parseInt(selectedItem));
                editor.putInt(getString(R.string.key),2);
                editor.commit();
                finish();




                //Intent i = new Intent(NumberPopup.this,GotoActivity.class);
                //startActivity(i);
                //Toast.makeText(NumberPopup.this,"You clicked " + selectedItem,Toast.LENGTH_SHORT).show();
                //DataActivity.mQuestionNumber = Integer.parseInt(selectedItem);
                //DataActivity mDataActivity = new DataActivity();
                //mDataActivity.updateQuestion();
                //finish();
            }
        });

    }

    public ArrayList<Integer> getDataSet()
    {
        ArrayList<Integer> mNumbers = new ArrayList<Integer>();
        int i;
        for(i=1;i<=5;i++)
            mNumbers.add(i);

        return mNumbers;
    }
}
