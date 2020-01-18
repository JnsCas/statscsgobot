package com.jnscas.statscsgo;

import com.google.common.collect.Lists;
import com.jnscas.pinhead.PinheadMain;
import com.jnscas.statscsgo.commands.MyStatsCommand;
import com.jnscas.statscsgo.commands.MyStatsDetailCommand;
import com.jnscas.statscsgo.commands.StartCommand;
import com.jnscas.statscsgo.factories.FactorySteamClient;
import com.jnscas.statscsgo.factories.FactoryUserDAO;

public class Main extends PinheadMain {

    public static void main(String[] args ) {
        init(() ->
            new StatsCsGoBot(
                Lists.newArrayList(
                    new StartCommand(FactoryUserDAO.create()),
                    new MyStatsCommand(new StatsResolver(), FactorySteamClient.create()),
                    new MyStatsDetailCommand(new StatsResolver(), FactorySteamClient.create())
                ),
                FactoryUserDAO.create()
            )
        );
    }
}
