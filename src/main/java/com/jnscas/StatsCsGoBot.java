package com.jnscas;

import com.jnscas.commands.Command;
import com.jnscas.entities.UserTelegram;
import com.jnscas.model.ContextBot;
import com.jnscas.persistence.UserTelegramDAO;
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

    private UserTelegramDAO userTelegramDAO;

    public StatsCsGoBot(UserTelegramDAO userTelegramDAO,
                        List<Command> commands) {
        this.userTelegramDAO = userTelegramDAO;
        this.commands = commands;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        ContextBot context = createContextBot(update);
        logger.info(String.format("message received: %s", context));

        //userTelegram.pendingInput().ifPresent(pendingInput -> pendingInput.resolve(update));

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
        }/* else {
            pendingInputs.stream().filter(pendingInput ->
               pendingInput.hasUserActivePending(update.getMessage().getFrom().getUserName())
            ).findFirst().ifPresent(pendingInput -> {
                try {
                    SendMessage sendMessage = pendingInput.resolve(update);
                    sendMessage.validate();
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
        }*/

    }

    private ContextBot createContextBot(Update update) {
        String userName = update.getMessage().getFrom().getUserName();
        Optional<UserTelegram> mayBeUserTelegram = userTelegramDAO.findByUserName(userName);
        if (!mayBeUserTelegram.isPresent()) {
            UserTelegram userTelegram = new UserTelegram(userName);
            return new ContextBot(userTelegram, update);
        } else {
            return new ContextBot(mayBeUserTelegram.get(), update);
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
