package enbledu.hackweekproject.database;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.QuestionEntity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public interface QuestionDAO {
    public void insertQuestion(QuestionEntity questionEntity);

    public void deleteQuestion(int questionNumber);

    public void updateQuestion(QuestionEntity questionEntity);

    public ArrayList<QuestionEntity> getQuestion();

    public int getQuestionNum();
}
