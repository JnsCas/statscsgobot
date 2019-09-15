package com.jnscas.persistence;

import com.jnscas.entities.UserTelegram;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

public class UserTelegramDAO extends Persistence<UserTelegram> {

    public UserTelegramDAO(MongoDatabase database,
                           String tableName) {
        super(database, tableName, UserTelegram.class);
    }

    public Optional<UserTelegram> findByUserName(String userName) {
        return findByColumn("user_name", userName);
    }
}
