package ru.javarush.module3.quest.repository;

import ru.javarush.module3.quest.entity.Answer;

import java.util.Map;
import java.util.Optional;

public class AnswerRepository {
    private final Map<Integer, Answer> idToAnswer;

    public AnswerRepository(Map<Integer, Answer> idToAnswer) {
        this.idToAnswer = idToAnswer;
    }

    public Optional<Answer> findAnswerById(int answerId) {
        return Optional.ofNullable(idToAnswer.get(answerId));
    }
}
