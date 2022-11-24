package ru.javarush.module3.quest.repository;

import ru.javarush.module3.quest.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    private final Map<String, User> sessionToUser;

    public UserRepository() {
        this.sessionToUser = new HashMap<>();
    }

    public synchronized void addUser(String sessionId, User user) {
        sessionToUser.put(sessionId, user);
    }

    public Optional<User> findUserBySessionId(String sessionId) {
        return Optional.ofNullable(sessionToUser.get(sessionId));
    }
}
