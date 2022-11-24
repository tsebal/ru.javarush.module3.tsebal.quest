package ru.javarush.module3.quest.entity;

public class Answer {
    private final int id;
    private final String text;
    private final Question nextQuestion;

    public Answer(int id, String text, Question nextQuestion) {
        this.id = id;
        this.text = text;
        this.nextQuestion = nextQuestion;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Question getNextQuestion() {
        return nextQuestion;
    }
}
