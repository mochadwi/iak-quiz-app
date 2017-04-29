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

public class Question01Activity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01_question);
        ButterKnife.bind(this);

        answer.add("\"Final Fantasy XIII\""); // Right Answer
        answer.add("\"Final Fantasy XII\"");
        answer.add("\"Final Fantasy VII: Crisis Core\"");
        answer.add("\"Final Fantasy\"");
        final QuestionModel question = new QuestionModel(
                "Almost every installment of \"Final Fantasy\"" +
                        "began with the melody called \"Prelude\", " +
                        "and we were always rewarded with \"Victory Fanfare\" after a battle. \n\n" +
                        "Which title is the only game that does not include those staple tunes?",
                "", // Explanation
                "\"Final Fantasy XIII\"", answer, 20);

        // Get previous intent
        final UserModel user = new Gson().fromJson(
                this.getIntent().getStringExtra("biodata"), UserModel.class);

        txtQuestionScore.setText(String.valueOf(question.getPoint()));
        txtQuestion.setText(question.getQuestion());
        rb01.setText(question.getAnswer().get(0));
        rb02.setText(question.getAnswer().get(1));
        rb03.setText(question.getAnswer().get(2));
        rb04.setText(question.getAnswer().get(3));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb01.isChecked()) {
                    user.setUserScores(question.getPoint());
                    Toast.makeText(Question01Activity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    user.setUserScores(0);
                    Toast.makeText(Question01Activity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(Question01Activity.this, Question02Activity.class);
                i.putExtra("biodata", user.toString());
                i.putExtra("question01", question.toString());
                startActivity(i);
            }
        });
    }
}
