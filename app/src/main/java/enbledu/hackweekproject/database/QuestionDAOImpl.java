package enbledu.hackweekproject.database;

import android.content.ContentValues;
import android.content.Context;
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
        db.execSQL("insert into note_info(hasAnswer,answer) values(?,?)",
                new Object[]{hasAnswer,
                            questionEntity.getAnswer()
                });
        db.close();
    }


    @Override
    public void deleteQuestion(QuestionEntity questionEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from note_info where answer = ?",
                new String[]{questionEntity.getAnswer()});

        db.close();
    }

    @Override
    public void updateQuestion(QuestionEntity questionEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues edit_values = new ContentValues();
        if (questionEntity.isHasAnswer()) {
            edit_values.put("hasAnswer", 1);
        } else {
            edit_values.put("hasAnswer", 0);
        }
        edit_values.put("answer", questionEntity.getAnswer());
        db.update("note_info", edit_values, "title = ?", new String[]{noteEntity.getTitle()});
        db.close();
    }

    @Override
    public ArrayList<QuestionEntity> getQuestion() {
        return null;
    }
}
