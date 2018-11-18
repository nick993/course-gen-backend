package org.learn.nick.pojos.vos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.learn.nick.pojos.dtos.User;

@AllArgsConstructor
@Getter
public class SigninPayload {
    private final String token;
    private final User user;
}
