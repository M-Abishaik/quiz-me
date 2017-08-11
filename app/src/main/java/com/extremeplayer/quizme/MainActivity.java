package com.extremeplayer.quizme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.list;
import static com.extremeplayer.quizme.R.id.Challenge;
import static com.extremeplayer.quizme.R.id.challengeText;


public class MainActivity extends AppCompatActivity {


    private int i,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView studyText = (TextView) findViewById(R.id.Study);
        studyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StudyActivity.class);
                startActivity(i);
            }
        });

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.challenge_file), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        flag = sharedPreferences.getInt(getString(R.string.challenge_flag),0);

        if(flag==0){
            float list[] = new float[10];
            StringBuilder str = new StringBuilder();

            for (i = 0; i < list.length; i++) {
                list[i]=0;
                str.append(list[i]).append(",");
            }
            editor.putString(getString(R.string.init_challenge_state),str.toString());
            editor.commit();
        }

        final TextView challengeText = (TextView) findViewById(Challenge);
        challengeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChallengeActivity.class);
                startActivity(i);
            }
        });

        TextView reviewText = (TextView) findViewById(R.id.Test);
        reviewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TestActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BookmarkActivity.class);
                startActivity(intent);
            }
        });


        TextView dbInsertion = (TextView)findViewById(R.id.Db);
        dbInsertion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,InsertionActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void aboutMe(MenuItem item) {
        Intent i = new Intent(MainActivity.this, PopupActivity.class);
        startActivity(i);
    }
}
