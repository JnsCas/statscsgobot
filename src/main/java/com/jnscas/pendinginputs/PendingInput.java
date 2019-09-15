package com.jnscas.pendinginputs;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface PendingInput {


    boolean hasUserActivePending(String userName);

    SendMessage resolve(Update update);

    String getPendingInputName();
}
