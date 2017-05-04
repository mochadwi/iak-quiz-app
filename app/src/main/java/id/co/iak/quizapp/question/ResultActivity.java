package id.co.iak.quizapp.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

	// Views Binding
	@BindView(R.id.img_result)
	ImageView imgResult;
	@BindView(R.id.txt_indicator_question_val)
	TextView txtQuestionVal;
	@BindView(R.id.txt_score_val)
	TextView txtScoreVal;
	@BindView(R.id.txt_name_val)
	TextView txtNameVal;
	@BindView(R.id.btn_next)
	Button btnNext;

	// Data
	private int questionAnswered = 0;
	private int overallScores = 0;
	private String name = "";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		ButterKnife.bind(this);

		// Get previous intent
		final UserModel user = new Gson().fromJson(
				getExtra("biodata"), UserModel.class);
		final QuestionModel.QuestionListModel quests = new Gson().fromJson(
				getExtra("questions"), QuestionModel.QuestionListModel.class);

		name = user.getName();
		questionAnswered = user.getQuestionAnswered();
		overallScores = user.getUserScores();

		// Set data to layout
		Glide.with(this).load(R.drawable.result).into(imgResult);
		txtQuestionVal.setText("Question answered correctly: " + questionAnswered +
				"/" + getQuestSize(quests));
		txtScoreVal.setText("You scored " + String.valueOf(overallScores) + " points.");
		txtNameVal.setText("Congratulations, " + name);

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ResultActivity.this, AnswerActivity.class);
				i.putExtra("biodata", user.toString());
				i.putExtra("questions", quests.toString());
				startActivity(i);
				finish();
			}
		});
	}

	/**
	 * Retrieved String extra from previous intent
	 *
	 * @param key as key-value pair from previous intent
	 * @return String value of key
	 */
	private String getExtra(String key) {
		return this.getIntent().getStringExtra(key);
	}

	/**
	 * Retrieve prepared questions from previous intent
	 *
	 * @param q   QuestionModel.QuestionListModel
	 * @param pos posistion of each question
	 * @return QuestionModel
	 */
	private QuestionModel getQuests(QuestionModel.QuestionListModel q, int pos) {
		return q.getQuestion_list().get(pos);
	}

	/**
	 * Retrieve size of Question
	 *
	 * @param q QuestionModel.QuestionListModel
	 * @return int
	 */
	private int getQuestSize(QuestionModel.QuestionListModel q) {
		return q.getQuestion_list().size();
	}
}
