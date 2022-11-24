import org.junit.jupiter.api.Test;
import ru.javarush.module3.quest.entity.User;
import ru.javarush.module3.quest.repository.UserRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest {
    @Test
    @SuppressWarnings("unchecked")
    void userRepositoryConstructorShouldSetEmptySessionToUserVariable() {
        int exceptedSessionToUserMapSize = 0;

        UserRepository userRepository = new UserRepository();
        try {
            Field sessionToUser = UserRepository.class.getDeclaredField("sessionToUser");
            sessionToUser.setAccessible(true);
            Map<String, User> sessionToUserMap = (HashMap) sessionToUser.get(userRepository);
            int actualSessionToUserMapSize = sessionToUserMap.size();
            assertEquals(exceptedSessionToUserMapSize, actualSessionToUserMapSize);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void methodAddUserShouldSetSessionToUserVariable() {
        String sessionId = "777";
        User exceptedUser = new User("UserName");
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(sessionId, exceptedUser);

        try {
            Field sessionToUser = UserRepository.class.getDeclaredField("sessionToUser");
            sessionToUser.setAccessible(true);
            Map<String, User> sessionToUserMap = (HashMap) sessionToUser.get(userRepository);
            User actualUser = sessionToUserMap.get(sessionId);
            assertEquals(exceptedUser, actualUser);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void methodFindUserBySessionIdShouldReturnOptionalUser() {
        String sessionId = "777";
        Map<String, User> sessionToUser = new HashMap<>();
        User user = new User("UserName");
        sessionToUser.put(sessionId, user);
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(sessionId, user);

        Optional<User> exceptedOptionalUser = Optional.ofNullable(sessionToUser.get(sessionId));
        Optional<User> actualOptionalUser = userRepository.findUserBySessionId(sessionId);

        assertEquals(exceptedOptionalUser, actualOptionalUser);
    }

}
