package id.co.iak.quizapp.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.R;

/**
 * Created by mochadwi on 4/26/17.
 */

public class QuestionModel extends AbstractItem<QuestionModel, QuestionModel.ViewHolder> {
	private String question, explanation, rightAnswer;
	private List<String> answer = new ArrayList<>();
	private int number, point;
	private boolean isUserCorrect = false;

	public QuestionModel() {
	}

	public QuestionModel(int number, String question, String explanation, String rightAnswer,
	                     List<String> answer, int point) {
		this.number = number;
		this.question = question;
		this.explanation = explanation;
		this.rightAnswer = rightAnswer;
		this.answer = answer;
		this.point = point;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isUserCorrect() {
		return isUserCorrect;
	}

	public void setUserCorrect(boolean userCorrect) {
		isUserCorrect = userCorrect;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	// Start FastAdapter
	@Override
	public int getType() {
		return R.id.ll_activity_answer;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.list_question;
	}


	@Override
	public void bindView(ViewHolder holder, List<Object> payloads) {
		super.bindView(holder, payloads);

		// Binds our data
		if (isUserCorrect) {
			Glide.with(holder.itemView.getContext())
					.load(R.drawable.ic_check_circle_black_48dp).into(holder.imgResult);
		} else {
			Glide.with(holder.itemView.getContext())
					.load(R.drawable.ic_highlight_off_black_48dp).into(holder.imgResult);
		}
		holder.txtQuest.setText(number + ". " + question);
		holder.txtAnswer.setText("The correct answer was: " + rightAnswer);
		holder.txtExplanation.setText(explanation);
	}

	@Override
	public void unbindView(ViewHolder holder) {
		super.unbindView(holder);
		holder.txtQuest.setText(null);
		holder.txtAnswer.setText(null);
		holder.txtExplanation.setText(null);
	}

	@Override
	public ViewHolder getViewHolder(View v) {
		return new ViewHolder(v);
	}

	protected static class ViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.txt_quest)
		TextView txtQuest;
		@BindView(R.id.img_result)
		ImageView imgResult;
		@BindView(R.id.txt_answer)
		TextView txtAnswer;
		@BindView(R.id.txt_explanation)
		TextView txtExplanation;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
	// End FastAdapter

	public static class QuestionListModel {
		private List<QuestionModel> question_list;

		public QuestionListModel(List<QuestionModel> question_list) {
			this.question_list = question_list;
		}

		public List<QuestionModel> getQuestion_list() {
			return question_list;
		}

		public void setQuestion_list(List<QuestionModel> question_list) {
			this.question_list = question_list;
		}

		@Override
		public String toString() {
			return new Gson().toJson(this);
		}
	}
}
