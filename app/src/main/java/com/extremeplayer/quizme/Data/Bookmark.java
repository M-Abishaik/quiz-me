package com.extremeplayer.quizme.Data;

import android.content.Context;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * Created by Abishaik on 10-07-2017.
 */

public class Bookmark {
    ArrayList<Question> bookmarksArrayList = new ArrayList<Question>();
    QuestionDbHelper mDbHelper;

    public int getLength()
    {
        return bookmarksArrayList.size();
    }

    public String getQuestion(int index)
    {
        return bookmarksArrayList.get(index).getmQuestion();
    }

    public String getChoice(int index,int num)
    {
        return bookmarksArrayList.get(index).getmChoices(num-1);
    }

    public String getCorrectAnswer(int index)
    {
        return bookmarksArrayList.get(index).getmAnswer();
    }

    public int getCorrectOption(int index)
    {
        return bookmarksArrayList.get(index).getmOption();
    }

    public void Questions(Context context)
    {
        mDbHelper = new QuestionDbHelper(context);
        bookmarksArrayList = mDbHelper.getBookmarks();
    }

}
