package com.extremeplayer.quizme.Data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Abishaik on 03-07-2017.
 */

public class QuestionBank {

    ArrayList<Question> mArrayList = new ArrayList<Question>();
    QuestionDbHelper mDbHelper;

    public int getLength()
    {
        return mArrayList.size();
    }

    public String getQuestion(int index)
    {
        return mArrayList.get(index).getmQuestion();
    }

    public String getChoice(int index,int num)
    {
        return mArrayList.get(index).getmChoices(num-1);
    }

    public String getCorrectAnswer(int index)
    {
        return mArrayList.get(index).getmAnswer();
    }

    public int getCorrectOption(int index)
    {
        return mArrayList.get(index).getmOption();
    }

    public void Questions(Context context,String titleText)
    {
        mDbHelper = new QuestionDbHelper(context);

        if(titleText.equals("Number Series"))
            mArrayList = mDbHelper.getAllQuestions();

        else if(titleText.equals("HCF & LCM"))
            mArrayList = mDbHelper.getHCFLCMQuestions();

        else
            mArrayList = mDbHelper.getAllQuestions();


        /**else if(titleText.equals("Fractions & Decimals"))
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
            result = saved_urls[10];*/



    }
}
