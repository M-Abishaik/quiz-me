package com.extremeplayer.quizme;

/**
 * Created by Abishaik on 17-07-2017.
 */

public class Table {

    private int mQuestionNumber;
    private String mUserAnswer;
    private String mCorrectAnswer;

    public Table(int quesNum, String userAns, String corrAns) {
        this.mQuestionNumber = quesNum;
        this.mUserAnswer = userAns;
        this.mCorrectAnswer = corrAns;
    }

    public int getmQuestionNumber() {
        return mQuestionNumber;
    }

    public void setmQuestionNumber(int mQuesNum) {
        mQuestionNumber = mQuesNum;
    }

    public String getmUserAnswer() {
        return mUserAnswer;
    }

    public void setmUserAnswer(String useAns) {
        mUserAnswer = useAns;
    }

    public String getmCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setmCorrectAnswer(String corrAns) {
        mCorrectAnswer = corrAns;
    }
}
