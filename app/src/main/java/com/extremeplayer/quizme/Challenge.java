package com.extremeplayer.quizme;

/**
 * Created by Abishaik on 14-07-2017.
 */

public class Challenge {

    private String mString;
    private Integer mNumber;

    Challenge(String text,int number)
    {
        this.mString = text;
        this.mNumber = number;
    }

    public void setmString(String text)
    {
        mString = text;
    }

    public void setmNumber(int number)
    {
        mNumber = number;
    }

    public String getmString()
    {
        return mString;
    }

    public int getmNumber()
    {
        return mNumber;
    }

}
