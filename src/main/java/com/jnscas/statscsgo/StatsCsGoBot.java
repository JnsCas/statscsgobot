package com.jnscas.statscsgo;


import com.jnscas.pinhead.Pinhead;
import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.persistence.UserStatsDAO;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Optional;

public class StatsCsGoBot extends Pinhead {

    private static Logger logger = LoggerContext.getContext().getLogger(StatsCsGoBot.class.getSimpleName());
    private final Config config = ConfigFactory.defaultApplication();

    private UserStatsDAO userStatsDAO;

    public StatsCsGoBot(List<Command> commands) {
        super(commands);
        this.userStatsDAO = FactoryUserDAO.create();
    }

    @Override
    public ContextBot createContextBot(Update update) {
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
