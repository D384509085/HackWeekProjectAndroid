package enbledu.hackweekproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class RobotDBHelper extends SQLiteOpenHelper {

    private static RobotDBHelper sHelper = null;
    private static final String DB_NAME = "question.db";
    private static final int VERSION = 1;
    private static final String SQL_CREATE = "create table robot_info(_id integer primary key autoincrement," +
            "qqid integer," +
            "character integer" +
            "stopSpeakingNum integer" +
            "isSpeakingAuto integer)";
    private static final String SQL_DROP = "drop table if exists robot_info";

    public RobotDBHelper(Context context) {
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
    public static RobotDBHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new RobotDBHelper(context);
        }
        return sHelper;
    }
}