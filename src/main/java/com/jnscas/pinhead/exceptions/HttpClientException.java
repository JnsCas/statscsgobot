package com.jnscas.pinhead.exceptions;

import java.io.IOException;

public class HttpClientException extends RuntimeException {
    public HttpClientException(IOException e) {
        super("Error in http client", e);
    }
}
