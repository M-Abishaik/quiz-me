package com.extremeplayer.quizme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.extremeplayer.quizme.R.style.imageView;

/**
 * Created by Abishaik on 17-07-2017.
 */

public class ResultsAdapter extends ArrayAdapter<Table> {

    private Context mContext;
    private ArrayList<Table> mTables;

    public ResultsAdapter(Context context, ArrayList<Table> tables)
    {
        super(context, R.layout.custom_results,tables);
        this.mContext=context;
        this.mTables=tables;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_results,parent,false);

        final TextView quesNumText = (TextView)customView.findViewById(R.id.customQuesNumber);
        final TextView userAnswerText = (TextView)customView.findViewById(R.id.customMyAnswer);
        final TextView correctAnswerText = (TextView)customView.findViewById(R.id.customCorrectAnswer);
        final ImageView imageView = (ImageView)customView.findViewById(R.id.customAnswer);

        final Table obj = getItem(position);
        int mQuestionNumber = obj.getmQuestionNumber();
        String userAnswer = obj.getmUserAnswer();
        String correctAnswer = obj.getmCorrectAnswer();

        quesNumText.setText(String.valueOf(mQuestionNumber));
        userAnswerText.setText(userAnswer);
        correctAnswerText.setText(correctAnswer);


        if(userAnswer==null || !(userAnswer.equals(correctAnswer)))
            imageView.setImageResource(R.drawable.cancelmark);

        else
            imageView.setImageResource(R.drawable.checkedsymbol);

        return customView;
    }
}