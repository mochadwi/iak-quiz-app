package id.co.iak.quizapp.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.MainActivity;
import id.co.iak.quizapp.R;
import id.co.iak.quizapp.model.QuestionModel;
import id.co.iak.quizapp.model.UserModel;

public class Question01Activity extends AppCompatActivity {

    // Views
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
    private String rightAnswer = "";
    private List<String> answer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01_question);
        ButterKnife.bind(this);

        answer.add("\"Final Fantasy XIII\"");
        answer.add("\"Final Fantasy XII\"");
        answer.add("\"Final Fantasy VII: Crisis Core\"");
        answer.add("\"Final Fantasy\"");
        final QuestionModel question = new QuestionModel(
                "Almost every installment of \"Final Fantasy\"" +
                "began with the melody called \"Prelude\", " +
                "and we were always rewarded with \"Victory Fanfare\" after a battle. \n\n" +
                "Which title is the only game that does not include those staple tunes?",
                "", // Explanation
                rightAnswer, answer, 20);

        txtQuestion.setText(question.getQuestion());
        rb01.setText(question.getAnswer().get(0));
        rb02.setText(question.getAnswer().get(1));
        rb03.setText(question.getAnswer().get(2));
        rb04.setText(question.getAnswer().get(3));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Question02", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Question01Activity.this, Question02Activity.class);
                i.putExtra("question01", question.toString());
                startActivity(i);
            }
        });
    }
}
