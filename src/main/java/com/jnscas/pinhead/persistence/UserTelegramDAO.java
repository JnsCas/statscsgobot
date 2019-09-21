package com.jnscas.pinhead.persistence;

import com.jnscas.pinhead.entities.UserPinhead;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

public class UserTelegramDAO extends Persistence<UserPinhead> {

    public UserTelegramDAO(MongoDatabase database,
                           String tableName) {
        super(database, tableName, UserPinhead.class);
    }

    public Optional<UserPinhead> findByUserName(String userName) {
        return findByColumn("userName", userName);
    }
}
