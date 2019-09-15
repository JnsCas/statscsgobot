package com.jnscas.statscsgo.persistence;

import com.jnscas.persistence.Persistence;
import com.jnscas.statscsgo.model.User;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO extends Persistence<User> {

    public UserDAO(MongoDatabase mongoDatabase,
                   String tableName) {
        super(mongoDatabase, tableName, User.class);
    }

    public Optional<User> findUserByUserName(String userName) {
        User user = retrieveCollection()
                .find(eq("user_name", userName))
                .first();
        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

}
