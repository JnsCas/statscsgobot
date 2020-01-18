package com.jnscas.statscsgo.factories;

import com.jnscas.statscsgo.steam.SteamClient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class FactorySteamClient {

    public static SteamClient create() {
        Config config = ConfigFactory.defaultApplication();
        return new SteamClient(
                config.getString("services.steam.protocol"),
                config.getString("services.steam.host"),
                config.getString("services.steam.key"),
                config.getString("services.steam.version")
        );
    }
}
