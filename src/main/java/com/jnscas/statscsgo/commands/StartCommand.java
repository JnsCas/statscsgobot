package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.pendinginputs.StartPendingInput;
import com.jnscas.statscsgo.persistence.UserStatsDAO;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;
import java.util.logging.Logger;

public class StartCommand implements Command {

    private static final Logger logger = Logger.getLogger(StartCommand.class.toString());

    private UserStatsDAO userStatsDAO;

    public StartCommand() {
        this.userStatsDAO = FactoryUserDAO.create();
    }

    @Override
    public String name() {
        return "/start";
    }

    @Override
    public SendMessage executeCommand(ContextBot context) {
        UserStats userStats= context.user();
        Optional<UserStats> mayBeUser = userStatsDAO.findByTelegramId(userStats.getTelegramId());
        if (!mayBeUser.isPresent()) {
            logger.info(String.format("User %s not exists", context.getFromUsernameOrFirstName()));
            UserStats userStatsNew = context.user();
            userStatsNew.setPendingInput(new StartPendingInput());
            userStatsDAO.store(userStatsNew);
            return SendMessageBuilder.newBuilder()
                    .chatId(context.chatId())
                    .userName(context.getFromUsernameOrFirstName())
                    .messageText("Insert your steamID64, please")
                    .build();
        } else {
            return SendMessageBuilder.newBuilder()
                    .chatId(context.chatId())
                    .userName(context.getFromUsernameOrFirstName())
                    .messageText(mayBeUser.get().isAlreadyRegistered() ?
                            "Was already sign up" : "Insert your steamID64, please")
                    .build();
        }
    }
}

