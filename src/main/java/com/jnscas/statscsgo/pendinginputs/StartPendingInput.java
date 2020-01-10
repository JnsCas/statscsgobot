package com.jnscas.statscsgo.pendinginputs;

import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.pendinginputs.PendingInput;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.persistence.UserStatsDAO;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.logging.Logger;

import static com.jnscas.statscsgo.utils.Validation.isValidSteamId;

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

    private void registerUser(UserStats userStats, String steamId) {
        userStats.setSteamId64(steamId);
        userStatsDAO.updateSteamId64(userStats);
        userStatsDAO.cleanPendingInput(userStats.getTelegramId()); //FIXME move this?
    }

    @Override
    public String getPendingInputName() {
        return StartPendingInput.class.getName();
    }
}
