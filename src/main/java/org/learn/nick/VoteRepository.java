package org.learn.nick;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class VoteRepository {

    private final MongoCollection<Document> votes;

    public VoteRepository(MongoCollection<Document> votes) {
        this.votes = votes;
    }

    public List<Vote> findByUserId(String userId) {
        return StreamSupport.stream(votes.find(eq("userId", userId)).spliterator(), true)
                .map(VoteRepository::vote)
                .collect(Collectors.toList());

    }

    public List<Vote> findByLinkId(String linkId) {
        return StreamSupport.stream(votes.find(eq("linkId", linkId)).spliterator(), true)
                .map(VoteRepository::vote)
                .collect(Collectors.toList());
    }

    public Vote saveVote(Vote vote) {
        Document doc = new Document().append("userId", vote.getUserId())
                .append("linkId", vote.getLinkId())
                .append("createdAt", Scalars.dateTime.getCoercing().serialize(vote.getCreatedAt()));

        votes.insertOne(doc);

        return new Vote(
                doc.get("_id").toString(),
                vote.getCreatedAt(),
                vote.getUserId(),
                vote.getLinkId()
        );

    }

    private static Vote vote(Document doc) {
        return new Vote(
                doc.get("_id").toString(),
                ZonedDateTime.parse(doc.getString("createdAt")),
                doc.getString("userId"),
                doc.getString("linkId")
        );
    }

}
