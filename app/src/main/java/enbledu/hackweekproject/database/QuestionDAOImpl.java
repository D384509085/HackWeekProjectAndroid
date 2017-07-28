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

    private static final String TAG = "NoteDAOImpl";
    private QuestionDBHelper mHelper;

    public QuestionDAOImpl(Context context) {
        mHelper = QuestionDBHelper.getInstance(context);
    }

    @Override
    public void insertQuestion(QuestionEntity questionEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int hasAnswer;
        if (questionEntity.isHasAnswer()) {
            hasAnswer = 1;
        } else {
            hasAnswer = 0;
        }
        db.execSQL("insert into note_info(question,hasAnswer,answer) values(?,?,?)",
                new Object[]{questionEntity.getQuestion(),
                        hasAnswer,
                        questionEntity.getAnswer()
                });
        db.close();
    }


    @Override
    public void deleteQuestion(QuestionEntity questionEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from note_info where question = ?",
                new String[]{questionEntity.getQuestion()});

        db.close();
    }

    @Override
    public void updateQuestion(QuestionEntity questionEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues edit_values = new ContentValues();
        edit_values.put("question", questionEntity.getQuestion());
        if (questionEntity.isHasAnswer()) {
            edit_values.put("hasAnswer", 1);
        } else {
            edit_values.put("hasAnswer", 0);
        }
        edit_values.put("answer", questionEntity.getAnswer());
        db.update("question_info", edit_values, "question = ?", new String[]{questionEntity.getQuestion()});
        db.close();
    }

    @Override
    public ArrayList<QuestionEntity> getQuestion() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ArrayList<QuestionEntity> list = new ArrayList<QuestionEntity>();
        /*Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
                 new String[]{url});*/
        Cursor cursor;
        cursor = db.query("note_info", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
            questionEntity.setHasAnser(1 == cursor.getInt(cursor.getColumnIndex("hasAnswer")));
            questionEntity.setAnswer(cursor.getString(cursor.getColumnIndex("answer")));
            list.add(questionEntity);
        }
        cursor.close();
        db.close();
        return list;
    }
}
