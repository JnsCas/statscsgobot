package com.jnscas.commands;

import com.jnscas.model.ContextBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command { //FIXME move modules

    String name();

    SendMessage executeCommand(ContextBot context);

}
