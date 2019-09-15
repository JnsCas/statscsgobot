package com.jnscas.statscsgo.persistence;

import com.jnscas.pinhead.persistence.Persistence;
import com.jnscas.statscsgo.model.User;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

public class UserDAO extends Persistence<User> {

    public UserDAO(MongoDatabase mongoDatabase,
                   String tableName) {
        super(mongoDatabase, tableName, User.class);
    }

    public Optional<User> findByUserName(String userName) {
        return findByColumn("userName", userName);
    }

    public void updateSteamId64(User user) {
        updateColumn(user.getUserName(), "steamId64", user.getSteamId64());
    }

    public void cleanPendingInput(String userName) {
        updateColumn(userName, "pendingInputName", null);
    }
}
