package com.extremeplayer.quizme;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.extremeplayer.quizme.Data.ChallengeBank;
import com.extremeplayer.quizme.Data.QuestionBank;
import com.extremeplayer.quizme.Data.QuestionsContract;

import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import static android.R.id.edit;
import static android.R.id.list;
import static com.extremeplayer.quizme.R.id.A;
import static com.extremeplayer.quizme.R.id.D;
import static java.lang.Integer.parseInt;

public class ChallengeDataActivity extends AppCompatActivity {

    private TextView timerText;
    public int mQuestionNumber=0;
    private ChallengeBank challenge = new ChallengeBank();
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
    private float stored_res;
    private int chall_pos;
    private String saved_string;
    private float saved_list[] = new float[10];
    private int ultimate_tracker;
    private int flag,currQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_test_data);

        Bundle challPosData = getIntent().getExtras();
        if (challPosData == null)
            return;
        else {
            chall_pos = challPosData.getInt("pos");
        }

        for(i=1;i<=20;i++) {
            userAnswer[i] = null;
            correctAnswer[i] = null;
        }

        timerText = (TextView)findViewById(R.id.timer);
        timerText.setText("10:00");

        final counterClass Timer = new counterClass(600000,1000);
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
        challenge.Questions(getApplicationContext());

        SharedPreferences sharedPreferences = ChallengeDataActivity.this.getSharedPreferences(getString(R.string.challenge_file), MODE_PRIVATE);
        ultimate_tracker = sharedPreferences.getInt(getString(R.string.ultimate_tracker),1);

        saved_string = sharedPreferences.getString(getString(R.string.init_challenge_state),"");
        StringTokenizer st = new StringTokenizer(saved_string, ",");
        for (int i = 0; i < 10; i++) {
            saved_list[i] = Float.parseFloat(st.nextToken());
        }
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
            Intent i = new Intent(ChallengeDataActivity.this,ResultsActivity.class);
            Bundle b=new Bundle();
            b.putStringArray("key1",userAnswer);
            b.putStringArray("key2",correctAnswer);
            b.putInt("score",scoreCount);
            b.putInt("flag",1);
            i.putExtras(b);
            startActivity(i);
            finish();
        }
    }

    public void gotoNumber1(View view) {
        Intent i = new Intent(ChallengeDataActivity.this, NumberPopup.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = ChallengeDataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
        flag = sharedPreferences.getInt(getString(R.string.key), 0);

        if (flag == 2) {
            currQuestion = sharedPreferences.getInt(getString(R.string.popup), 1);
            mQuestionNumber = currQuestion - 1;
            updateQuestion();
        }
    }

    public void updateQuestion() {
        ImageView prevQues = (ImageView) findViewById(R.id.prevQues);
        ImageView nextQues = (ImageView) findViewById(R.id.nextQues);
        TextView currQues = (TextView) findViewById(R.id.num1);

        if (mQuestionNumber < challenge.getLength()) {

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

            questionText.setText(challenge.getQuestion(mQuestionNumber));
            firstOption.setText(challenge.getChoice(mQuestionNumber, 1));
            secondOption.setText(challenge.getChoice(mQuestionNumber, 2));
            thirdOption.setText(challenge.getChoice(mQuestionNumber, 3));
            fourthOption.setText(challenge.getChoice(mQuestionNumber, 4));
            mAnswer = challenge.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
            dispNumQues(mQuestionNumber);
            correctAnswer[parseInt(currQues.getText().toString())] = mAnswer;

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

            questionText.setText(challenge.getQuestion(mQuestionNumber - 2));
            firstOption.setText(challenge.getChoice(mQuestionNumber - 2, 1));
            secondOption.setText(challenge.getChoice(mQuestionNumber - 2, 2));
            thirdOption.setText(challenge.getChoice(mQuestionNumber - 2, 3));
            fourthOption.setText(challenge.getChoice(mQuestionNumber - 2, 4));
            mAnswer = challenge.getCorrectAnswer(mQuestionNumber - 2);
            mQuestionNumber--;
            dispNumQues(mQuestionNumber);
            correctAnswer[parseInt(currQues.getText().toString())] = mAnswer;
        }
    }

    public void dispNumQues(int num) {
        TextView currQues = (TextView) findViewById(R.id.num1);
        currQues.setText(String.valueOf(num));
        totText.setText(String.valueOf(20));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChallengeDataActivity.this);
        builder.setTitle("Finish?");
        builder.setMessage("Do you want to finish your test?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                float result = calcScore();
                //System.out.println(result + " " + chall_pos + " " + saved_list[chall_pos]);
                if(result==100){
                    SharedPreferences sharedPreferences = ChallengeDataActivity.this.getSharedPreferences(getString(R.string.challenge_file), MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt(getString(R.string.ultimate_tracker),ultimate_tracker+1);
                    editor.commit();
                }

                if(saved_list[chall_pos]<result) {
                    saved_list[chall_pos]=result;
                    saveResult();
                }

                Bundle b=new Bundle();
                b.putStringArray("key1",userAnswer);
                b.putStringArray("key2",correctAnswer);
                b.putInt("score",scoreCount);
                b.putInt("flag",1);

                Intent i = new Intent(ChallengeDataActivity.this,ResultsActivity.class);
                i.putExtras(b);
                startActivity(i);

                finish();
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.show();
    }

    private void saveResult(){
        SharedPreferences sharedPreferences = ChallengeDataActivity.this.getSharedPreferences(getString(R.string.challenge_file), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        StringBuilder str = new StringBuilder();

        for (i = 0; i < saved_list.length; i++) {
            str.append(saved_list[i]).append(",");
        }
        editor.putString(getString(R.string.init_challenge_state),str.toString());
        editor.commit();
    }

    /**public void insertIntoDb(float result) {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, questionText.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, firstOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, secondOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, thirdOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, fourthOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, mAnswer);
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION, correctOption);
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CHALLENGE_SCORE, result);

        Uri uri = getContentResolver().insert(QuestionsContract.QuestionsEntry.CONTENT_URI2, values);
    }*/

    private float calcScore()
    {
        float tempScore = scoreCount;
        float numStars = (tempScore/5) * 100;

        return numStars;
    }

    public void onOptionsClick(View view) {
        TextView currQues = (TextView) findViewById(R.id.num1);
        if (stateChanged != true) {
            TextView clickedAnswer = (TextView) view;
            String tag = clickedAnswer.getTag().toString();
            String answer = clickedAnswer.getText().toString();
            if(answer.equals(mAnswer))
                scoreCount++;

            userAnswer[parseInt(currQues.getText().toString())] = answer;
            updateYellowColour(tag);
        }
    }

    public void gotoNumber(View view) {
        Intent i = new Intent(ChallengeDataActivity.this, NumberPopup.class);
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
        final int id = ContextCompat.getColor(ChallengeDataActivity.this, R.color.test);

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
        final int id = ContextCompat.getColor(ChallengeDataActivity.this, R.color.test);

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
        final int id = ContextCompat.getColor(ChallengeDataActivity.this, R.color.goldcolor);
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
