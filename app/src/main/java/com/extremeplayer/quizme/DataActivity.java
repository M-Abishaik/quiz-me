package com.extremeplayer.quizme;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.content.ContextCompat;


import com.extremeplayer.quizme.Data.QuestionBank;
import com.extremeplayer.quizme.Data.QuestionsContract;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.color;
import static android.R.attr.id;
import static android.R.attr.privateImeOptions;
import static android.R.attr.tag;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.extremeplayer.quizme.R.id.A;
import static com.extremeplayer.quizme.R.id.D;
import static com.extremeplayer.quizme.R.id.nextQues;
import static com.extremeplayer.quizme.R.id.prevQues;
import static com.extremeplayer.quizme.R.string.currQues;
import static com.extremeplayer.quizme.R.string.firstOption;
import static com.extremeplayer.quizme.R.string.quesNum;
import static com.extremeplayer.quizme.R.string.secondOption;
import static com.extremeplayer.quizme.R.string.thirdOption;
import static com.extremeplayer.quizme.R.string.totQues;


public class DataActivity extends AppCompatActivity {

    //final ArrayList<String> numbers = new ArrayList<String>();
    final ArrayList<String> mNumbers = new ArrayList<String>();
    public int mQuestionNumber;
    int i, localflag, flag;
    private QuestionBank questionBank = new QuestionBank();
    private TextView questionText;
    private String question;
    private TextView firstOption;
    private String mFirstOption;
    private TextView aText;
    private TextView secondOption;
    private String mSecondOption;
    private TextView bText;
    private TextView thirdOption;
    private String mThirdOption;
    private TextView cText;
    private TextView fourthOption;
    private String mFourthOption;
    private TextView dText;
    private String mAnswer;
    private int correctOption;
    private boolean stateChanged = false;
    private boolean statechanged1 = false;
    private int currQuestion;
    private int totQuestion;
    private String mCorrectAnswer;
    private int mCorrectOption;
    private int count = 0;
    private TextView totText;
    private String saved_url;
    private String saved_urls[] = new String[11];
    private String current_url;
    private String titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle topicsData = getIntent().getExtras();
        if (topicsData == null)
            return;
        else {
            titleText = topicsData.getString("TopicsName");
            this.setTitle(titleText);
        }

        setContentView(R.layout.custom_questions);

        questionText = (TextView) findViewById(R.id.question);
        totText = (TextView) findViewById(R.id.num2);
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

        SharedPreferences sharedPreferences = DataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
        totQuestion = sharedPreferences.getInt(getString(R.string.totQuestion), 5);
        totText.setText(String.valueOf(totQuestion));
        questionBank.Questions(getApplicationContext(),titleText);

        saved_url = sharedPreferences.getString(getString(R.string.my_urls),"");
        StringTokenizer st1 = new StringTokenizer(saved_url, ",");
        for (int i = 0; i < 11; i++) {
            saved_urls[i] = st1.nextToken();
            //System.out.println(saved_urls[i]);
        }

        String wordString = sharedPreferences.getString(getString(R.string.question), "");
        String words[] = wordString.split(",");
        for (i = 0; i < words.length; i++)
            mNumbers.add(words[i]);

        flag = sharedPreferences.getInt(getString(R.string.key), 0);

        //if (flag == 0) {
            mQuestionNumber = 0;
            updateQuestion();
        /**else if (flag == 1 || flag == 2) {

            currQuestion = sharedPreferences.getInt(getString(R.string.currQuesNum), 1);

            if (currQuestion == 1 || currQuestion == 0) {
                prevQues.setImageResource(R.drawable.doubleleftarrow1);
                prevQues.setEnabled(false);
            }

            if (currQuestion == 5) {
                nextQues.setImageResource(R.drawable.doublearrowright1);
                nextQues.setEnabled(false);
            }

            question = sharedPreferences.getString(getString(currQues), "");
            questionText.setText(question);

            mFirstOption = sharedPreferences.getString(getString(R.string.firstOption), "");
            firstOption.setText(mFirstOption);

            mSecondOption = sharedPreferences.getString(getString(R.string.secondOption), "");
            secondOption.setText(mSecondOption);

            mThirdOption = sharedPreferences.getString(getString(R.string.thirdOption), "");
            thirdOption.setText(mThirdOption);

            mFourthOption = sharedPreferences.getString(getString(R.string.fourthOption), "");
            fourthOption.setText(mFourthOption);

            mAnswer = sharedPreferences.getString(getString(R.string.correctAnswer), "");

            correctOption = sharedPreferences.getInt(getString(R.string.correctOption), 1);

            mQuestionNumber = currQuestion;

            dispNumQues(currQuestion);

        }*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = DataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
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
            correctOption = questionBank.getCorrectOption(mQuestionNumber);
            mQuestionNumber++;
            dispNumQues(mQuestionNumber);
        }
    }

    private void decreaseQuestion() {
        ImageView prevQues = (ImageView) findViewById(R.id.prevQues);
        ImageView nextQues = (ImageView) findViewById(R.id.nextQues);


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
            correctOption = questionBank.getCorrectOption(mQuestionNumber - 2);
            mQuestionNumber--;
            dispNumQues(mQuestionNumber);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DataActivity.this);
        builder.setTitle("Quit?");
        builder.setMessage("Do you really want to quit learning?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.show();
    }

    public void dispNumQues(int num) {
        TextView currQues = (TextView) findViewById(R.id.num1);
        currQues.setText(String.valueOf(num));

        SharedPreferences sharedPreferences = DataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.currQuesNum), num);
        editor.putString(getString(R.string.currQues), questionText.getText().toString());
        editor.putString(getString(R.string.firstOption), firstOption.getText().toString());
        editor.putString(getString(R.string.secondOption), secondOption.getText().toString());
        editor.putString(getString(R.string.thirdOption), thirdOption.getText().toString());
        editor.putString(getString(R.string.fourthOption), fourthOption.getText().toString());
        editor.putInt(getString(R.string.totQuestion), 5);
        editor.putString(getString(R.string.correctAnswer), mAnswer);
        editor.putInt(getString(R.string.correctOption), correctOption);
        editor.putInt(getString(R.string.key), 1);
        editor.commit();
    }

    public void onOptionsClick(View view) {

        if (stateChanged != true) {
            TextView clickedAnswer = (TextView) view;
            String tag = clickedAnswer.getTag().toString();
            if (clickedAnswer.getText().equals(mAnswer))
                updateGreenColour(tag);
            else {
                updateRedColour(tag);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showCorrectAnswer();
                    }
                }, 500);
            }
        }
    }

    public void onForwardClick(View view) {
        final int id = ContextCompat.getColor(DataActivity.this, R.color.test);

        ImageView nextQues = (ImageView) view.findViewById(R.id.nextQues);
        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final LinearLayout linearlayout = (LinearLayout) findViewById(R.id.optionsLayout);
                linearlayout.setVisibility(View.GONE);

                aText.setBackgroundColor(id);
                bText.setBackgroundColor(id);
                cText.setBackgroundColor(id);
                dText.setBackgroundColor(id);
                stateChanged = false;

                SharedPreferences sharedPreferences = DataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.key), 1);
                editor.commit();

                updateQuestion();
            }
        });
    }

    public void onBackwardClick(View view) {
        final int id = ContextCompat.getColor(DataActivity.this, R.color.test);

        ImageView prevQues = (ImageView) view.findViewById(R.id.prevQues);
        prevQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LinearLayout linearlayout = (LinearLayout) findViewById(R.id.optionsLayout);
                linearlayout.setVisibility(View.GONE);

                aText.setBackgroundColor(id);
                bText.setBackgroundColor(id);
                cText.setBackgroundColor(id);
                dText.setBackgroundColor(id);
                stateChanged = false;

                decreaseQuestion();
            }
        });
    }

    public void gotoNumber(View view) {
        Intent i = new Intent(DataActivity.this, NumberPopup.class);
        startActivity(i);
    }

    public void Bookmark(View view) {
        TextView currQues = (TextView) findViewById(R.id.num1);
        String quesNum = currQues.getText().toString();

        for (i = 0; i < mNumbers.size(); i++)
            System.out.println(mNumbers.get(i));

        if (mNumbers.contains(quesNum))
            Toast.makeText(DataActivity.this, "Bookmark Exists", Toast.LENGTH_SHORT).show();

        else {
            addToList();
            Toast.makeText(DataActivity.this, "Bookmark Added", Toast.LENGTH_SHORT).show();
            insertIntoDb();
        }
    }


    public void insertIntoDb() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, questionText.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, firstOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, secondOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, thirdOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, fourthOption.getText().toString());
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, mAnswer);
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION, correctOption);

        Uri uri = getContentResolver().insert(QuestionsContract.QuestionsEntry.CONTENT_URI1, values);

        if (uri != null)
            Toast.makeText(DataActivity.this, "Bookmark Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(DataActivity.this, "Error in Bookmarking", Toast.LENGTH_SHORT).show();
    }

    public void addToList() {
        final TextView currQues = (TextView) findViewById(R.id.num1);
        String quesNum = currQues.getText().toString();

        if (mNumbers == null)
            mNumbers.add(quesNum);
        else {
            localflag = 0;
            if (mNumbers.contains(quesNum)) {
                localflag = 1;
            }
            if (localflag == 0)
                mNumbers.add(quesNum);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : mNumbers) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }

        SharedPreferences sharedPreferences = DataActivity.this.getSharedPreferences(getString(R.string.Shared_pref_file), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.question), stringBuilder.toString());
        editor.commit();
    }

    public void showFormula(View view) {
        ImageView formulaView = (ImageView) view.findViewById(R.id.formula);
        formulaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_url = getUrl(titleText);
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(current_url));
                startActivity(intent);
            }
        });
    }

    private String getUrl(String titleText)
    {
        String result="";
        if(titleText.equals("Number Series"))
            result = saved_urls[0];
        else if(titleText.equals("HCF & LCM"))
            result = saved_urls[1];
        else if(titleText.equals("Fractions & Decimals"))
            result = saved_urls[2];
        else if(titleText.equals("Simplification & Approximation"))
            result = saved_urls[3];
        else if(titleText.equals("Average"))
            result = saved_urls[4];
        else if(titleText.equals("Time & Distance"))
            result = saved_urls[5];
        else if(titleText.equals("Ratio & Proportion"))
            result = saved_urls[6];
        else if(titleText.equals("Trigonometry"))
            result = saved_urls[7];
        else if(titleText.equals("Volumes & Surface Area"))
            result = saved_urls[8];
        else if(titleText.equals("Pipes & Cisterns"))
            result = saved_urls[9];
        else if(titleText.equals("Alligations & Mixtures"))
            result = saved_urls[10];

        return result;
    }



    public void showOptions(View view) {
        ImageView settings = (ImageView) view.findViewById(R.id.settings);
        final LinearLayout linearlayout = (LinearLayout) findViewById(R.id.optionsLayout);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statechanged1 == false) {
                    linearlayout.setVisibility(View.VISIBLE);
                    statechanged1 = true;
                } else {
                    linearlayout.setVisibility(View.GONE);
                    statechanged1 = false;
                }
            }
        });
    }

    private void updateGreenColour(String tag) {
        final int id = ContextCompat.getColor(DataActivity.this, R.color.correctAnswer);
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

    private void updateRedColour(String tag) {
        if (tag.equals("1")) {
            aText.setBackgroundColor(Color.RED);
        } else if (tag.equals("2")) {
            bText.setBackgroundColor(Color.RED);
        } else if (tag.equals("3")) {
            cText.setBackgroundColor(Color.RED);
        } else {
            dText.setBackgroundColor(Color.RED);
        }
        stateChanged = true;
    }

    private void showCorrectAnswer() {
        final int id = ContextCompat.getColor(DataActivity.this, R.color.correctAnswer);

        if (correctOption == 1)
            aText.setBackgroundColor(id);
        else if (correctOption == 2)
            bText.setBackgroundColor(id);
        else if (correctOption == 3)
            cText.setBackgroundColor(id);
        else
            dText.setBackgroundColor(id);
    }

}
