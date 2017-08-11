package com.extremeplayer.quizme.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Abishaik on 01-07-2017.
 */

public final class QuestionsContract {

    public static final String CONTENT_AUTHORITY =  "com.extremeplayer.quizme";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_QUANTZ = "quantz";
    public static final String PATH_HCF_LCM = "hcf_lcm";
    public static final String PATH_BOOKMARKS = "bookmarks";
    public static final String PATH_CHALLENGES = "challenges";

    private QuestionsContract() {}

    public static final class QuestionsEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_QUANTZ);
        public static final Uri CONTENT_URI1 = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_BOOKMARKS);
        public static final Uri CONTENT_URI2 = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_CHALLENGES);
        public static final Uri CONTENT_URI3 = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_HCF_LCM);


        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_QUANTZ;

        public static final String CONTENT_LIST_TYPE1 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKMARKS;

        public static final String CONTENT_LIST_TYPE2 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CHALLENGES;

        public static final String CONTENT_LIST_TYPE3 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HCF_LCM;

        public static final String TABLE_NAME = "quantz";
        public static final String TABLE_NAME1 = "bookmarks";
        public static final String TABLE_NAME2 = "challenges";
        public static final String TABLE_NAME3 = "hcflcm";



        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_FIRST_OPTION = "option_a";
        public static final String COLUMN_SECOND_OPTION = "option_b";
        public static final String COLUMN_THIRD_OPTION = "option_c";
        public static final String COLUMN_FOURTH_OPTION = "option_d";
        public static final String COLUMN_CORRECT_ANSWER= "correct_answer";
        public static final String COLUMN_CORRECT_OPTION = "correct_option";
    }
}
