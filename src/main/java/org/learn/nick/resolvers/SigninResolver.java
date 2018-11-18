package org.learn.nick.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.learn.nick.pojos.vos.SigninPayload;
import org.learn.nick.pojos.dtos.User;

public class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }

}
