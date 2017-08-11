package com.extremeplayer.quizme;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.extremeplayer.quizme.Data.QuestionDbHelper;
import com.extremeplayer.quizme.Data.QuestionsContract;
import com.extremeplayer.quizme.Data.QuestionsCursorAdapter;
import com.extremeplayer.quizme.Data.QuestionsContract.QuestionsEntry;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class ChallengeActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String saved_string;
    private float saved_list[] = new float[10];
    private int i,ultimate_tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        final SharedPreferences sharedPreferences = ChallengeActivity.this.getSharedPreferences(getString(R.string.challenge_file), MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final int stored_tracker = sharedPreferences.getInt(getString(R.string.challenge_position),1);
        editor.putInt(getString(R.string.challenge_flag),1);

        saved_string = sharedPreferences.getString(getString(R.string.init_challenge_state),"");
        ultimate_tracker = sharedPreferences.getInt(getString(R.string.ultimate_tracker),1);

        StringTokenizer st = new StringTokenizer(saved_string, ",");
        for (int i = 0; i < 10; i++) {
            saved_list[i] = Float.parseFloat(st.nextToken());
            System.out.println(saved_list[i]);
        }
        editor.commit();

        recyclerView = (RecyclerView)findViewById(R.id.myRecyclerView2);
        final ArrayList<Challenge> challenges = new ArrayList<Challenge>();

        challenges.add(new Challenge("Challenge ",1));
        challenges.add(new Challenge("Challenge ",2));
        challenges.add(new Challenge("Challenge ",3));
        challenges.add(new Challenge("Challenge ",4));
        challenges.add(new Challenge("Challenge ",5));
        challenges.add(new Challenge("Challenge ",6));
        challenges.add(new Challenge("Challenge ",7));
        challenges.add(new Challenge("Challenge ",8));
        challenges.add(new Challenge("Challenge ",9));
        challenges.add(new Challenge("Challenge ",10));


        adapter = new ChallengeAdapter(saved_list,ultimate_tracker, challenges, new ChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Challenge item, int position,int tracker) {
                Intent i = new Intent(ChallengeActivity.this,ChallengeDataActivity.class);
                i.putExtra("pos",position);
                if(position>=0 && position <=ultimate_tracker) {
                    startActivity(i);
                }
                else {
                    Toast.makeText(ChallengeActivity.this, "Please clear the previous challenge(s).", Toast.LENGTH_SHORT).show();
                }
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
