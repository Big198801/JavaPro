package ru.big198801.model;

public class Users {
    private Long id;
    private String username;

    public Users(Long id, String username) {
        this.id = id;
        this.username = username;
    }


    public Long getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + username + '\'' +
                '}';
    }
}
