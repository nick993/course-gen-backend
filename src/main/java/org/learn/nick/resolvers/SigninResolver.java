package org.learn.nick.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.learn.nick.dtos.SigninPayload;
import org.learn.nick.dtos.User;

public class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }

}
