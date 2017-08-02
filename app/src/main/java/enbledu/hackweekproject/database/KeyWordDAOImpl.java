package enbledu.hackweekproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.KeywordEntity;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class KeyWordDAOImpl implements KeyWordDAO {

    private KeyWordDBHelper mHelper;

    public KeyWordDAOImpl(Context context) {
        mHelper = KeyWordDBHelper.getInstance(context);
    }
    @Override
    public void insertQuestion(KeywordEntity keyWordEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into keyword_info(keyWord,keyWordNumber) values(?,?)",
                new Object[]{keyWordEntity.getKeyWord(),
                            keyWordEntity.getKeyWordNumber()
                });
        db.close();
    }

    @Override
    public void deleteQuestion(KeywordEntity keyWordEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from note_info where keyWordNumber = ?",
                new Integer[]{keyWordEntity.getKeyWordNumber()});

        db.close();
    }

    @Override
    public void updateQuestion(KeywordEntity keyWordEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues edit_values = new ContentValues();
        edit_values.put("keyWord", keyWordEntity.getKeyWord());
        edit_values.put("keyWordNumber", keyWordEntity.getKeyWordNumber());
        db.update("keyword_info", edit_values, "keyWordNumber = ?", new String[]{String.valueOf(keyWordEntity.getKeyWordNumber())});
        db.close();
    }

    @Override
    public ArrayList<KeywordEntity> getKeyWord() {
        return null;
    }
}
