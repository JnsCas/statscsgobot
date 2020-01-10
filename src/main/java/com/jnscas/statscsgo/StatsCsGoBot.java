package com.jnscas.statscsgo;


import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import com.jnscas.statscsgo.exceptions.NotRegisteredExceptionStatsCsGoBot;
import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.persistence.UserStatsDAO;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

public class StatsCsGoBot extends TelegramLongPollingBot {

    private Config config = ConfigFactory.defaultApplication();
    private static Logger logger = LoggerContext.getContext().getLogger(StatsCsGoBot.class.getSimpleName());

    private List<Command> commands;
    private UserStatsDAO userStatsDAO;

    public StatsCsGoBot(List<Command> commands) {
        this.userStatsDAO = FactoryUserDAO.create();
        this.commands = commands;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        ContextBot context = createContextBot(update);
        SendMessage sendMessage = null;
        try {
            if (context.message().isCommand()) {
                Optional<Command> mayBeCommand = searchCommand(context);
                if (mayBeCommand.isPresent()) {
                    Command command = mayBeCommand.get();
                    logger.info(String.format("Command found: %s", command.name()));
                    sendMessage = command.executeCommand(context);
                    logger.info(String.format("Command %s executed", command.name()));
                }
            } else if (context.user().pendingInputExists()) {
                logger.info(String.format("PendingInput found: %s", context.user().getPendingInputName()));
                sendMessage = context.user().getPendingInput().resolve(context);
                logger.info(String.format("PendingInput %s executed", context.user().getPendingInputName()));
                //FIXME add clean pendingInput here?
            }
        } catch (NotRegisteredExceptionStatsCsGoBot e) {
            logger.info(e.getLocalizedMessage());
            sendMessage = SendMessageBuilder.newBuilder()
                    .chatId(context.chatId())
                    .userName(context.getFromUsernameOrFirstName())
                    .messageText("you are not registered. Did you execute '/start' command?")
                    .build();
        }

        try {
            if (sendMessage != null) {
                sendMessage.validate();
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            logger.error("Error with message :" + context.message().getText());
            e.printStackTrace();
        }
    }

    private Optional<Command> searchCommand(ContextBot context) {
        return commands.stream().filter(command ->
                command.name().equalsIgnoreCase(context.message().getText())
        ).findFirst();
    }

    private ContextBot createContextBot(Update update) {
        User user = update.getMessage().getFrom();
        Optional<UserStats> mayBeUser = userStatsDAO.findByTelegramId(user.getId());
        if (!mayBeUser.isPresent()) {
            logger.info("new user connected: " + user);
            UserStats userStats = new UserStats(user.getId());
            return new ContextBot(userStats, update);
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
