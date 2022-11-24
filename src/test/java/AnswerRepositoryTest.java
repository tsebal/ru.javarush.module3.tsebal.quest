import org.junit.jupiter.api.Test;
import ru.javarush.module3.quest.entity.Answer;
import ru.javarush.module3.quest.repository.AnswerRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerRepositoryTest {
    @Test
    void answerRepositoryConstructorShouldSetIdToAnswerVariableOfFirstParamOfAnswerRepositoryConstructor() {
        Map<Integer, Answer> exceptedIdToAnswer = new HashMap<>();

        Answer answer = new Answer(777, "AnswerText", null);
        exceptedIdToAnswer.put(777, answer);
        AnswerRepository answerRepository = new AnswerRepository(exceptedIdToAnswer);
        try {
            Field idToAnswer = AnswerRepository.class.getDeclaredField("idToAnswer");
            idToAnswer.setAccessible(true);
            Object actualIdToAnswer = idToAnswer.get(answerRepository);
            assertEquals(exceptedIdToAnswer, actualIdToAnswer);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void methodFindAnswerByIdShouldReturnOptionalAnswer() {
        int answerId = 777;
        Map<Integer, Answer> idToAnswer = new HashMap<>();
        Answer answer = new Answer(answerId, "AnswerText", null);
        idToAnswer.put(answerId, answer);
        AnswerRepository answerRepository = new AnswerRepository(idToAnswer);

        Optional<Answer> exceptedOptionalAnswer = Optional.ofNullable(idToAnswer.get(answerId));
        Optional<Answer> actualOptionalAnswer = answerRepository.findAnswerById(answerId);

        assertEquals(exceptedOptionalAnswer, actualOptionalAnswer);
    }

}
