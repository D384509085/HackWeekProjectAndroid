package enbledu.hackweekproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.RobotEntity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class RobotDAOImpl implements RobotDAO{

    private RobotDBHelper mHelper;

    public RobotDAOImpl(Context context) {
        mHelper = RobotDBHelper.getInstance(context);
    }
    @Override
    public void insertRobot(RobotEntity robotEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int isSpeakAutoInt;
        if (robotEntity.isSpeakAuto()) {
            isSpeakAutoInt = 1;
        } else {
            isSpeakAutoInt = 0;
        }
        Log.i("insert", robotEntity.toString());
        db.execSQL("insert into robot_info(name,qqid,character,stopSpeakingNum,isSpeakingAuto) values(?,?,?,?,?)",
                new Object[]{robotEntity.getName(),
                        robotEntity.getQqID(),
                        robotEntity.getCharacter(),
                        robotEntity.getStopSpeakingNum(),
                        isSpeakAutoInt
        });
        db.close();
    }

    @Override
    public void deleteRobot(String name) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from note_info where name = ?",
                new String[]{name});

        db.close();
    }

    @Override
    public void updateRobot(RobotEntity robotEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues edit_values = new ContentValues();
        edit_values.put("name", robotEntity.getName());
        edit_values.put("qqid", robotEntity.getQqID());
        edit_values.put("character", robotEntity.getCharacter());
        edit_values.put("stopSpeakingNum", robotEntity.getStopSpeakingNum());
        if (robotEntity.isSpeakAuto()) {
            edit_values.put("isSpeakingAuto", 1);
        } else {
            edit_values.put("isSpeakingAuto", 0);
        }
        db.update("robot_info", edit_values, "name = ?", new String[]{robotEntity.getName()});
        db.close();
    }

    @Override
    public ArrayList<RobotEntity> getRobotDatas() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ArrayList<RobotEntity> list = new ArrayList<RobotEntity>();
        /*Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
                 new String[]{url});*/
        Cursor cursor;
        cursor = db.query("robot_info", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            RobotEntity robotEntity = new RobotEntity();
            robotEntity.setName(cursor.getString(cursor.getColumnIndex("name")));
            robotEntity.setQqID(cursor.getInt(cursor.getColumnIndex("qqid")));
            robotEntity.setCharacter(cursor.getInt(cursor.getColumnIndex("character")));
            robotEntity.setStopSpeakingNum(cursor.getInt(cursor.getColumnIndex("stopSpeakingNum")));
            robotEntity.setSpeakAuto(1 == cursor.getInt(cursor.getColumnIndex("isSpeakingAuto")));
            list.add(robotEntity);
            Log.i("RObotDAOImp",robotEntity.toString());
        }
        cursor.close();
        db.close();
        return list;
    }
}
