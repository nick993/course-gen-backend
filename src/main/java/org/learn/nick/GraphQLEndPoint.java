package org.learn.nick;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.learn.env.DBProperties;
import org.learn.env.Environment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndPoint extends SimpleGraphQLServlet {


    private static final LinkRepository linkRepository;
    private static final UserRepository userRepository;
    private static final VoteRepository voteRepository;

    static {

        DBProperties prop = Environment.getEnv();
        MongoCredential cred = MongoCredential.createScramSha1Credential(prop.getUser(), prop.getDbName(), prop.getPassword().toCharArray());
        MongoClientSettings clientSettings = MongoClientSettings.builder().credential(cred).applyConnectionString(new ConnectionString(prop.getAddress())).build();
        MongoClient client = MongoClients.create(clientSettings);

        MongoDatabase mongo = client.getDatabase("mydb");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
        userRepository = new UserRepository(mongo.getCollection("users"));
        voteRepository = new VoteRepository(mongo.getCollection("votes"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCors(resp);
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCors(resp);
        super.doPost(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCors(resp);
        super.doOptions(req, resp);
    }

    private void addCors(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    }


    public static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository, userRepository, voteRepository),
                        new Mutation(linkRepository, userRepository, voteRepository),
                        new SigninResolver(),
                        new VoteResolver(linkRepository, userRepository))
                .scalars(Scalars.dateTime)
                .build()
                .makeExecutableSchema();
    }

    public GraphQLEndPoint() {
        super(buildSchema());
    }
}
