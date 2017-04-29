package id.co.iak.quizapp.model;

import com.google.gson.Gson;

/**
 * Created by mochadwi on 4/28/17.
 */

public class UserModel {
    private String name, email;

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
