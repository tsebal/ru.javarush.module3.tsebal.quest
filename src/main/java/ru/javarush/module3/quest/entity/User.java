package ru.javarush.module3.quest.entity;

public class User {
    private final String name;
    private int score;

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public void resetScore() {
        this.score = 0;
    }
}
