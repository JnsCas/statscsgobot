package com.jnscas.statscsgo.steam.parser;

import com.jnscas.statscsgo.exceptions.SteamParserException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class SteamParser {

    private static final ObjectMapper mapper = new ObjectMapper(); //FIXME se instancia una sola vez, no? cuando se inicia la app? mandar al constructor?

    public static <T> T parse(String response, Class<T> aClass) {
        try {
            return mapper.readValue(response, aClass);
        } catch (IOException e) {
            throw new SteamParserException(e);
        }
    }

}
