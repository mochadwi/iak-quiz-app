package id.co.iak.quizapp.question;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
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

public class AnswerActivity extends AppCompatActivity {

	// Views
	@BindView(R.id.txt_quest)
	TextView txtQuest;
	@BindView(R.id.img_result)
	ImageView imgResult;
	@BindView(R.id.txt_answer)
	TextView txtAnswer;
	@BindView(R.id.txt_explanation)
	TextView txtExplanation;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		ButterKnife.bind(this);

		// Get previous intent
		final QuestionModel.QuestionListModel quests = new Gson().fromJson(
				getExtra("questions"), QuestionModel.QuestionListModel.class);

		txtQuest.setText("1. " + getQuests(quests, 0).getQuestion());
		txtAnswer.setText("The correct answer was: " + getQuests(quests, 0).getRightAnswer());
		txtExplanation.setText(getQuests(quests, 0).getExplanation());
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
