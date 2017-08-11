package com.extremeplayer.quizme.Data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Abishaik on 24-07-2017.
 */

public class ChallengeBank {

    ArrayList<Question> challengeArrayList = new ArrayList<Question>();
    QuestionDbHelper mDbHelper;

    public int getLength()
    {
        return challengeArrayList.size();
    }

    public String getQuestion(int index)
    {
        return challengeArrayList.get(index).getmQuestion();
    }

    public String getChoice(int index,int num)
    {
        return challengeArrayList.get(index).getmChoices(num-1);
    }

    public String getCorrectAnswer(int index)
    {
        return challengeArrayList.get(index).getmAnswer();
    }

    public int getCorrectOption(int index)
    {
        return challengeArrayList.get(index).getmOption();
    }

    public void Questions(Context context)
    {
        mDbHelper = new QuestionDbHelper(context);
        challengeArrayList = mDbHelper.getChallengeQuestions();
    }
}
