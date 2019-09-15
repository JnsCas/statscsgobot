package com.jnscas.pinhead.pendinginputs;

import com.jnscas.pinhead.model.ContextBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface PendingInput {


    SendMessage resolve(ContextBot context);

    String getPendingInputName();
}
