package com.extremeplayer.quizme;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.extremeplayer.quizme.Data.QuestionBank;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.extremeplayer.quizme.R.id.A;
import static com.extremeplayer.quizme.R.id.C;
import static com.extremeplayer.quizme.R.id.D;
import static com.extremeplayer.quizme.R.id.timer;
import static com.extremeplayer.quizme.R.id.view;
import static com.extremeplayer.quizme.R.string.totQuestion;


public class TestDataActivity extends AppCompatActivity {

    private TextView timerText;
    public int mQuestionNumber=0;
    private QuestionBank questionBank = new QuestionBank();
    private TextView questionText;
    private TextView firstOption;
    private TextView aText;
    private TextView secondOption;
    private TextView bText;
    private TextView thirdOption;
    private TextView cText;
    private TextView fourthOption;
    private TextView dText;
    private TextView totText;
    private String mAnswer;
    private int correctOption;
    private boolean stateChanged = false;
    private boolean statechanged1 = false;
    private int scoreCount=0,i;
    private String userAnswer[] = new String[21];
    private String correctAnswer[] = new String[21];
    private int flag,currQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle testData = getIntent().getExtras();
        if (testData == null)
            return;
        else {
            String titleText = testData.getString("TestName");
            this.setTitle(titleText);
        }
        setContentView(R.layout.activity_test_data);

        for(i=1;i<=20;i++) {
            userAnswer[i] = null;
            correctAnswer[i] = null;
        }

        timerText = (TextView)findViewById(R.id.timer);
        timerText.setText("20:00");

        final counterClass Timer = new counterClass(1200000,1000);
        Timer.start();

        questionText = (TextView) findViewById(R.id.question);
        firstOption = (TextView) findViewById(R.id.first_option);
        secondOption = (TextView) findViewById(R.id.second_option);
        thirdOption = (TextView) findViewById(R.id.third_option);
        fourthOption = (TextView) findViewById(R.id.fourth_option);
        aText = (TextView) findViewById(A);
        bText = (TextView) findViewById(R.id.B);
        cText = (TextView) findViewById(R.id.C);
        dText = (TextView) findViewById(D);
        ImageView prevQues = (ImageView) findViewById(R.id.prevQues);
        ImageView nextQues = (ImageView) findViewById(R.id.nextQues);
        totText = (TextView) findViewById(R.id.num2);
        questionBank.Questions(getApplicationContext(),"test");

        SharedPreferences sharedPreferences = TestDataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
        flag = sharedPreferences.getInt(getString(R.string.key), 0);



        updateQuestion();
    }

    public class counterClass extends CountDownTimer {

        public counterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            timerText.setText(hms);
        }

        @Override
        public void onFinish() {
            Intent i = new Intent(TestDataActivity.this,ResultsActivity.class);
            Bundle b=new Bundle();
            b.putStringArray("key1",userAnswer);
            b.putStringArray("key2",correctAnswer);
            b.putInt("score",scoreCount);
            b.putInt("flag",0);
            i.putExtras(b);
            startActivity(i);
            finish();
        }
    }

    public void updateQuestion() {
        ImageView prevQues = (ImageView) findViewById(R.id.prevQues);
        ImageView nextQues = (ImageView) findViewById(R.id.nextQues);
        TextView currQues = (TextView) findViewById(R.id.num1);

        if (mQuestionNumber < questionBank.getLength()) {

            if (mQuestionNumber == 0) {
                prevQues.setImageResource(R.drawable.doubleleftarrow1);
                prevQues.setEnabled(false);
            } else {
                prevQues.setImageResource(R.drawable.doubleleftarrow2);
                prevQues.setEnabled(true);
            }

            if (mQuestionNumber == 4) {
                nextQues.setImageResource(R.drawable.doublearrowright1);
                nextQues.setEnabled(false);
            }

            questionText.setText(questionBank.getQuestion(mQuestionNumber));
            firstOption.setText(questionBank.getChoice(mQuestionNumber, 1));
            secondOption.setText(questionBank.getChoice(mQuestionNumber, 2));
            thirdOption.setText(questionBank.getChoice(mQuestionNumber, 3));
            fourthOption.setText(questionBank.getChoice(mQuestionNumber, 4));
            mAnswer = questionBank.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
            dispNumQues(mQuestionNumber);
            correctAnswer[Integer.parseInt(currQues.getText().toString())] = mAnswer;

        }
    }

    private void decreaseQuestion() {
        ImageView prevQues = (ImageView) findViewById(R.id.prevQues);
        ImageView nextQues = (ImageView) findViewById(R.id.nextQues);
        TextView currQues = (TextView) findViewById(R.id.num1);

        if (mQuestionNumber > 1) {

            if (mQuestionNumber == 2) {
                prevQues.setImageResource(R.drawable.doubleleftarrow1);
                prevQues.setEnabled(false);
            }

            nextQues.setImageResource(R.drawable.doublerightarrow2);
            nextQues.setEnabled(true);

            questionText.setText(questionBank.getQuestion(mQuestionNumber - 2));
            firstOption.setText(questionBank.getChoice(mQuestionNumber - 2, 1));
            secondOption.setText(questionBank.getChoice(mQuestionNumber - 2, 2));
            thirdOption.setText(questionBank.getChoice(mQuestionNumber - 2, 3));
            fourthOption.setText(questionBank.getChoice(mQuestionNumber - 2, 4));
            mAnswer = questionBank.getCorrectAnswer(mQuestionNumber - 2);
            //correctOption = questionBank.getCorrectOption(mQuestionNumber - 2);
            mQuestionNumber--;
            dispNumQues(mQuestionNumber);
            correctAnswer[Integer.parseInt(currQues.getText().toString())] = mAnswer;
        }
    }

    public void gotoNumber1(View view) {
        Intent i = new Intent(TestDataActivity.this, NumberPopup.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = TestDataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
        flag = sharedPreferences.getInt(getString(R.string.key), 0);

        if (flag == 2) {
            currQuestion = sharedPreferences.getInt(getString(R.string.popup), 1);
            mQuestionNumber = currQuestion - 1;
            updateQuestion();
        }
    }

    public void dispNumQues(int num) {
        TextView currQues = (TextView) findViewById(R.id.num1);
        currQues.setText(String.valueOf(num));
        totText.setText(String.valueOf(20));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestDataActivity.this);
        builder.setTitle("Finish?");
        builder.setMessage("Do you want to finish your test?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle b=new Bundle();
                b.putStringArray("key1",userAnswer);
                b.putStringArray("key2",correctAnswer);
                b.putInt("score",scoreCount);
                b.putInt("flag",0);
                Intent i = new Intent(TestDataActivity.this,ResultsActivity.class);
                i.putExtras(b);
                startActivity(i);
                finish();
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.show();
    }

    public void onOptionsClick(View view) {
        TextView currQues = (TextView) findViewById(R.id.num1);
        if (stateChanged != true) {
            TextView clickedAnswer = (TextView) view;
            String answer = clickedAnswer.getText().toString();
            if(answer.equals(mAnswer))
                scoreCount++;

            userAnswer[Integer.parseInt(currQues.getText().toString())] = answer;
            String tag = clickedAnswer.getTag().toString();
                updateYellowColour(tag);
        }
    }

    public void gotoNumber(View view) {
        Intent i = new Intent(TestDataActivity.this, NumberPopup.class);
        startActivity(i);
    }

    public void stopTest(View view)
    {
        ImageView stopImage = (ImageView)view.findViewById(R.id.stopTest);
        stopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void onForwardClick(View view) {
        final int id = ContextCompat.getColor(TestDataActivity.this, R.color.test);

        ImageView nextQues = (ImageView) view.findViewById(R.id.nextQues);
        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aText.setBackgroundColor(id);
                bText.setBackgroundColor(id);
                cText.setBackgroundColor(id);
                dText.setBackgroundColor(id);
                stateChanged = false;

                updateQuestion();
            }
        });
    }

    public void onBackwardClick(View view) {
        final int id = ContextCompat.getColor(TestDataActivity.this, R.color.test);

        ImageView prevQues = (ImageView) view.findViewById(R.id.prevQues);
        prevQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aText.setBackgroundColor(id);
                bText.setBackgroundColor(id);
                cText.setBackgroundColor(id);
                dText.setBackgroundColor(id);
                stateChanged = false;

                decreaseQuestion();
            }
        });
    }

    private void updateYellowColour(String tag) {
        final int id = ContextCompat.getColor(TestDataActivity.this, R.color.goldcolor);
        if (tag.equals("1")) {
            aText.setBackgroundColor(id);
        } else if (tag.equals("2")) {
            bText.setBackgroundColor(id);
        } else if (tag.equals("3")) {
            cText.setBackgroundColor(id);
        } else {
            dText.setBackgroundColor(id);
        }
        stateChanged = true;
    }
}
