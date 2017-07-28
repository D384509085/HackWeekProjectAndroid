package enbledu.hackweekproject.Entity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class QuestionEntity {
    public boolean hasAnser;
    public String answer;

    public QuestionEntity(boolean hasAnser, String answer) {
        this.hasAnser = hasAnser;
        this.answer = answer;
    }

    public boolean isHasAnser() {
        return hasAnser;
    }

    public String getAnswer() {
        return answer;
    }

    public void setHasAnser(boolean hasAnser) {
        this.hasAnser = hasAnser;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
