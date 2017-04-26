package id.co.iak.quizapp.model;

/**
 * Created by mochadwi on 4/26/17.
 */

public class QuestionModel {
    private String question, answer;
    private int point;

    public QuestionModel(String question, String answer, int point) {
        this.question = question;
        this.answer = answer;
        this.point = point;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
