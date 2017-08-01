package enbledu.hackweekproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.QuestionEntity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class QuestionDAOImpl implements QuestionDAO {


    private QuestionDBHelper mHelper;

    public QuestionDAOImpl(Context context) {
        mHelper = QuestionDBHelper.getInstance(context);
    }

    @Override
    public void insertQuestion(QuestionEntity questionEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into note_info(answer,question1,question2,question3, questionNumber) values(?,?,?,?,?)",
                new Object[]{questionEntity.getAnswer(),
                        questionEntity.getQuestion1(),
                        questionEntity.getQuestion2(),
                        questionEntity.getQuestion3(),
                        questionEntity.getQuestionNumber()
                });
        db.close();
    }


    @Override
    public void deleteQuestion(int questionNumber) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from question_info where questionNumber = ?",
                new Integer[]{questionNumber});

        db.close();
    }

    @Override
    public void updateQuestion(QuestionEntity questionEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues edit_values = new ContentValues();
        edit_values.put("answer", questionEntity.getAnswer());
        edit_values.put("question1", questionEntity.getQuestion1());
        edit_values.put("question2", questionEntity.getQuestion2());
        edit_values.put("question3", questionEntity.getQuestion3());
        db.update("question_info", edit_values, "questionNumber = ?",  new String[]{String.valueOf(questionEntity.getQuestionNumber())});
        db.close();
    }

    @Override
    public ArrayList<QuestionEntity> getQuestion() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ArrayList<QuestionEntity> list = new ArrayList<QuestionEntity>();
        /*Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
                 new String[]{url});*/
        Cursor cursor;
        cursor = db.query("question_info", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setAnswer(cursor.getString(cursor.getColumnIndex("answer")));
            questionEntity.setQuestion1(cursor.getString(cursor.getColumnIndex("question1")));
            questionEntity.setQuestion2(cursor.getString(cursor.getColumnIndex("question2")));
            questionEntity.setQuestion3(cursor.getString(cursor.getColumnIndex("question3")));
            questionEntity.setQuestionNumber(cursor.getInt(cursor.getColumnIndex("questionNumber")));
            list.add(questionEntity);
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public int getQuestionNum() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        int num = 0;
        /*Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
                 new String[]{url});*/
        Cursor cursor;
        cursor = db.query("question_info", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            num++;
        }
        cursor.close();
        db.close();
        return num;
    }
}
