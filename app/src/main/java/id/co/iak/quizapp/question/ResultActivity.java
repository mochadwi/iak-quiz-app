package id.co.iak.quizapp.question;

import android.app.AlertDialog;
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
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

	// Utility
	/**
	 * Mobile Service Client reference
	 */
	private MobileServiceClient mAzureClient;

	/**
	 * Mobile Service Table used to access data
	 */
	private MobileServiceTable<UserModel> mToDoTable;

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

		try {
			// Create the Mobile Service Client instance, using the provided

			// Mobile Service URL and key
			mAzureClient = new MobileServiceClient(
					"https://ffquizapp.azurewebsites.net",
					this);

			// Extend timeout from default of 10s to 20s
			mAzureClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
				@Override
				public OkHttpClient createOkHttpClient() {
					OkHttpClient client = new OkHttpClient();
					client.setReadTimeout(20, TimeUnit.SECONDS);
					client.setWriteTimeout(20, TimeUnit.SECONDS);
					return client;
				}
			});

			// Get the Mobile Service Table instance to use

			mToDoTable = mAzureClient.getTable(UserModel.class);

			mToDoTable.insert(user);

		} catch (MalformedURLException e) {
			createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
		} catch (Exception e){
			createAndShowDialog(e, "Error");
		}

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

	/**
	 * Creates a dialog and shows it
	 *
	 * @param exception
	 *            The exception to show in the dialog
	 * @param title
	 *            The dialog title
	 */
	private void createAndShowDialog(Exception exception, String title) {
		Throwable ex = exception;
		if(exception.getCause() != null){
			ex = exception.getCause();
		}
		createAndShowDialog(ex.getMessage(), title);
	}

	/**
	 * Creates a dialog and shows it
	 *
	 * @param message
	 *            The dialog message
	 * @param title
	 *            The dialog title
	 */
	private void createAndShowDialog(final String message, final String title) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(message);
		builder.setTitle(title);
		builder.create().show();
	}
}
