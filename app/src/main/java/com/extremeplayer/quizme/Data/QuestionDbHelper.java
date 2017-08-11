package com.extremeplayer.quizme.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abishaik on 01-07-2017.
 */

import com.extremeplayer.quizme.Data.QuestionsContract.QuestionsEntry;

import java.util.ArrayList;
import java.util.Collections;

import static android.R.attr.rating;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.sql.Types.FLOAT;

public class QuestionDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 5;

    public QuestionDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_QUANTZ_TABLE = "CREATE TABLE " + QuestionsEntry.TABLE_NAME + " ("
                + QuestionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuestionsEntry.COLUMN_QUESTION + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FIRST_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_SECOND_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_THIRD_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FOURTH_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_ANSWER  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_OPTION + " INTEGER NOT NULL);";

        String SQL_CREATE_BOOKMARKS_TABLE = "CREATE TABLE " + QuestionsEntry.TABLE_NAME1 + " ("
                + QuestionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuestionsEntry.COLUMN_QUESTION + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FIRST_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_SECOND_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_THIRD_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FOURTH_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_ANSWER  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_OPTION + " INTEGER NOT NULL);";

        String SQL_CREATE_CHALLENGES_TABLE = "CREATE TABLE " + QuestionsEntry.TABLE_NAME2 + " ("
                + QuestionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuestionsEntry.COLUMN_QUESTION + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FIRST_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_SECOND_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_THIRD_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FOURTH_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_ANSWER  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_OPTION + " INTEGER NOT NULL);";

        String SQL_CREATE_HCF_LCM__TABLE = "CREATE TABLE " + QuestionsEntry.TABLE_NAME3 + " ("
                + QuestionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QuestionsEntry.COLUMN_QUESTION + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FIRST_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_SECOND_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_THIRD_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_FOURTH_OPTION  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_ANSWER  + " TEXT NOT NULL, "
                + QuestionsEntry.COLUMN_CORRECT_OPTION + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_QUANTZ_TABLE);
        db.execSQL(SQL_CREATE_BOOKMARKS_TABLE);
        db.execSQL(SQL_CREATE_CHALLENGES_TABLE);
        db.execSQL(SQL_CREATE_HCF_LCM__TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsEntry.TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsEntry.TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsEntry.TABLE_NAME3);

        onCreate(db);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionArrayList = new ArrayList<Question>();

        String selectQuery = "SELECT * FROM " + QuestionsEntry.TABLE_NAME;

        questionArrayList=fetchFromDb(selectQuery);
        return questionArrayList;
    }

    public ArrayList<Question> getHCFLCMQuestions() {
        ArrayList<Question> questionArrayList = new ArrayList<Question>();

        String selectQuery = "SELECT * FROM " + QuestionsEntry.TABLE_NAME3;

        questionArrayList=fetchFromDb(selectQuery);
        return questionArrayList;
    }

    public ArrayList<Question>getBookmarks() {
        ArrayList<Question> bookmarksArrayList = new ArrayList<Question>();

        String selectQuery = "SELECT DISTINCT " + QuestionsEntry.COLUMN_QUESTION + ", " + QuestionsEntry.COLUMN_FIRST_OPTION + ", "
                                + QuestionsEntry.COLUMN_SECOND_OPTION + ", " + QuestionsEntry.COLUMN_THIRD_OPTION + ", "
                                + QuestionsEntry.COLUMN_FOURTH_OPTION + ", " + QuestionsEntry.COLUMN_CORRECT_ANSWER + ", "
                                + QuestionsEntry.COLUMN_CORRECT_OPTION  + " FROM " + QuestionsEntry.TABLE_NAME1;

        bookmarksArrayList = fetchFromDb(selectQuery);
        return bookmarksArrayList;
    }

    public ArrayList<Question> getChallengeQuestions() {
        ArrayList<Question> challengeArrayList = new ArrayList<Question>();

        String selectQuery = "SELECT * FROM " + QuestionsEntry.TABLE_NAME;

        challengeArrayList=fetchFromDb(selectQuery);
        return challengeArrayList;
    }


    public ArrayList<Question>fetchFromDb(String selectQuery)
    {
        ArrayList<Question>mArrayList = new ArrayList<Question>();

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do
            {
                Question question = new Question();

                String questionText = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_QUESTION));
                question.setmQuestion(questionText);

                String firstOption = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_FIRST_OPTION));
                question.setmChoice(0,firstOption);

                String secondOption = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_SECOND_OPTION));
                question.setmChoice(1,secondOption);

                String thirdOption = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_THIRD_OPTION));
                question.setmChoice(2,thirdOption);

                String fourthOption = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_FOURTH_OPTION));
                question.setmChoice(3,fourthOption);

                String correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_CORRECT_ANSWER));
                question.setmAnswer(correctAnswer);

                int correctOption = cursor.getInt(cursor.getColumnIndexOrThrow(QuestionsEntry.COLUMN_CORRECT_OPTION));
                question.setmOption(correctOption);

                mArrayList.add(question);
            }while(cursor.moveToNext());
            Collections.shuffle(mArrayList);
        }
        return mArrayList;
    }
}
