package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.StatsResolver;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class MyStatsDetailCommand
        extends MyStatsAbstract implements Command {

    private StatsResolver statsResolver;

    public MyStatsDetailCommand() {
        this.statsResolver = new StatsResolver();
    }

    @Override
    public String name() {
        return "/mystatsdetail";
    }

    @Override
    public SendMessage executeCommand(ContextBot context) {
        return executeMyStats(context);
    }

    @Override
    protected String createMyStats(Map<String, Map<String, Integer>> stats) {
        return statsResolver.createStatsDetailUserMessage(stats);
    }
}
