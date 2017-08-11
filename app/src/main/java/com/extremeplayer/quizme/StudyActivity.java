package com.extremeplayer.quizme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class StudyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        Toast.makeText(StudyActivity.this,"Please connect to the internet for good UX.",Toast.LENGTH_LONG).show();

        final SharedPreferences sharedPreferences = StudyActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        String myUrl[] = new String[11];
        for(i=0;i<11;i++){
            switch (i)
            {
                case 0: myUrl[0]= "https://www.indiabix.com/aptitude/numbers/formulas";
                    break;
                case 1: myUrl[1]= "https://www.indiabix.com/aptitude/problems-on-hcf-and-lcm/formulas";
                    break;
                case 2: myUrl[2]= "https://www.indiabix.com/aptitude/decimal-fraction/formulas";
                    break;
                case 3: myUrl[3]= "https://www.indiabix.com/aptitude/simplification/formulas";
                    break;
                case 4: myUrl[4]= "https://www.indiabix.com/aptitude/average/formulas";
                    break;
                case 5: myUrl[5]= "https://www.indiabix.com/aptitude/time-and-distance/formulas";
                    break;
                case 6: myUrl[6]= "https://www.indiabix.com/aptitude/ratio-and-proportion/formulas";
                    break;
                case 7: myUrl[7]= "https://www.indiabix.com/aptitude/area/formulas";
                    break;
                case 8: myUrl[8]= "https://www.indiabix.com/aptitude/volume-and-surface-area/formulas";
                    break;
                case 9: myUrl[9]= "https://www.indiabix.com/aptitude/pipes-and-cistern/formulas";
                    break;
                case 10: myUrl[10]= "https://www.indiabix.com/aptitude/alligation-or-mixture/formulas";
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < myUrl.length; i++) {
            sb.append(myUrl[i]).append(",");
        }

        editor.putString(getString(R.string.my_urls),sb.toString());
        editor.commit();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final ArrayList<Topics> topics = new ArrayList<Topics>();
        topics.add(new Topics("https://www.bhavinionline.com/wp-content/uploads/2015/03/Find-the-next-number-in-the-series-6-14-36-98.jpg", "Number Series"));
        topics.add(new Topics("http://www.codewithc.com/wp-content/uploads/2014/12/hcf-lcm.jpg","HCF and LCM of Numbers"));
        topics.add(new Topics("https://dryuc24b85zbr.cloudfront.net/tes/resources/6073224/image?width=500&height=500&version=1352890386000", "Fractions and Decimals"));
        topics.add(new Topics("https://pbs.twimg.com/profile_images/671435217274564608/vNZoXdrq.png", "Simplification and Approximation"));
        topics.add(new Topics("https://d30y9cdsu7xlg0.cloudfront.net/png/607382-200.png", "Average"));
        topics.add(new Topics("https://f.ptcdn.info/202/050/000/onnyyh5n2149Hg6kg1U-o.gif", "Time and Distance"));
        topics.add(new Topics("https://image.slidesharecdn.com/proportion-140127153808-phpapp01/95/proportion-and-how-to-solve-problems-in-ratio-3-638.jpg?cb=1390837216", "Ratio and Proportion"));
        topics.add(new Topics("http://www.k6-geometric-shapes.com/image-files/prism-triangle.jpg", "Trigonometry"));
        topics.add(new Topics("http://www.bbc.co.uk/staticarchive/d4d16c0b816a502280fcb3a40b3d711bfc2acc33.gif", "Volumes and Surface Area"));
        topics.add(new Topics("http://4.bp.blogspot.com/-Q34g6wAz264/VD0vFrrHGDI/AAAAAAAAVyc/rho4mgHxcSs/s1600/Pipes-Cisterns-Practice-Problems.jpg", "Pipes and Cisterns"));
        topics.add(new Topics("https://rankjunction.com/nen/image_uploads/SuperClasses/257/tests/Alligation%20or%20mixture.jpg", "Alligation and Mixtures"));

        adapter = new TopicsAdapter(StudyActivity.this,topics, new TopicsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Topics item,int position) {
                String topicsName=null;
                Intent i = new Intent(StudyActivity.this,DataActivity.class);

                switch (position)
                {
                    case 0: topicsName = "Number Series";
                              break;
                    case 1: topicsName = "HCF & LCM";
                              break;
                    case 2: topicsName = "Fractions & Decimals";
                              break;
                    case 3: topicsName = "Simplification & Approximation";
                              break;
                    case 4: topicsName = "Average";
                              break;
                    case 5: topicsName = "Time & Distance";
                              break;
                    case 6: topicsName = "Ratio & Proportion";
                              break;
                    case 7: topicsName = "Trigonometry";
                              break;
                    case 8: topicsName = "Volumes & Surface Area";
                              break;
                    case 9: topicsName = "Pipes & Cisterns";
                              break;
                    case 10: topicsName = "Alligations & Mixtures";
                              break;
                }
                i.putExtra("TopicsName",topicsName);
                startActivity(i);
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}
