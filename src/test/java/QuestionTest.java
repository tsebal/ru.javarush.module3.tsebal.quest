import org.junit.jupiter.api.Test;
import ru.javarush.module3.quest.entity.Answer;
import ru.javarush.module3.quest.entity.Question;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    @Test
    void questionConstructorShouldSetIdVariableOfFirstParamOfQuestionConstructor() {
        int exceptedQuestionId = 777;

        Question question = new Question(exceptedQuestionId, "QuestionText");

        try {
            Field questionId = Question.class.getDeclaredField("id");
            questionId.setAccessible(true);
            int actualQuestionId = (int) questionId.get(question);
            assertEquals(exceptedQuestionId, actualQuestionId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void methodGetIdShouldReturnQuestionId() {
        int exceptedQuestionId = 777;

        Question question = new Question(exceptedQuestionId, "QuestionText");
        int actualQuestionId = question.getId();
        assertEquals(exceptedQuestionId, actualQuestionId);
    }

    @Test
    void methodGetTextShouldReturnQuestionText() {
        String exceptedQuestionText = "QuestionText";

        Question question = new Question(777, exceptedQuestionText);
        String actualQuestionText = question.getText();
        assertEquals(exceptedQuestionText, actualQuestionText);
    }

    @Test
    void methodGetAnswersShouldReturnListOfAnswer() {
        List<Answer> exceptedAnswerList = new ArrayList<>();
        exceptedAnswerList.add(new Answer(777, "AnswerText", null));
        exceptedAnswerList.add(new Answer(778, "AnswerTextSecond", null));

        Question question = new Question(777, "QuestionText");
        question.setAnswers(exceptedAnswerList);
        List<Answer> actualAnswerList = question.getAnswers();
        assertEquals(exceptedAnswerList, actualAnswerList);
    }

    @Test
    void methodSetAnswersShouldSetAnswersVariable() {
        List<Answer> exceptedAnswerList = new ArrayList<>();
        exceptedAnswerList.add(new Answer(777, "AnswerText", null));
        exceptedAnswerList.add(new Answer(778, "AnswerTextSecond", null));

        Question question = new Question(777, "QuestionText");
        question.setAnswers(exceptedAnswerList);

        try {
            Field answers = Question.class.getDeclaredField("answers");
            answers.setAccessible(true);
            Object actualAnswerList = answers.get(question);
            assertEquals(exceptedAnswerList, actualAnswerList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
