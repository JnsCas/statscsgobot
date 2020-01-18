package com.jnscas.statscsgo;

import com.google.common.collect.Lists;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.persistence.UserStatsDAO;
import org.junit.Before;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatsCsGoBotTest {

    StatsCsGoBot statsCsGoBot;
    UserStatsDAO userStatsDAO;

    @Before
    public void init() {
        this.userStatsDAO = mock(UserStatsDAO.class);
        this.statsCsGoBot = new StatsCsGoBot(Lists.newArrayList(), userStatsDAO);
    }

    @Test
    public void createContextBotNewUserOK() {
        Update update = mock(Update.class);
        User user = mock(User.class);
        int telegramId = 123;
        when(user.getId()).thenReturn(telegramId);
        Message message = mock(Message.class);
        when(update.getMessage()).thenReturn(message);
        when(message.getFrom()).thenReturn(user);
        when(userStatsDAO.findByTelegramId(telegramId)).thenReturn(Optional.empty());
        ContextBot contextBot = statsCsGoBot.createContextBot(update);

        assertTrue(contextBot.user().getTelegramId() == telegramId);
    }

    @Test
    public void createContextBotExistsUserOK() {
        Update update = mock(Update.class);
        User user = mock(User.class);
        int telegramId = 123;
        when(user.getId()).thenReturn(telegramId);
        Message message = mock(Message.class);
        when(update.getMessage()).thenReturn(message);
        when(message.getFrom()).thenReturn(user);

        UserStats userStats = new UserStats(telegramId);
        when(userStatsDAO.findByTelegramId(telegramId)).thenReturn(Optional.of(userStats));
        ContextBot contextBot = statsCsGoBot.createContextBot(update);

        assertTrue(contextBot.user().getTelegramId() == telegramId);
    }

}