package com.example.demospring2;

public class Usuario {
    String nickname;
    String password;

    public Usuario() {
    }

    public Usuario(String nickname, String password) {
        this.nickname = nickname;
        boolean matchFound = password.matches("^[a-zA-Z0-9]{8,18}$");
        if (matchFound) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Error de password");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
