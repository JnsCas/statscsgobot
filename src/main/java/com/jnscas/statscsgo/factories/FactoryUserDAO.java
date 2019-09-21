package com.jnscas.statscsgo.factories;

import com.jnscas.pinhead.factories.MongoDatabaseSingleton;
import com.jnscas.statscsgo.persistence.UserStatsDAO;

public class FactoryUserDAO {

    public static UserStatsDAO create() {
        return new UserStatsDAO(MongoDatabaseSingleton.getInstance(), "users");
    }

}
