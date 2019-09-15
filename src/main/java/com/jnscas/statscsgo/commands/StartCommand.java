package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.pendinginputs.StartPendingInput;
import com.jnscas.statscsgo.model.User;
import com.jnscas.statscsgo.persistence.UserDAO;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;
import java.util.logging.Logger;

public class StartCommand implements Command {

    private static final Logger logger = Logger.getLogger(StartCommand.class.toString());

    private UserDAO userDAO;

    public StartCommand() {
        this.userDAO = FactoryUserDAO.create();
    }

    @Override
    public String name() {
        return "/start";
    }

    @Override
    public SendMessage executeCommand(ContextBot context) {
        String userName = context.user().getUserName();
        Optional<User> mayBeUser = userDAO.findByUserName(userName);
        if (!mayBeUser.isPresent()) {
            logger.info(String.format("User %s not exists", userName));
            User userNew = context.user();
            userNew.setPendingInput(new StartPendingInput());
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
                    .messageText(mayBeUser.get().isAlreadyRegistered() ?
                            "Was already sign up" : "Insert your steamID64, please")
                    .build();
        }
    }
}

