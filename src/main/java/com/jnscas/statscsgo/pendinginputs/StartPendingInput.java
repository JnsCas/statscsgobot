package com.jnscas.statscsgo.pendinginputs;

import com.jnscas.pinhead.entities.UserPinhead;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.pendinginputs.PendingInput;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.persistence.UserStatsDAO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.logging.Logger;

public class StartPendingInput implements PendingInput {

    private static final Logger logger = Logger.getLogger(StartPendingInput.class.getSimpleName());

    private UserStatsDAO userStatsDAO;

    public StartPendingInput() {
        this.userStatsDAO = FactoryUserDAO.create();
    }

    @Override
    public SendMessage resolve(ContextBot context) {
        String userName = context.getFromUsernameOrFirstName();
        Long chatId = context.chatId();
        String steamId = context.message().getText();
        if (isValidSteamId(steamId)) {
            logger.info(String.format("Sign up ok for username: %s", userName));
            registerUser(context.user(), steamId);
            return SendMessageBuilder.newBuilder()
                    .chatId(chatId)
                    .userName(userName)
                    .messageText("Sign up OK!")
                    .build();
        } else {
            return SendMessageBuilder.newBuilder()
                    .chatId(chatId)
                    .userName(userName)
                    .messageText("SteamID64 invalid. Try again")
                    .build();
        }
    }

    private void registerUser(UserPinhead user, String steamId) {
        UserStats userStats = new UserStats(user.getTelegramId());
        userStats.setSteamId64(steamId);
        userStatsDAO.updateSteamId64(userStats);
        userStatsDAO.cleanPendingInput(user.getTelegramId()); //FIXME move this
    }

    public boolean isValidSteamId(String messageText) {
        return messageText.length() == 17 && messageText.matches("[0-9]\\d*");
    }

    @Override
    public String getPendingInputName() {
        return StartPendingInput.class.getName();
    }
}
