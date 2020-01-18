package com.jnscas.statscsgo.exceptions;

public class NotRegisteredExceptionStatsCsGoBot extends RuntimeException {

    public NotRegisteredExceptionStatsCsGoBot(String usernameOrFirstname) {
        super("User not registered. Username or Firstname: " + usernameOrFirstname);
    }

}
