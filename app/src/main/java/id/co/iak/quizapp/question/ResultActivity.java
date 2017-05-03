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

		// Get previous intent
		final UserModel user = new Gson().fromJson(
				getExtra("biodata"), UserModel.class);
		final QuestionModel.QuestionListModel quests = new Gson().fromJson(
				getExtra("questions"), QuestionModel.QuestionListModel.class);

		for (int i = 0; i < 5; i++) {
			final QuestionModel q = new QuestionModel(
					getQuests(quests, i).getQuestion(),
					getQuests(quests, i).getExplanation(),
					getQuests(quests, i).getRightAnswer(),
					getQuests(quests, i).getAnswer(),
					getQuests(quests, i).getPoint()
			);

			if (q.getPoint() > 0) {
				questionAnswered++;
				overallScores += q.getPoint();
			}
		}

		name = user.getName();

		txtQuestionVal.setText("Question answered correctly: " + questionAnswered +
				"/" + getQuestSize(quests));
		txtScoreVal.setText("You scored " + String.valueOf(overallScores) + " points.");
		txtNameVal.setText("Congratulations, " + name);
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
