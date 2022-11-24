import org.junit.jupiter.api.Test;
import ru.javarush.module3.quest.entity.User;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void methodGetNameShouldReturnFirstParamOfUserConstructor() {
        String exceptedUserName = "User";

        User user = new User(exceptedUserName);

        try {
            Field userName = User.class.getDeclaredField("name");
            userName.setAccessible(true);
            String actualUserName = (String) userName.get(user);
            assertEquals(exceptedUserName, actualUserName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void methodGetScoreShouldReturnDefaultNulIfFirstParamOfUserConstructor() {
        int exceptedScore = 0;

        User user = new User("User");

        try {
            Field userScore = User.class.getDeclaredField("score");
            userScore.setAccessible(true);
            int actualUserScore = (int) userScore.get(user);
            assertEquals(exceptedScore, actualUserScore);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void methodIncrementScoreShouldIncrementByOneScore() {
        int exceptedScore = 1;

        User user = new User("User");
        user.incrementScore();
        int actualScore = user.getScore();
        assertEquals(exceptedScore, actualScore);
    }

    @Test
    void methodResetScoreShouldResetTheScore() {
        int exceptedScore = 0;

        User user = new User("User");
        user.incrementScore();
        user.incrementScore();
        user.resetScore();
        int actualScore = user.getScore();
        assertEquals(exceptedScore, actualScore);
    }

}
