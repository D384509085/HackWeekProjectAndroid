package enbledu.hackweekproject.Entity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class QuestionEntity {
    public boolean hasAnswer;
    public String answer;

    public QuestionEntity(boolean hasAnser, String answer) {
        this.hasAnswer = hasAnser;
        this.answer = answer;
    }

    public boolean isHasAnser() {
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
}
