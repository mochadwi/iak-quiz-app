package id.co.iak.quizapp.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.R;
import id.co.iak.quizapp.model.QuestionModel;
import id.co.iak.quizapp.model.UserModel;

public class Question02Activity extends AppCompatActivity {

    // Views
    @BindView(R.id.txt_score_val)
    TextView txtQuestionScore;
    @BindView(R.id.txt_question)
    TextView txtQuestion;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.rb_01)
    RadioButton rb01;
    @BindView(R.id.rb_02)
    RadioButton rb02;
    @BindView(R.id.rb_03)
    RadioButton rb03;
    @BindView(R.id.rb_04)
    RadioButton rb04;

    // Data
    private List<String> answer = new ArrayList<>();
    private List<QuestionModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01_question);
        ButterKnife.bind(this);

        answer.add("Aerith");
        answer.add("Fang");
        answer.add("Yuna");
        answer.add("Garnet");
        final QuestionModel question = new QuestionModel(
                "Who does not belong in this group?",
                "", // Explanation
                "\"Fang\"", answer, 20);
        final QuestionModel question2 = new Gson().fromJson(this.getIntent().getStringExtra("question"),
                QuestionModel.class);
        list.add(question2);
        list.add(question);

        // Get previous intent
        final UserModel user = new Gson().fromJson(
                this.getIntent().getStringExtra("biodata"), UserModel.class);

        final QuestionModel.QuestionListModel questionList = new QuestionModel.QuestionListModel();
        questionList.setQuestion_list(list);

        txtQuestionScore.setText(String.valueOf(question.getPoint()));
//        txtQuestion.setText(question.getQuestion());
        txtQuestion.setText(new Gson().toJson(questionList));
        rb01.setText(question.getAnswer().get(0));
        rb02.setText(question.getAnswer().get(1));
        rb03.setText(question.getAnswer().get(2));
        rb04.setText(question.getAnswer().get(3));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb01.isChecked()) {
                    user.setUserScores(question.getPoint());
                    Toast.makeText(Question02Activity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    user.setUserScores(0);
                    Toast.makeText(Question02Activity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(Question02Activity.this, Question02Activity.class);
                i.putExtra("biodata", user.toString());
                i.putExtra("question", question.toString());
                startActivity(i);
            }
        });
    }
}
