package enbledu.hackweekproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class KeywordDBHelper extends SQLiteOpenHelper{
    private static KeywordDBHelper sHelper = null;
    private static final String DB_NAME = "keyword.db";
    private static final int VERSION = 1;
    private static final String SQL_CREATE = "create table keyword_info(_id integer primary key autoincrement," +
            "keyWord text" +
            "keyWordNumber integer)";
    private static final String SQL_DROP = "drop table if exists keyword_info";

    public KeywordDBHelper(Context context) {
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
    public static KeywordDBHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new KeywordDBHelper(context);
        }
        return sHelper;
    }
}