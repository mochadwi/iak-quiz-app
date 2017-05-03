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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_02_question);
		ButterKnife.bind(this);

		// Get previous intent
		final UserModel user = new Gson().fromJson(
				getExtra("biodata"), UserModel.class);
		final QuestionModel.QuestionListModel questions = new Gson().fromJson(
				getExtra("questions"), QuestionModel.QuestionListModel.class
		);
		final QuestionModel question = new QuestionModel(
				getQuests(questions, 1).getQuestion(),
				getQuests(questions, 1).getExplanation(),
				getQuests(questions, 1).getRightAnswer(),
				getQuests(questions, 1).getAnswer(),
				getQuests(questions, 1).getPoint()
		);

		txtQuestionScore.setText(String.valueOf(question.getPoint()));
		txtQuestion.setText(question.getQuestion());
		rb01.setText(question.getAnswer().get(0));
		rb02.setText(question.getAnswer().get(1)); // Right Answer
		rb03.setText(question.getAnswer().get(2));
		rb04.setText(question.getAnswer().get(3));
		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rb02.isChecked()) {
					user.setUserScores(question.getPoint());
					Toast.makeText(Question02Activity.this, "Correct!", Toast.LENGTH_SHORT).show();
				} else {
					user.setUserScores(0);
					Toast.makeText(Question02Activity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
				}

				Intent i = new Intent(Question02Activity.this, Question02Activity.class);
				i.putExtra("biodata", user.toString());
				i.putExtra("questions", questions.toString());
				startActivity(i);
				finish();
			}
		});
	}

	/**
	 * Retrieved String extra from previous intent
	 *
	 * @param key as key-pair values from previous intent
	 * @return
	 */
	private String getExtra(String key) {
		return this.getIntent().getStringExtra(key);
	}

	/**
	 * Retrieve prepared questions from previous intent
	 *
	 * @param questions
	 * @param position
	 * @return
	 */
	private QuestionModel getQuests(QuestionModel.QuestionListModel questions, int position) {
		return questions.getQuestion_list().get(position);
	}
}
