package enbledu.hackweekproject.database;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.KeywordEntity;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public interface KeyWordDAO {
    public void insertQuestion(KeywordEntity keyWordEntity);

    public void deleteQuestion(KeywordEntity keyWordEntity);

    public void updateQuestion(KeywordEntity keyWordEntity);

    public ArrayList<KeywordEntity> getKeyWord();
}
