package com.extremeplayer.quizme;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.extremeplayer.quizme.Data.QuestionDbHelper;
import com.extremeplayer.quizme.Data.QuestionsContract;
import com.extremeplayer.quizme.Data.QuestionsCursorAdapter;

public class InsertionActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int QUANTZ_LOADER = 0;
    QuestionsCursorAdapter mCursorAdapter;
    private QuestionDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mCursorAdapter = new QuestionsCursorAdapter(this, null);
        ListView questionsView = (ListView) findViewById(R.id.listView);
        questionsView.setAdapter(mCursorAdapter);

        getSupportLoaderManager().initLoader(QUANTZ_LOADER, null, this);
    }

    private void insertFinalRow(ContentValues values) {
        Uri uri = getContentResolver().insert(QuestionsContract.QuestionsEntry.CONTENT_URI, values);
    }

    private void insertFinalRow1(ContentValues values) {
        Uri uri = getContentResolver().insert(QuestionsContract.QuestionsEntry.CONTENT_URI3, values);
    }

    private void insertRow1() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "Find the odd one out among the following: " + "\n" + "54,9,15,6,24,4,16.");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "24");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "16");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "6");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "15");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "15");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,4);
        insertFinalRow(values);
    }

    private void insertRow2() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "Find the next number: " + "\n" + "2,7,28,63,126");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "196");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "215");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "217");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "225");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "215");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,2);
        insertFinalRow(values);
    }

    private void insertRow3() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "Find the next number: " + "\n" + "2,10,30,68");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "90");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "120");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "125");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "130");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "130");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,4);
        insertFinalRow(values);
    }

    private void insertRow4() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "Find the odd one out among the following: " + "\n" + "696,340,168,80,36,14,3");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "340");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "80");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "3");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "36");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "340");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,1);
        insertFinalRow(values);
    }

    private void insertRow5() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "Find the next number " + "\n" + "2,9,28,65,126");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "218");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "217");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "216");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "215");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "217");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,2);
        insertFinalRow(values);
    }

    private void insertRow6() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION,"Find the greatest number that will divide 43, 91 and 183 so as to leave the same remainder in each case.");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "4");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "7");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "9");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "13");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "4");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,1);
        insertFinalRow1(values);
    }

    private void insertRow7() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "The H.C.F. of two numbers is 23 and the other two factors of their L.C.M. are 13 and 14. The larger of the two numbers is:");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "276");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "299");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "322");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "345");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "322");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,3);
        insertFinalRow1(values);
    }

    private void insertRow8() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "Six bells commence tolling together and toll at intervals of 2, 4, 6, 8 10 and 12 seconds respectively. In 30 minutes, how many times do they toll together ?");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "4");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "10");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "15");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "16");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "16");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,4);
        insertFinalRow1(values);
    }

    private void insertRow9() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION,"Let N be the greatest number that will divide 1305, 4665 and 6905, leaving the same remainder in each case. Then sum of the digits in N is:");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "4");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "5");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "6");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "8");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "4");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,1);
        insertFinalRow1(values);
    }

    private void insertRow10() {
        ContentValues values = new ContentValues();
        values.put(QuestionsContract.QuestionsEntry.COLUMN_QUESTION, "The greatest number of four digits which is divisible by 15, 25, 40 and 75 is:");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION, "9000");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, "9400");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, "9600");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION, "9800");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_ANSWER, "9600");
        values.put(QuestionsContract.QuestionsEntry.COLUMN_CORRECT_OPTION,3);
        insertFinalRow1(values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.dummy_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.data1:
                insertRow1();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.data2:
                insertRow2();// Do nothing for now
                return true;
            case R.id.data3:
                insertRow3();
                return true;
            case R.id.data4:
                insertRow4();
                return true;
            case R.id.data5:
                insertRow5();
                return true;
            case R.id.data6:
                insertRow6();
                return true;
            case R.id.data7:
                insertRow7();
                return true;
            case R.id.data8:
                insertRow8();
                return true;
            case R.id.data9:
                insertRow9();
                return true;
            case R.id.data10:
                insertRow10();
                return true;



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String projection[] = {QuestionsContract.QuestionsEntry._ID, QuestionsContract.QuestionsEntry.COLUMN_QUESTION, QuestionsContract.QuestionsEntry.COLUMN_FIRST_OPTION,
                QuestionsContract.QuestionsEntry.COLUMN_SECOND_OPTION, QuestionsContract.QuestionsEntry.COLUMN_THIRD_OPTION, QuestionsContract.QuestionsEntry.COLUMN_FOURTH_OPTION};
        return new CursorLoader(this, QuestionsContract.QuestionsEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
