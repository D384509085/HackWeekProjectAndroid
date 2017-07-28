package enbledu.hackweekproject.Entity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class QuestionEntity {
    private boolean hasAnswer;
    private String answer;
    private String question;

    public QuestionEntity(boolean hasAnser, String answer, String question) {
        this.hasAnswer = hasAnser;
        this.answer = answer;
        this.question = question;
    }

    public QuestionEntity() {

    }
    public boolean isHasAnswer() {
        return hasAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setHasAnser(boolean hasAnser) {
        this.hasAnswer = hasAnser;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
