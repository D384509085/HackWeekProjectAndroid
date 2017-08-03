package enbledu.hackweekproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class QuestionDBHelper extends SQLiteOpenHelper {

    private static QuestionDBHelper sHelper = null;
    private static final String DB_NAME = "question.db";
    private static final int VERSION = 1;
    private static final String SQL_CREATE = "create table question_info(_id integer primary key autoincrement," +
            "answer text," +
            "question1 text," +
            "question2 text," +
            "question3 text," +
            "questionNumber integer)";
    private static final String SQL_DROP = "drop table if exists question_info";

    public QuestionDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
    public static QuestionDBHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new QuestionDBHelper(context);
        }
        return sHelper;
    }
}