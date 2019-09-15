package com.jnscas.pinhead.persistence;

import com.jnscas.pinhead.entities.UserTelegram;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

public class UserTelegramDAO extends Persistence<UserTelegram> {

    public UserTelegramDAO(MongoDatabase database,
                           String tableName) {
        super(database, tableName, UserTelegram.class);
    }

    public Optional<UserTelegram> findByUserName(String userName) {
        return findByColumn("userName", userName);
    }
}
