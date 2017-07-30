package enbledu.hackweekproject.database;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.KeyWordEntity;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public interface KeyWordDAO {
    public void insertQuestion(KeyWordEntity keyWordEntity);

    public void deleteQuestion(KeyWordEntity keyWordEntity);

    public void updateQuestion(KeyWordEntity keyWordEntity);

    public ArrayList<KeyWordEntity> getKeyWord();
}
