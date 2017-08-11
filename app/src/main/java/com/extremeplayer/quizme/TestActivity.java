package com.extremeplayer.quizme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
        final ArrayList<Test> tests = new ArrayList<Test>();

            tests.add(new Test("Practice Test ",1));
            tests.add(new Test("Practice Test ",2));
            tests.add(new Test("Practice Test ",3));
            tests.add(new Test("Practice Test ",4));
            tests.add(new Test("Practice Test ",5));
            tests.add(new Test("Practice Test ",6));
            tests.add(new Test("Practice Test ",7));
            tests.add(new Test("Practice Test ",8));
            tests.add(new Test("Practice Test ",9));
            tests.add(new Test("Practice Test ",10));

        adapter = new TestAdapter(tests, new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Test item, int position) {
                String testName = null;
                Intent i = new Intent(TestActivity.this,TestDataActivity.class);

                switch (position)
                {
                    case 0: testName = "Practice Test 1";
                        break;
                    case 1: testName = "Practice Test 2";
                        break;
                    case 2: testName = "Practice Test 3";
                        break;
                    case 3: testName = "Practice Test 4";
                        break;
                    case 4: testName = "Practice Test 5";
                        break;
                    case 5: testName = "Practice Test 6";
                        break;
                    case 6: testName = "Practice Test 7";
                        break;
                    case 7: testName = "Practice Test 8";
                        break;
                    case 8: testName = "Practice Test 9";
                        break;
                    case 9: testName = "Practice Test 10";
                        break;
                }
                i.putExtra("TestName",testName);
                startActivity(i);
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
