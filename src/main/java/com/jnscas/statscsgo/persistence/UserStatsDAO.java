package com.jnscas.statscsgo.persistence;

import com.jnscas.pinhead.persistence.Persistence;
import com.jnscas.statscsgo.model.UserStats;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

public class UserStatsDAO extends Persistence<UserStats> {

    public UserStatsDAO(MongoDatabase mongoDatabase,
                        String tableName) {
        super(mongoDatabase, tableName, UserStats.class);
    }

    /*
    public Optional<UserStats> findByUserName(String userName) {
        return findByColumn("userName", userName);
    }
     */

    public Optional<UserStats> findBySteamId64(String steamId64) {
        return findByColumn("steamId64", steamId64);
    }

    public Optional<UserStats> findByTelegramId(Integer telegramId) {
        return findByColumn("telegramId", telegramId);
    }

    public void updateSteamId64(UserStats userStats) {
        updateColumn(userStats.getTelegramId(), "steamId64", userStats.getSteamId64());
    }

    public void cleanPendingInput(Integer telegramId) {
        updateColumn(telegramId, "pendingInputName", null);
    }
}
