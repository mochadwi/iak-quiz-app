package id.co.iak.quizapp.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mochadwi on 4/26/17.
 */

public class QuestionModel {
    private String question, explanation, rightAnswer;
    private List<String> answer = new ArrayList<>();
    private int point;

    public QuestionModel() {
    }

    public QuestionModel(String question, String explanation, String rightAnswer, List<String> answer, int point) {
        this.question = question;
        this.explanation = explanation;
        this.rightAnswer = rightAnswer;
        this.answer = answer;
        this.point = point;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static class QuestionListModel {
        private List<QuestionModel> question_list;

        public QuestionListModel(List<QuestionModel> question_list) {
            this.question_list = question_list;
        }

        public List<QuestionModel> getQuestion_list() {
            return question_list;
        }

        public void setQuestion_list(List<QuestionModel> question_list) {
            this.question_list = question_list;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }
}
