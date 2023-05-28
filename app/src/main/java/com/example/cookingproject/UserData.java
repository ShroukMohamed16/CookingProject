package com.example.cookingproject;

public class UserData {
    private String email,username;

    public UserData() {
    }

    public UserData(String email, String username) {
        this.email = email;
        this.username = username;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", name='" + username + '\'' +
                '}';
    }
}

