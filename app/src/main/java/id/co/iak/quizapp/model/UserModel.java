package id.co.iak.quizapp.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mochadwi on 4/28/17.
 */

public class UserModel {
	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	@SerializedName("score")
	private int userScores;

	private int questionAnswered;

	public UserModel(String id, String name, String email, int userScores, int questionAnswered) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.userScores = userScores;
		this.questionAnswered = questionAnswered;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserScores() {
		return userScores;
	}

	public void setUserScores(int userScores) {
		this.userScores += userScores;
	}

	public int getQuestionAnswered() {
		return questionAnswered;
	}

	public void setQuestionAnswered(int questionAnswered) {
		this.questionAnswered += questionAnswered;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
