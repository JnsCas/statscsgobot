package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.StatsResolver;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class PistolsCommand implements Command {

    private StatsResolver statsResolver;

    public PistolsCommand() {
        this.statsResolver = new StatsResolver();
    }

    @Override
    public String name() {
        return "/pistols";
    }

    @Override
    public SendMessage executeCommand(ContextBot context) {
        return null;
    }
}
