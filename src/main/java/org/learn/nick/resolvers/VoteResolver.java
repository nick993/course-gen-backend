package org.learn.nick.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.learn.nick.repository.LinkRepository;
import org.learn.nick.repository.UserRepository;
import org.learn.nick.dtos.Link;
import org.learn.nick.dtos.User;
import org.learn.nick.dtos.Vote;

public class VoteResolver implements GraphQLResolver<Vote> {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public VoteResolver(LinkRepository linkRepository, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public User user(Vote vote) {
        return userRepository.findByName(vote.getUserId());
    }

    public Link link(Vote vote) {
        return linkRepository.findById(vote.getLinkId());
    }

}
