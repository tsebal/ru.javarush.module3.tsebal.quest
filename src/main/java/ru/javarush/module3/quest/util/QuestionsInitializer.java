package ru.javarush.module3.quest.util;

import ru.javarush.module3.quest.entity.Question;

import java.util.*;

/**
 *  Initializes the map with the questions and their id's.
 */
public class QuestionsInitializer {
    private static Properties questProp;
    private final Map<Integer, Question> questionsMap;

    public QuestionsInitializer(Properties questProp) {
        QuestionsInitializer.questProp = questProp;
        this.questionsMap = new HashMap<>();
        init();
    }

    public Map<Integer, Question> getQuestionsMap() {
        return questionsMap;
    }

    private void init() {
        int questionsTotal = Integer.parseInt(questProp.getProperty("QuestionsTotal"));

        for (int i = 0; i < questionsTotal; i++) {
            String questionText = questProp.getProperty(String.valueOf(i));
            Question question = new Question(i, questionText);
            questionsMap.put(i, question);
        }
    }
}
