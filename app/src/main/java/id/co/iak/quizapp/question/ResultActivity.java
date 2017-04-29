package id.co.iak.quizapp.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

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
    @BindView(R.id.txt_name_val)
    TextView txtName;
    @BindView(R.id.txt_score_val)
    TextView txtScore;

    // Data
    private String name;
    private int scores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Intent i = getIntent();
        final UserModel user = new Gson().fromJson(
                i.getStringExtra("biodata"), UserModel.class);
        final QuestionModel question = new Gson().fromJson(
                i.getStringExtra("question01"), QuestionModel.class);

        name = user.getName();
        scores = question.getPoint();

        txtName.setText("Congratulations, " + name);
        txtScore.setText("You scored " + String.valueOf(scores) + " points.");
    }
}
