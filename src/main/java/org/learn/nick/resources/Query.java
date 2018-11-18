package org.learn.nick.resources;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import org.learn.nick.dtos.Link;
import org.learn.nick.dtos.User;
import org.learn.nick.dtos.Vote;
import org.learn.nick.repository.LinkRepository;
import org.learn.nick.repository.UserRepository;
import org.learn.nick.repository.VoteRepository;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRespository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public Query(LinkRepository linkRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.linkRespository = linkRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public List<Link> allLinks() {
        return linkRespository.allLinks();
    }

    public List<User> allUsers() {
        return userRepository.allUsers();
    }

    public List<Vote> userVotes(String user) {
        return voteRepository.findByUserId(user);
    }
}

