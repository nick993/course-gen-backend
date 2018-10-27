package org.learn.nick;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

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

