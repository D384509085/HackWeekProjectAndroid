package enbledu.hackweekproject.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class QuestionEntity implements Serializable{
    private String answer;
    private String question1;
    private String question2;
    private String question3;
    private int questionNumber;

    public QuestionEntity(String answer, String question1, String question2, String question3,int questionNumber) {
        this.answer = answer;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.questionNumber = questionNumber;
    }

    public QuestionEntity() {
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getQuestion3() {
        return question3;
    }
}
