package enbledu.hackweekproject.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class KeywordEntity implements Serializable{
    String keyWord;
    int keyWordNumber;

    public KeywordEntity(String keyWord, int keyWordNumber) {
        this.keyWord = keyWord;
        this.keyWordNumber = keyWordNumber;
    }

    public KeywordEntity() {
    }

    public void setKeyWordNumber(int keyWordNumber) {
        this.keyWordNumber = keyWordNumber;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public int getKeyWordNumber() {
        return keyWordNumber;
    }
}
