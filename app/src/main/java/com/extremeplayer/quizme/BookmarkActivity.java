package com.extremeplayer.quizme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extremeplayer.quizme.Data.Bookmark;
import com.extremeplayer.quizme.Data.QuestionBank;

import static com.extremeplayer.quizme.R.id.A;
import static com.extremeplayer.quizme.R.id.D;
import static com.extremeplayer.quizme.R.id.question;

public class BookmarkActivity extends AppCompatActivity {

    private int flag;
    private int mQuestionNumber;
    private String question;
    private Bookmark bookmark = new Bookmark();
    private TextView questionText;
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
    private TextView totText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

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

        SharedPreferences sharedPreferences = BookmarkActivity.this.getSharedPreferences(getString(R.string.bookmark_file), MODE_PRIVATE);
        totQuestion = sharedPreferences.getInt(getString(R.string.totQuestion), 5);
        totText.setText(String.valueOf(totQuestion));
        bookmark.Questions(getApplicationContext());

        flag=sharedPreferences.getInt(getString(R.string.key),0);

        if(flag==0) {
            mQuestionNumber = 0;
            updateQuestion();
        }

        else {
            currQuestion = sharedPreferences.getInt(getString(R.string.currQuesNum), 1);

            if (currQuestion == 1) {
                prevQues.setImageResource(R.drawable.doubleleftarrow1);
                prevQues.setEnabled(false);
            }

            /**if (currQuestion == 5) {
             nextQues.setImageResource(R.drawable.doublearrowright1);
             nextQues.setEnabled(false);
             }*/

            question = sharedPreferences.getString(getString(R.string.currQues), "");
            questionText.setText(question);

            mFirstOption = sharedPreferences.getString(getString(R.string.firstOption), "");
            firstOption.setText(mFirstOption);

            mSecondOption = sharedPreferences.getString(getString(R.string.secondOption), "");
            secondOption.setText(mSecondOption);

            mThirdOption = sharedPreferences.getString(getString(R.string.thirdOption), "");
            thirdOption.setText(mThirdOption);

            mFourthOption = sharedPreferences.getString(getString(R.string.fourthOption), "");
            fourthOption.setText(mFourthOption);

            mQuestionNumber = currQuestion;

            dispNumQues(currQuestion);

        }
    }

    private void updateQuestion() {
        ImageView prevQues = (ImageView) findViewById(R.id.prevQues);
        ImageView nextQues = (ImageView) findViewById(R.id.nextQues);

        if (mQuestionNumber < bookmark.getLength()) {

            if (mQuestionNumber == 0) {
                prevQues.setImageResource(R.drawable.doubleleftarrow1);
                prevQues.setEnabled(false);
            } else {
                prevQues.setImageResource(R.drawable.doubleleftarrow2);
                prevQues.setEnabled(true);
            }

            /**if (mQuestionNumber == 4) {
                nextQues.setImageResource(R.drawable.doublearrowright1);
                nextQues.setEnabled(false);
            }*/

            questionText.setText(bookmark.getQuestion(mQuestionNumber));
            firstOption.setText(bookmark.getChoice(mQuestionNumber, 1));
            secondOption.setText(bookmark.getChoice(mQuestionNumber, 2));
            thirdOption.setText(bookmark.getChoice(mQuestionNumber, 3));
            fourthOption.setText(bookmark.getChoice(mQuestionNumber, 4));
            mAnswer = bookmark.getCorrectAnswer(mQuestionNumber);
            correctOption = bookmark.getCorrectOption(mQuestionNumber);
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

            questionText.setText(bookmark.getQuestion(mQuestionNumber - 2));
            firstOption.setText(bookmark.getChoice(mQuestionNumber - 2, 1));
            secondOption.setText(bookmark.getChoice(mQuestionNumber - 2, 2));
            thirdOption.setText(bookmark.getChoice(mQuestionNumber - 2, 3));
            fourthOption.setText(bookmark.getChoice(mQuestionNumber - 2, 4));
            mAnswer = bookmark.getCorrectAnswer(mQuestionNumber - 2);
            correctOption = bookmark.getCorrectOption(mQuestionNumber - 2);
            mQuestionNumber--;
            dispNumQues(mQuestionNumber);
        }
    }

    public void dispNumQues(int num) {
        TextView currQues = (TextView) findViewById(R.id.num1);
        currQues.setText(String.valueOf(num));

        SharedPreferences sharedPreferences = BookmarkActivity.this.getSharedPreferences(getString(R.string.bookmark_file), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.currQuesNum), num);
        editor.putString(getString(R.string.currQues), questionText.getText().toString());
        editor.putString(getString(R.string.firstOption), firstOption.getText().toString());
        editor.putString(getString(R.string.secondOption), secondOption.getText().toString());
        editor.putString(getString(R.string.thirdOption), thirdOption.getText().toString());
        editor.putString(getString(R.string.fourthOption), fourthOption.getText().toString());
        editor.putInt(getString(R.string.totQuestion),5);
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
        final int id = ContextCompat.getColor(BookmarkActivity.this, R.color.test);

        ImageView nextQues = (ImageView) view.findViewById(R.id.nextQues);
        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aText.setBackgroundColor(id);
                bText.setBackgroundColor(id);
                cText.setBackgroundColor(id);
                dText.setBackgroundColor(id);
                stateChanged = false;

                SharedPreferences sharedPreferences = BookmarkActivity.this.getSharedPreferences(getString(R.string.bookmark_file), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.key),1);
                editor.commit();

                updateQuestion();
            }
        });
    }

    public void onBackwardClick(View view) {
        final int id = ContextCompat.getColor(BookmarkActivity.this, R.color.test);

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

    public void showFormula(View view)
    {
        ImageView formulaView = (ImageView)view.findViewById(R.id.formula);
        formulaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookmarkActivity.this,FormulaActivity.class);
                startActivity(i);
            }
        });
    }

    public void showOptions(final View view) {
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
        final int id = ContextCompat.getColor(BookmarkActivity.this, R.color.correctAnswer);
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
        final int id = ContextCompat.getColor(BookmarkActivity.this, R.color.correctAnswer);

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
