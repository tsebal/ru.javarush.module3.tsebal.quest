import org.junit.jupiter.api.Test;
import ru.javarush.module3.quest.entity.Answer;
import ru.javarush.module3.quest.entity.Question;
import ru.javarush.module3.quest.entity.User;
import ru.javarush.module3.quest.repository.AnswerRepository;
import ru.javarush.module3.quest.repository.QuestionRepository;
import ru.javarush.module3.quest.repository.UserRepository;
import ru.javarush.module3.quest.service.GameService;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {
    @Test
    void methodGetInstanceShouldCreateNewSingleGameServiceInstance() {

        GameService expectedGameServiceInstance = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("instance");
            instance.setAccessible(true);
            GameService actualGameServiceInstance = (GameService) instance.get(instance);
            assertEquals(expectedGameServiceInstance, actualGameServiceInstance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void methodGetUserNameShouldReturnUserName() {

        String exceptedUserName = "UserName";
        String sessionId = "777";
        UserRepository userRepository = new UserRepository();
        User user = new User(exceptedUserName);
        userRepository.addUser(sessionId, user);
        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("userRepository");
            instance.setAccessible(true);
            instance.set(gameService, userRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        String actualUserName = gameService.getUserName(sessionId);
        assertEquals(exceptedUserName, actualUserName);
    }

    @Test
    void methodUserIsPresentShouldReturnBooleanResult() {

        boolean exceptedUserIsPresent = true;
        String sessionId = "777";
        UserRepository userRepository = new UserRepository();
        User user = new User("UserName");
        userRepository.addUser(sessionId, user);
        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("userRepository");
            instance.setAccessible(true);
            instance.set(gameService, userRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        boolean actualUserIsPresent = gameService.userIsPresent(sessionId);
        assertEquals(exceptedUserIsPresent, actualUserIsPresent);
    }

    @Test
    void methodGetUserScoreShouldReturnUserScore() {

        String exceptedUserScore = "2";
        String sessionId = "777";
        UserRepository userRepository = new UserRepository();
        User user = new User("UserName");
        user.incrementScore();
        user.incrementScore();
        userRepository.addUser(sessionId, user);
        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("userRepository");
            instance.setAccessible(true);
            instance.set(gameService, userRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        String actualUserScore = gameService.getUserScore(sessionId);
        assertEquals(exceptedUserScore, actualUserScore);
    }

    @Test
    void methodIncrementUserScoreShouldIncrementUserScore() {

        int exceptedUserScore = 1;
        String sessionId = "777";
        UserRepository userRepository = new UserRepository();
        User user = new User("UserName");
        userRepository.addUser(sessionId, user);
        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("userRepository");
            instance.setAccessible(true);
            instance.set(gameService, userRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        gameService.incrementUserScore(sessionId);
        int actualUserScore = Integer.parseInt(gameService.getUserScore(sessionId));
        assertEquals(exceptedUserScore, actualUserScore);
    }

    @Test
    void methodResetUserScoreShouldResetsUserScore() {

        int exceptedUserScore = 0;
        String sessionId = "777";
        UserRepository userRepository = new UserRepository();
        User user = new User("UserName");
        user.incrementScore();
        user.incrementScore();
        userRepository.addUser(sessionId, user);
        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("userRepository");
            instance.setAccessible(true);
            instance.set(gameService, userRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        gameService.resetUserScore(sessionId);
        int actualUserScore = Integer.parseInt(gameService.getUserScore(sessionId));
        assertEquals(exceptedUserScore, actualUserScore);
    }

    @Test
    void methodFindQuestionByIdShouldResetsUserScore() {

        String exceptedQuestionText = "QuestionText";
        int questionId = 777;
        Question question = new Question(questionId, exceptedQuestionText);
        Map<Integer, Question> questionMap = new HashMap<>();
        questionMap.put(questionId, question);
        QuestionRepository questionRepository = new QuestionRepository(questionMap);

        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("questionRepository");
            instance.setAccessible(true);
            instance.set(gameService, questionRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        String actualQuestionText = gameService.findQuestionById(questionId);
        assertEquals(exceptedQuestionText, actualQuestionText);
    }

    @Test
    void methodFindAnswersByQuestionIdShouldReturnAnswerCollection() {

        List<Answer> exceptedAnswerList = new ArrayList<>();
        exceptedAnswerList.add(new Answer(111, "AnswerText", null));
        exceptedAnswerList.add(new Answer(222, "AnswerTextSecond", null));
        int questionId = 777;
        Question question = new Question(questionId, "QuestionText");
        question.setAnswers(exceptedAnswerList);
        Map<Integer, Question> questionMap = new HashMap<>();
        questionMap.put(questionId, question);
        QuestionRepository questionRepository = new QuestionRepository(questionMap);

        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("questionRepository");
            instance.setAccessible(true);
            instance.set(gameService, questionRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        List<Answer> actualAnswerList = (List<Answer>) gameService.findAnswersByQuestionId(questionId);
        assertEquals(exceptedAnswerList, actualAnswerList);
    }

    @Test
    void methodFindNextQuestionIdByAnswerIdShouldReturnNextQuestionId() {

        int exceptedAnswerId = 0;

        int answerId = 777;
        Map<Integer, Answer> idToAnswer = new HashMap<>();
        Answer answer = new Answer(answerId, "AnswerText", null);
        idToAnswer.put(answerId, answer);
        AnswerRepository answerRepository = new AnswerRepository(idToAnswer);

        GameService gameService = GameService.getInstance();

        try {
            Field instance = GameService.class.getDeclaredField("answerRepository");
            instance.setAccessible(true);
            instance.set(gameService, answerRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        int actualAnswerId = gameService.findNextQuestionIdByAnswerId(answerId);
        assertEquals(exceptedAnswerId, actualAnswerId);
    }
}
