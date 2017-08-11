package com.extremeplayer.quizme;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.extremeplayer.quizme.R.id.listView;

public class ResultsActivity extends AppCompatActivity {

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle b=this.getIntent().getExtras();
        String userAnswer[] = b.getStringArray("key1");
        String correctAnswer[] = b.getStringArray("key2");
        int score = b.getInt("score");
        int flag = b.getInt("flag");

        if(flag==0)
            Toast.makeText(ResultsActivity.this,"Score: " + score +" /20.",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ResultsActivity.this,"Score: " + score +" /10.",Toast.LENGTH_LONG).show();

        final ListView listView = (ListView) findViewById(R.id.tableList);
        final ArrayList<Table> tableRows = new ArrayList<Table>();

        for(i=1;i<=5;i++)
            tableRows.add(new Table(i,userAnswer[i],correctAnswer[i]));

        ResultsAdapter resultsAdapter = new ResultsAdapter(ResultsActivity.this,tableRows);
        listView.setAdapter(resultsAdapter);
    }
}
