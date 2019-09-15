package com.jnscas.statscsgo.utils;

public class Validation {

    public static boolean isValidSteamId(String messageText) {
        return messageText.length() == 17 && messageText.matches("[0-9]\\d*");
    }

}
