package enbledu.hackweekproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.KeyWordEntity;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class KeyWordDAOImpl implements KeyWordDAO {

    private KeyWordDBHelper mHelper;

    public KeyWordDAOImpl(Context context) {
        mHelper = KeyWordDBHelper.getInstance(context);
    }
    @Override
    public void insertQuestion(KeyWordEntity keyWordEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into note_info(keyword) values(?............)",
                new Object[]{keyWordEntity.getKeyWord()
                });
        db.close();
    }

    @Override
    public void deleteQuestion(KeyWordEntity keyWordEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from note_info where keyword = ?",
                new String[]{keyWordEntity.getKeyWord()});

        db.close();
    }

    @Override
    public void updateQuestion(KeyWordEntity keyWordEntity) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues edit_values = new ContentValues();
        edit_values.put("keyword", keyWordEntity.getKeyWord());
        db.update("keyword_info", edit_values, "keyword = ?", new String[]{keyWordEntity.getKeyWord()});
        db.close();
    }

    @Override
    public ArrayList<KeyWordEntity> getKeyWord() {
        return null;
    }
}
