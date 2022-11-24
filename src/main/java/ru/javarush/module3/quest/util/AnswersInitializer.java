package ru.javarush.module3.quest.util;

import ru.javarush.module3.quest.entity.Answer;
import ru.javarush.module3.quest.entity.Question;

import java.util.*;

/**
 *  Initializes the map with the answers and their id's.
 *  Also initializes the List<Answer> for each question.
 */
public class AnswersInitializer {
    private static Properties questProp;
    private final Map<Integer, Answer> answersMap;
    private final Map<Integer, Question> questionsMap;
    private final List<Answer> answersList;

    public AnswersInitializer(Properties questProp, Map<Integer, Question> questionsMap) {
        AnswersInitializer.questProp = questProp;
        this.answersMap = new HashMap<>();
        this.questionsMap = questionsMap;
        this.answersList = new ArrayList<>();
        init();
    }

    public Map<Integer, Answer> getAnswersMap() {
        return answersMap;
    }

    private void init() {

        int maxAnswersPerQuestion = Integer.parseInt(questProp.getProperty("MaxAnswersPerQuestion"));
        int answersSectionFactor = Integer.parseInt(questProp.getProperty("AnswersSectionFactor"));

        for (Integer questionId : questionsMap.keySet()) {
            for (int i = 0; i < maxAnswersPerQuestion; i++) {

                int answerId = questionId * answersSectionFactor + i;
                String answerText = questProp.getProperty("Answer" + answerId, null);
                Integer nextQuestionNum = Integer.parseInt(questProp.getProperty("NextQuestion" + answerId, "0"));
                Question nextQuestion = questionsMap.getOrDefault(nextQuestionNum, null);

                if (Objects.nonNull(answerText)) {
                    Answer answer = new Answer(answerId, answerText, nextQuestion);
                    answersMap.put(answerId, answer);
                    answersList.add(answer);
                }
            }

            List<Answer> currentAnswerList = new ArrayList<>(answersList);
            Question currentQuestion = questionsMap.get(questionId);
            currentQuestion.setAnswers(currentAnswerList);
            answersList.clear();
        }
    }
}
