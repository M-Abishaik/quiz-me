package com.extremeplayer.quizme.Data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.extremeplayer.quizme.R;

/**
 * Created by Abishaik on 02-07-2017.
 */
import com.extremeplayer.quizme.Data.QuestionsContract.QuestionsEntry;

public class QuestionsCursorAdapter extends CursorAdapter{


    public QuestionsCursorAdapter(Context context , Cursor c)
    {
        super(context,c,0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.custom_questions,parent,false);
        return customView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView questionText = (TextView)view.findViewById(R.id.question);
        TextView firstOption = (TextView)view.findViewById(R.id.first_option);
        TextView secondOption = (TextView)view.findViewById(R.id.second_option);
        TextView thirdOption = (TextView)view.findViewById(R.id.third_option);
        TextView fourthOption = (TextView)view.findViewById(R.id.fourth_option);


        String question = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_QUESTION));
        String option1 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_FIRST_OPTION));
        String option2 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_SECOND_OPTION));
        String option3 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_THIRD_OPTION));
        String option4 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_FOURTH_OPTION));

        questionText.setText(question);
        firstOption.setText(option1);
        secondOption.setText(option2);
        thirdOption.setText(option3);
        fourthOption.setText(option4);
    }
}
