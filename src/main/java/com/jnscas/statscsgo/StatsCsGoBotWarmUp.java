package com.jnscas.statscsgo;

import com.google.common.collect.Lists;
import com.jnscas.statscsgo.commands.MyStatsCommand;
import com.jnscas.statscsgo.commands.MyStatsDetailCommand;
import com.jnscas.statscsgo.commands.StartCommand;

public class StatsCsGoBotWarmUp {

    public static StatsCsGoBot init() {
        return new StatsCsGoBot(Lists.newArrayList(
                new StartCommand(),
                new MyStatsCommand(),
                new MyStatsDetailCommand()
        ));
    }
}
