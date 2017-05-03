package id.co.iak.quizapp.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.R;
import id.co.iak.quizapp.model.QuestionModel;
import id.co.iak.quizapp.model.UserModel;

public class Question01Activity extends AppCompatActivity {

	// Views
	@BindView(R.id.txt_score_val)
	TextView txtQuestionScore;
	@BindView(R.id.txt_indicator_question_val)
	TextView txtIndicator;
	@BindView(R.id.txt_question)
	TextView txtQuestion;
	@BindView(R.id.btn_next)
	Button btnNext;
	@BindView(R.id.rg_answers)
	RadioGroup rgAnswers;
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
		setContentView(R.layout.activity_question);
		ButterKnife.bind(this);

		// Get previous intent
		final UserModel user = new Gson().fromJson(
				getExtra("biodata"), UserModel.class);
		final QuestionModel.QuestionListModel questions = new Gson().fromJson(
				getExtra("questions"), QuestionModel.QuestionListModel.class
		);
		final QuestionModel question = new QuestionModel(
				1,
				getQuests(questions, 0).getQuestion(),
				getQuests(questions, 0).getExplanation(),
				getQuests(questions, 0).getRightAnswer(),
				getQuests(questions, 0).getAnswer(),
				getQuests(questions, 0).getPoint()
		);

		txtQuestionScore.setText(String.valueOf(question.getPoint()));
		txtIndicator.setText(String.valueOf("1/" + getQuestSize(questions)));
		txtQuestion.setText(question.getQuestion());
		rb01.setText(question.getAnswer().get(0)); // Right Answer
		rb02.setText(question.getAnswer().get(1));
		rb03.setText(question.getAnswer().get(2));
		rb04.setText(question.getAnswer().get(3));
		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rgAnswers.getCheckedRadioButtonId() == -1) {
					Toast.makeText(Question01Activity.this, "Please choose an answer!",
							Toast.LENGTH_SHORT).show();
				} else {
					if (rb01.isChecked()) {
						questions.getQuestion_list().get(0).setUserCorrect(true);
						user.setQuestionAnswered(1);
						user.setUserScores(question.getPoint());
						Toast.makeText(Question01Activity.this, "Correct!",
								Toast.LENGTH_SHORT).show();
					} else {
						user.setUserScores(0);
						Toast.makeText(Question01Activity.this, "Incorrect!",
								Toast.LENGTH_SHORT).show();
					}

					Intent i = new Intent(Question01Activity.this, ResultActivity.class);
					i.putExtra("biodata", user.toString());
					i.putExtra("questions", questions.toString());
					startActivity(i);
					finish();
				}
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
	 * @param q
	 * @param pos
	 * @return
	 */
	private QuestionModel getQuests(QuestionModel.QuestionListModel q, int pos) {
		return q.getQuestion_list().get(pos);
	}

	/**
	 * Retrieve size
	 *
	 * @param q
	 * @return
	 */
	private int getQuestSize(QuestionModel.QuestionListModel q) {
		return q.getQuestion_list().size();
	}
}
