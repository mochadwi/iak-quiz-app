package id.co.iak.quizapp.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.R;
import id.co.iak.quizapp.model.QuestionModel;
import id.co.iak.quizapp.model.UserModel;

/**
 * Created by mochadwi on 4/28/17.
 */

public class ResultActivity extends AppCompatActivity {

    // Views
    @BindView(R.id.txt_indicator_question_val)
    TextView txtQuestionVal;
    @BindView(R.id.txt_score_val)
    TextView txtScoreVal;
    @BindView(R.id.txt_name_val)
    TextView txtNameVal;

    // Data
    private List<QuestionModel> question = new ArrayList<>();
    private int questionAnswered = 0;
    private int overallScores = 0;
    private String name = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Intent i = getIntent();
        final UserModel user = new Gson().fromJson(
                i.getStringExtra("biodata"), UserModel.class);

        for (int j = 1; j <= 4; j++) {
            final QuestionModel q = new Gson().fromJson(
                    i.getStringExtra("question0" + j), QuestionModel.class);
            question.add(q);

            if (question.get(j - 1).getPoint() > 0) {
                questionAnswered++;
            }
        }

        name = user.getName();
        overallScores = question.get(0).getPoint();

        txtQuestionVal.setText("Question answered correctly: " + questionAnswered + " / 5");
        txtScoreVal.setText("You scored " + String.valueOf(overallScores) + " points.");
        txtNameVal.setText("Congratulations, " + name);
    }
}
