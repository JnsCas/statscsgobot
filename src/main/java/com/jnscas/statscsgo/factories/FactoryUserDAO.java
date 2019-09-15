package com.jnscas.statscsgo.factories;

import com.jnscas.pinhead.factories.MongoDatabaseSingleton;
import com.jnscas.statscsgo.persistence.UserDAO;

public class FactoryUserDAO {

    public static UserDAO create() {
        return new UserDAO(MongoDatabaseSingleton.getInstance(), "users");
    }

}
