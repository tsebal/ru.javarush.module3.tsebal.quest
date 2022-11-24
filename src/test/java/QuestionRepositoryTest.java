import org.junit.jupiter.api.Test;
import ru.javarush.module3.quest.entity.Question;
import ru.javarush.module3.quest.repository.QuestionRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionRepositoryTest {
    @Test
    void questionRepositoryConstructorShouldSetIdToQuestionVariableOfFirstParamOfQuestionRepositoryConstructor() {
        Map<Integer, Question> exceptedIdToQuestion = new HashMap<>();

        Question question = new Question(777, "QuestionText");
        exceptedIdToQuestion.put(777, question);
        QuestionRepository questionRepository = new QuestionRepository(exceptedIdToQuestion);
        try {
            Field idToQuestion = QuestionRepository.class.getDeclaredField("idToQuestion");
            idToQuestion.setAccessible(true);
            Object actualIdToQuestion = idToQuestion.get(questionRepository);
            assertEquals(exceptedIdToQuestion, actualIdToQuestion);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void methodFindQuestionByIdShouldReturnOptionalQuestion() {
        int questionId = 777;
        Map<Integer, Question> idToQuestion = new HashMap<>();
        Question question = new Question(questionId, "AnswerText");
        idToQuestion.put(questionId, question);
        QuestionRepository questionRepository = new QuestionRepository(idToQuestion);

        Optional<Question> exceptedOptionalAnswer = Optional.ofNullable(idToQuestion.get(questionId));
        Optional<Question> actualOptionalAnswer = questionRepository.findQuestionById(questionId);

        assertEquals(exceptedOptionalAnswer, actualOptionalAnswer);
    }

}
