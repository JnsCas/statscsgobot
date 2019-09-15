package com.jnscas.pinhead.commands;

import com.jnscas.pinhead.model.ContextBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command {

    String name();

    SendMessage executeCommand(ContextBot context);

}
