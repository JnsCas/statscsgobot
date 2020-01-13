package com.jnscas.pinhead.model;

import com.jnscas.pinhead.entities.UserPinhead;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class ContextBot {

    private UserPinhead user;
    private Update update;
    private Message message;
    private Long chatId;

    public ContextBot(UserPinhead user,
                      Update update) {
        this.user = user;
        this.update = update;
        this.message = update.getMessage();
        this.chatId = update.getMessage().getChatId();
    }

    public UserPinhead user() {
        return user;
    }

    public Update update() {
        return update;
    }

    public Message message() {
        return message;
    }

    public Long chatId() {
        return chatId;
    }

    public String getFromUsernameOrFirstName() {
        User fromUser = update.getMessage().getFrom();
        String userName = fromUser.getUserName();
        return userName == null ? fromUser.getFirstName() : userName;
    }

    @Override
    public String toString() {
        return "ContextBot{" +
                "user=" + user +
                ", update=" + update +
                ", message=" + message +
                ", chatId=" + chatId +
                '}';
    }
}
