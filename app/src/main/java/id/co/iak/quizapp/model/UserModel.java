package id.co.iak.quizapp.model;

import com.google.gson.Gson;

/**
 * Created by mochadwi on 4/28/17.
 */

public class UserModel {
    private String name, email;
    private int userScores;

    public UserModel(String name, String email, int userScores) {
        this.name = name;
        this.email = email;
        this.userScores = userScores;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
