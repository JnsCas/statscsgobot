package com.jnscas.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageBuilder {

    private String userName;
    private Long chatId;
    private String messageText;

    private SendMessageBuilder() {

    }

    public static SendMessageBuilder newBuilder() {
        return new SendMessageBuilder();
    }

    public SendMessageBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public SendMessageBuilder chatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public SendMessageBuilder messageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public SendMessage build() {
        return new SendMessage()
                .setText(String.format("@%s: %s", userName, messageText))
                .setChatId(chatId);
    }

}
