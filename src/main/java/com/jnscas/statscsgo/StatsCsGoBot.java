package com.jnscas.statscsgo;


import com.jnscas.pinhead.commands.Command;
import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.model.User;
import com.jnscas.statscsgo.persistence.UserDAO;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

public class StatsCsGoBot extends TelegramLongPollingBot { //FIXME esto deberia ser comun para cualquier bot

    private Config config = ConfigFactory.defaultApplication();
    private static Logger logger = LoggerContext.getContext().getLogger(StatsCsGoBot.class.toString());

    private List<Command> commands;

    private UserDAO userDAO;

    public StatsCsGoBot(List<Command> commands) {
        this.userDAO = FactoryUserDAO.create();
        this.commands = commands;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        ContextBot context = createContextBot(update);
        logger.info(String.format("message received: %s", context));

        if (context.message().isCommand()) {
            commands.stream().filter(command ->
                    command.name().equalsIgnoreCase(context.message().getText())
            ).findFirst().ifPresent(command -> {
                String commandName = command.name();
                try {
                    logger.info(String.format("Command found: %s", commandName));
                    SendMessage sendMessage = command.executeCommand(context);
                    sendMessage.validate();
                    execute(sendMessage);
                    logger.info(String.format("Command %s executed", commandName));
                } catch (TelegramApiException e) {
                    logger.error("Error executing command: " + commandName);
                    e.printStackTrace();
                }
            });
        } else {
            if (context.user().pendingInputExists()) {
                try {
                    logger.info(String.format("PendingInput found: %s", context.user().getPendingInputName()));
                    SendMessage sendMessage = context.user().getPendingInput().resolve(context);
                    sendMessage.validate();
                    execute(sendMessage);
                    logger.info(String.format("PendingInput %s executed", context.user().getPendingInputName()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private ContextBot createContextBot(Update update) {
        String userName = update.getMessage().getFrom().getUserName();
        Optional<User> mayBeUser = userDAO.findByUserName(userName);
        if (!mayBeUser.isPresent()) {
            User user = new User(userName);
            return new ContextBot(user, update);
        } else {
            return new ContextBot(mayBeUser.get(), update);
        }
    }

    @Override
    public String getBotUsername() {
        return config.getString("bot_username");
    }

    @Override
    public String getBotToken() {
        return config.getString("token");
    }
}
