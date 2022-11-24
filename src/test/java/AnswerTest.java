import org.junit.jupiter.api.Test;
import ru.javarush.module3.quest.entity.Answer;
import ru.javarush.module3.quest.entity.Question;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {
    @Test
    void answerConstructorShouldSetIdVariableOfFirstParamOfAnswerConstructor() {
        int exceptedAnswerId = 777;

        Answer answer = new Answer(exceptedAnswerId, "AnswerText", null);

        try {
            Field answerId = Answer.class.getDeclaredField("id");
            answerId.setAccessible(true);
            int actualAnswerId = (int) answerId.get(answer);
            assertEquals(exceptedAnswerId, actualAnswerId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void answerConstructorShouldSetTextVariableOfSecondParamOfAnswerConstructor() {
        String exceptedAnswerText = "AnswerText";

        Answer answer = new Answer(777, exceptedAnswerText, null);

        try {
            Field answerText = Answer.class.getDeclaredField("text");
            answerText.setAccessible(true);
            String actualAnswerText = (String) answerText.get(answer);
            assertEquals(exceptedAnswerText, actualAnswerText);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void answerConstructorShouldSetNextQuestionVariableOfThirdParamOfAnswerConstructor() {
        Question exceptedNextQuestion = new Question(777, "QuestionText");

        Answer answer = new Answer(777, "AnswerText", exceptedNextQuestion);

        try {
            Field nextQuestion = Answer.class.getDeclaredField("nextQuestion");
            nextQuestion.setAccessible(true);
            Question actualNextQuestion = (Question) nextQuestion.get(answer);
            assertEquals(exceptedNextQuestion, actualNextQuestion);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void methodGetIdShouldReturnAnswerId() {
        int exceptedAnswerId = 777;

        Answer answer = new Answer(exceptedAnswerId, "QuestionText", null);
        int actualAnswerId = answer.getId();
        assertEquals(exceptedAnswerId, actualAnswerId);
    }

    @Test
    void methodGetTextShouldReturnAnswerText() {
        String exceptedAnswerText = "QuestionText";

        Answer answer = new Answer(777, exceptedAnswerText, null);
        String actualAnswerText = answer.getText();
        assertEquals(exceptedAnswerText, actualAnswerText);
    }

    @Test
    void methodGetNextQuestionShouldReturnNextQuestion() {
        Question exceptedNextQuestion = new Question(777, "QuestionText");
        Answer answer = new Answer(777, "AnswerText", exceptedNextQuestion);

        Question actualNextQuestion = answer.getNextQuestion();
        assertEquals(exceptedNextQuestion, actualNextQuestion);
    }

}
