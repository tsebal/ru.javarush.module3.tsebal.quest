package ru.javarush.module3.quest.service;

import ru.javarush.module3.quest.entity.Answer;
import ru.javarush.module3.quest.entity.User;
import ru.javarush.module3.quest.repository.AnswerRepository;
import ru.javarush.module3.quest.repository.QuestionRepository;
import ru.javarush.module3.quest.repository.UserRepository;
import ru.javarush.module3.quest.util.AnswersInitializer;
import ru.javarush.module3.quest.util.PropertiesLoader;
import ru.javarush.module3.quest.util.QuestionsInitializer;

import java.util.*;

public class GameService {
    private static GameService instance;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private UserRepository userRepository;

    public static synchronized GameService getInstance() {
        if (instance == null)
            instance = new GameService();
        return instance;
    }

    public GameService() {
    }

    public void init(String appPath) {
        Properties questProp = PropertiesLoader.load(appPath);
        QuestionsInitializer questionsInitializer = new QuestionsInitializer(questProp);
        AnswersInitializer answersInitializer = new AnswersInitializer(questProp, questionsInitializer.getQuestionsMap());
        this.questionRepository = new QuestionRepository(questionsInitializer.getQuestionsMap());
        this.answerRepository = new AnswerRepository(answersInitializer.getAnswersMap());
        this.userRepository = new UserRepository();
    }

    public void addNewUser(String sessionId, String userName) {
        userRepository.addUser(sessionId, new User(userName));
    }

    public boolean userIsPresent(String sessionId) {
        return Objects.nonNull(userRepository.findUserBySessionId(sessionId));
    }

    public String findQuestionById(int questionId) {
        return questionRepository.findQuestionById(questionId).get().getText();
    }

    public String getUserScore(String sessionId) {
        User currentUser = userRepository.findUserBySessionId(sessionId).get();
        return String.valueOf(currentUser.getScore());
    }

    public Collection<Answer> findAnswersByQuestionId(int questionId) {
        return questionRepository.findQuestionById(questionId).get().getAnswers();
    }

    public int findNextQuestionIdByAnswerId(int answerId) {
        int nextQuestionId = 0;
        Optional<Answer> optionalAnswer = answerRepository.findAnswerById(answerId);

        if (optionalAnswer.isPresent()) {
            nextQuestionId = optionalAnswer.get().getNextQuestion() != null ?
                    optionalAnswer.get().getNextQuestion().getId() :
                    0;
        }
        if (nextQuestionId != 0 && findQuestionById(nextQuestionId).equals("FINISH")) {
            nextQuestionId = -1;
        }

        return nextQuestionId;
    }

}
