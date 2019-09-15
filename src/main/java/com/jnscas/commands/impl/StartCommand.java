package com.jnscas.commands.impl;

import com.jnscas.commands.Command;
import com.jnscas.entities.UserTelegram;
import com.jnscas.model.ContextBot;
import com.jnscas.statscsgo.model.User;
import com.jnscas.statscsgo.persistence.UserDAO;
import com.jnscas.utils.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;
import java.util.logging.Logger;

public class StartCommand implements Command {

    private static final Logger logger = Logger.getLogger(StartCommand.class.toString());

    private UserDAO userDAO;

    public StartCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String name() {
        return "/start";
    }

    @Override
    public SendMessage executeCommand(ContextBot context) {
        String userName = context.userTelegram().getUserName();
        Optional<User> user = userDAO.findUserByUserName(userName);
        if (!user.isPresent()) {
            logger.info(String.format("User %s not exists", userName));
            User userNew = new User(userName, Optional.empty());
            userDAO.store(userNew);
            return SendMessageBuilder.newBuilder()
                    .chatId(context.chatId())
                    .userName(userName)
                    .messageText("Insert your steamID64, please")
                    .build();
        } else {
            return SendMessageBuilder.newBuilder()
                    .chatId(context.chatId())
                    .userName(userName)
                    .messageText("Was already sign up")
                    .build();
        }
    }
}

