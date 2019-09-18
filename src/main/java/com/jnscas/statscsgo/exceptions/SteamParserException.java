package com.jnscas.statscsgo.exceptions;

import java.io.IOException;

public class SteamParserException extends RuntimeException {
    public SteamParserException(IOException e) {
        super("Error in steam parser", e);
    }
}
