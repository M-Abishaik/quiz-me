package com.extremeplayer.quizme.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Abishaik on 01-07-2017.
 */
import com.extremeplayer.quizme.Data.QuestionsContract.QuestionsEntry;

import static android.R.attr.id;
import static com.extremeplayer.quizme.R.id.B;


public class QuestionsProvider extends ContentProvider {

    private static final int QUANTZ = 100;
    private static final int BOOKMARKS = 101;
    private static final int CHALLENGES = 102;
    private static final int HCF_LCM = 103;



    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(QuestionsContract.CONTENT_AUTHORITY, QuestionsContract.PATH_QUANTZ, QUANTZ);
        sUriMatcher.addURI(QuestionsContract.CONTENT_AUTHORITY, QuestionsContract.PATH_BOOKMARKS, BOOKMARKS);
        sUriMatcher.addURI(QuestionsContract.CONTENT_AUTHORITY, QuestionsContract.PATH_CHALLENGES, CHALLENGES);
        sUriMatcher.addURI(QuestionsContract.CONTENT_AUTHORITY, QuestionsContract.PATH_HCF_LCM, HCF_LCM);
    }

    private QuestionDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new QuestionDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case QUANTZ:
                cursor = database.query(QuestionsEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;

            case BOOKMARKS:
                cursor = database.query(QuestionsEntry.TABLE_NAME1, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;

            case CHALLENGES:
                cursor = database.query(QuestionsEntry.TABLE_NAME2, projection, selection,
                        selectionArgs, null, null, sortOrder);

                break;
            case HCF_LCM:
                cursor = database.query(QuestionsEntry.TABLE_NAME3, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case QUANTZ:
                return QuestionsEntry.CONTENT_LIST_TYPE;

            case BOOKMARKS:
                return QuestionsEntry.CONTENT_LIST_TYPE1;

            case CHALLENGES:
                return QuestionsEntry.CONTENT_LIST_TYPE2;

            case HCF_LCM:
                return QuestionsEntry.CONTENT_LIST_TYPE3;

            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case QUANTZ:
                return insertQuestion(uri, values, 0);


            case BOOKMARKS:
                return insertQuestion(uri, values, 1);

            case CHALLENGES:
                return insertQuestion(uri, values, 2);

            case HCF_LCM:
                return insertQuestion(uri, values, 3);

            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertQuestion(Uri uri, ContentValues values, int flag) {
        String question = values.getAsString(QuestionsEntry.COLUMN_QUESTION);
        if (question == null)
            throw new IllegalArgumentException("Question is mandatory");

        String firstOption = values.getAsString(QuestionsEntry.COLUMN_FIRST_OPTION);
        if (firstOption == null)
            throw new IllegalArgumentException("First option is mandatory");

        String secondOption = values.getAsString(QuestionsEntry.COLUMN_SECOND_OPTION);
        if (secondOption == null)
            throw new IllegalArgumentException("Second option is mandatory");

        String thirdOption = values.getAsString(QuestionsEntry.COLUMN_THIRD_OPTION);
        if (thirdOption == null)
            throw new IllegalArgumentException("Third option is mandatory");

        String fourthOption = values.getAsString(QuestionsEntry.COLUMN_FOURTH_OPTION);
        if (fourthOption == null)
            throw new IllegalArgumentException("Fourth option is mandatory");

        String correctAnswer = values.getAsString(QuestionsEntry.COLUMN_CORRECT_ANSWER);
        if (correctAnswer == null)
            throw new IllegalArgumentException("Correct answer is mandatory");

        int correctOption = values.getAsInteger(QuestionsEntry.COLUMN_CORRECT_OPTION);

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        if (flag == 0) {
            long id = database.insert(QuestionsEntry.TABLE_NAME, null, values);
        } else if(flag==1){
            long id = database.insert(QuestionsEntry.TABLE_NAME1, null, values);
        }
        else if(flag==2){
            long id = database.insert(QuestionsEntry.TABLE_NAME2, null, values);
        }
        else {
            long id = database.insert(QuestionsEntry.TABLE_NAME3, null, values);
        }


        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case QUANTZ:
                rowsDeleted = database.delete(QuestionsEntry.TABLE_NAME, selection, selectionArgs);
                if (rowsDeleted != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsDeleted;

            case BOOKMARKS:
                rowsDeleted = database.delete(QuestionsEntry.TABLE_NAME1, selection, selectionArgs);
                if (rowsDeleted != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsDeleted;

            case CHALLENGES:
                rowsDeleted = database.delete(QuestionsEntry.TABLE_NAME2, selection, selectionArgs);
                if (rowsDeleted != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsDeleted;

            case HCF_LCM:
                rowsDeleted = database.delete(QuestionsEntry.TABLE_NAME3, selection, selectionArgs);
                if (rowsDeleted != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsDeleted;

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case QUANTZ:
                return updateQuestion(uri, values, selection, selectionArgs,0);

            case BOOKMARKS:
                return updateQuestion(uri, values, selection, selectionArgs,1);

            case CHALLENGES:
                return updateQuestion(uri, values, selection, selectionArgs,2);

            case HCF_LCM:
                return updateQuestion(uri, values, selection, selectionArgs,3);

            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateQuestion(Uri uri, ContentValues values, String selection, String[] selectionArgs,int flag) {
        if (values.containsKey(QuestionsEntry.COLUMN_QUESTION)) {
            String question = values.getAsString(QuestionsEntry.COLUMN_QUESTION);
            if (question == null)
                throw new IllegalArgumentException("Question is mandatory");
        }

        if (values.containsKey(QuestionsEntry.COLUMN_FIRST_OPTION)) {
            String firstOption = values.getAsString(QuestionsEntry.COLUMN_FIRST_OPTION);
            if (firstOption == null)
                throw new IllegalArgumentException("First option is mandatory");
        }

        if (values.containsKey(QuestionsEntry.COLUMN_SECOND_OPTION)) {
            String secondOption = values.getAsString(QuestionsEntry.COLUMN_SECOND_OPTION);
            if (secondOption == null)
                throw new IllegalArgumentException("Second option is mandatory");
        }

        if (values.containsKey(QuestionsEntry.COLUMN_THIRD_OPTION)) {
            String thirdOption = values.getAsString(QuestionsEntry.COLUMN_THIRD_OPTION);
            if (thirdOption == null)
                throw new IllegalArgumentException("Third option is mandatory");
        }

        if (values.containsKey(QuestionsEntry.COLUMN_FOURTH_OPTION)) {
            String fourthOption = values.getAsString(QuestionsEntry.COLUMN_FOURTH_OPTION);
            if (fourthOption == null)
                throw new IllegalArgumentException("Fourth option is mandatory");
        }

        if (values.containsKey(QuestionsEntry.COLUMN_CORRECT_ANSWER)) {
            String correctAnswer = values.getAsString(QuestionsEntry.COLUMN_CORRECT_ANSWER);
            if (correctAnswer == null)
                throw new IllegalArgumentException("Correct answer is mandatory");
        }

        if (values.containsKey(QuestionsEntry.COLUMN_CORRECT_OPTION)) {
            int correctOption = values.getAsInteger(QuestionsEntry.COLUMN_CORRECT_OPTION);
            /**if (correctOption != 1 || correctOption != 2 || correctOption != 3 || correctOption != 4)
                throw new IllegalArgumentException("Correct Option is mandatory");*/
        }

        if (values.size() == 0)
            return 0;

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        if(flag==0) {
            int rowsUpdated = database.update(QuestionsEntry.TABLE_NAME, values, selection, selectionArgs);
            if (rowsUpdated != 0)
                getContext().getContentResolver().notifyChange(uri, null);
            return rowsUpdated;
        }

        else if(flag==1){
            int rowsUpdated = database.update(QuestionsEntry.TABLE_NAME1, values, selection, selectionArgs);
            if (rowsUpdated != 0)
                getContext().getContentResolver().notifyChange(uri, null);
            return rowsUpdated;
        }
        else if(flag==2){
            int rowsUpdated = database.update(QuestionsEntry.TABLE_NAME2, values, selection, selectionArgs);
            if (rowsUpdated != 0)
                getContext().getContentResolver().notifyChange(uri, null);
            return rowsUpdated;
        }
        else{
            int rowsUpdated = database.update(QuestionsEntry.TABLE_NAME3, values, selection, selectionArgs);
            if (rowsUpdated != 0)
                getContext().getContentResolver().notifyChange(uri, null);
            return rowsUpdated;
        }
    }
}
