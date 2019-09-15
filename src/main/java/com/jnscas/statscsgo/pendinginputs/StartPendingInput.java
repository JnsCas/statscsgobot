package com.jnscas.statscsgo.pendinginputs;

import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.pendinginputs.PendingInput;
import com.jnscas.statscsgo.model.User;
import com.jnscas.statscsgo.persistence.UserDAO;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.logging.Logger;

import static com.jnscas.statscsgo.utils.Validation.isValidSteamId;

public class StartPendingInput implements PendingInput {

    private static final Logger logger = Logger.getLogger(StartPendingInput.class.getSimpleName());

    private UserDAO userDAO;

    public StartPendingInput() {
        this.userDAO = FactoryUserDAO.create();
    }

    @Override
    public SendMessage resolve(ContextBot context) {
        String userName = context.user().getUserName();
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

    private void registerUser(User user, String steamId) {
        user.setSteamId64(steamId);
        userDAO.updateSteamId64(user);
        userDAO.cleanPendingInput(user.getUserName());
    }

    @Override
    public String getPendingInputName() {
        return StartPendingInput.class.getName();
    }
}
