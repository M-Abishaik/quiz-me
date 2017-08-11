package com.extremeplayer.quizme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import static android.R.attr.width;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .85), (int) (height * .55));

        TextView description = (TextView) findViewById(R.id.description);
        description.setText("An Android application that not only challenges your aptitude skills but also provides a platform " +
                "for learning purposes.The questions under each topic are captured from various sources and detailed solutions are also provided.");
    }
}
