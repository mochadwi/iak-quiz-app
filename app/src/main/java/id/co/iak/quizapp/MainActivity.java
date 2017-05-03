package id.co.iak.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.model.QuestionModel;
import id.co.iak.quizapp.model.UserModel;
import id.co.iak.quizapp.question.Question01Activity;

public class MainActivity extends AppCompatActivity {

	// Views Binding
	@BindView(R.id.img_main)
	ImageView imgMain;
	@BindView(R.id.btn_continue)
	Button btnContinue;
	@BindView(R.id.edt_name)
	TextView edtName;
	@BindView(R.id.edt_email)
	TextView edtEmail;
	@BindView(R.id.cb_eula)
	CheckBox cbEula;

	// Data
	private String name, email;
	private List<QuestionModel> questions = new ArrayList<>();

	/**
	 * Create views
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		Glide.with(this).load(R.drawable.main).into(imgMain);

		btnContinue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				name = edtName.getText().toString();
				email = edtEmail.getText().toString();

				if (cbEula.isChecked() && !name.isEmpty() && !email.isEmpty()) {
					UserModel user = new UserModel(name, email, 0);

					Toast.makeText(MainActivity.this, "Ready!", Toast.LENGTH_SHORT).show();

					Intent i = new Intent(MainActivity.this, Question01Activity.class);
					i.putExtra("biodata", user.toString());

					// Prepare all questions here
					i.putExtra("questions", prepareQuestion().toString());
					startActivity(i);
					finish();
				} else {
					Toast.makeText(MainActivity.this, "Please ensure your submission!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	/**
	 * Prepare list of question
	 *
	 * @return new QuestionModel.QuestionListModel
	 */
	private QuestionModel.QuestionListModel prepareQuestion() {
		// Question 01
		List<String> answers = new ArrayList<>();
		answers.add("\"Final Fantasy XIII\""); // Right Answer
		answers.add("\"Final Fantasy XII\"");
		answers.add("\"Final Fantasy VII: Crisis Core\"");
		answers.add("\"Final Fantasy\"");
		QuestionModel question = new QuestionModel(
				1,
				// Question
				"Almost every installment of \"Final Fantasy\"" +
						"began with the melody called \"Prelude\", " +
						"and we were always rewarded with \"Victory Fanfare\" after a battle. \n\n" +
						"Which title is the only game that does not include those staple tunes?",
				// Explanation
				"This game's music was not composed by Nobuo Uematsu. In \"Final Fantasy XII\" " +
						"we only heard the fanfare after boss fights.",
				"\"Final Fantasy XIII\"", answers, 20);
		questions.add(question);

		// Question 02
		answers = new ArrayList<>();
		answers.add("Aerith");
		answers.add("Fang"); // Right Answer
		answers.add("Yuna");
		answers.add("Garnet");
		question = new QuestionModel(
				2,
				// Question
				"Who does not belong in this group?",
				// Explanation
				"Fang uses a spear while the other three use staves or wands. " +
						"Also, Aerith, Yuna and Garnet rely more on magic and summons " +
						"while Fang attacks and takes damage.",
				"Fang", answers, 20);
		questions.add(question);

		// Question 03
		answers = new ArrayList<>();
		answers.add("Remiem Tower");
		answers.add("Lix Tower");
		answers.add("Phoenix Tower");
		answers.add("Zot Tower");  // Right Answer
		question = new QuestionModel(
				3,
				// Question
				"In \"Final Fantasy IV\", the Magus Sisters are bosses that sit atop what tower?",
				// Explanation
				"In this game the sisters are the minions of Barbariccia. " +
						"Golbez orders for the sisters to kill Cecil. " +
						"They recur often in the series, and always use their Delta Attack.",
				"Zot Tower", answers, 20);
		questions.add(question);

		// Question 04
		answers = new ArrayList<>();
		answers.add("Beatrix");  // Right Answer
		answers.add("Garnet");
		answers.add("Zidane");
		answers.add("Eiko");
		question = new QuestionModel(
				4,
				// Question
				"In \"Final Fantasy IX\", whose theme song is titled \"Loss of Me\"?",
				// Explanation
				"It is also known in Japan as \"Rose of May\". " +
						"The song plays every time you fight Beatrix and " +
						"when she and Steiner are protecting Alexandria.",
				"Beatrix", answers, 20);
		questions.add(question);

		// Question 05
		answers = new ArrayList<>();
		answers.add("Mario");
		answers.add("Link"); // Right Answer
		answers.add("Donkey Kong");
		answers.add("Gannon");
		question = new QuestionModel(
				5,
				// Question
				"In \"Final Fantasy I\" (\"Origins\" for the PSX), inside Elfheim's small graveyard," +
						" there's a tombstone with and epitaph for what Nintendo character?",
				// Explanation
				"It reads \"Here Lies Link\". It is located in a Elf town (as Link resembles an elf). " +
						"All the other headstones are blank.",
				"Link", answers, 20);
		questions.add(question);

		return new QuestionModel.QuestionListModel(questions);
	}
}
