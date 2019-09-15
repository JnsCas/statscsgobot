package com.jnscas.statscsgo.model;

import com.google.common.collect.Lists;
import com.jnscas.statscsgo.StatsCsGoBot;
import com.jnscas.statscsgo.commands.StartCommand;

public class StatsCsGoBotWarmUp {

    public static StatsCsGoBot init() {
        return new StatsCsGoBot(Lists.newArrayList(new StartCommand()));
    }
}
