package org.learn.nick.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SigninPayload {
    private final String token;
    private final User user;
}
