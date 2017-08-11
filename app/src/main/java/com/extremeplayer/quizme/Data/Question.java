package com.extremeplayer.quizme.Data;

/**
 * Created by Abishaik on 02-07-2017.
 */

public class Question {

    private String mQuestion;
    private String mChoice[] = new String[4];
    private String mAnswer;
    private int mOption;

    public Question() {}

    public Question(String question,String choices[],String answer,int correctOption,float rating)
    {
        this.mQuestion=question;
        this.mChoice[0]=choices[0];
        this.mChoice[1]=choices[1];
        this.mChoice[2]=choices[2];
        this.mChoice[3]=choices[3];
        this.mAnswer=answer;
        this.mOption = correctOption;
    }

    public void setmQuestion(String question)
    {
        mQuestion=question;
    }

    public void setmAnswer(String answer)
    {
        mAnswer=answer;
    }

    public void setmChoice(int i,String choice)
    {
        mChoice[i]=choice;
    }

    public void setmOption(int option)
    {
        mOption = option;
    }

    public String getmQuestion()
    {
        return mQuestion;
    }

    public String getmChoices(int i)
    {
        return mChoice[i];
    }

    public String getmAnswer()
    {
        return mAnswer;
    }

    public int getmOption()
    {
        return mOption;
    }
}
