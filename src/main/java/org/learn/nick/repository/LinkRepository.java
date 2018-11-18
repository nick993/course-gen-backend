package org.learn.nick.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.learn.nick.dtos.Link;


import static com.mongodb.client.model.Filters.eq;


import java.util.ArrayList;
import java.util.List;

public class LinkRepository {

    private final MongoCollection<Document> links;

    public LinkRepository(MongoCollection<Document> links) {
        this.links = links;
    }

    public Link findById(String id) {
        Document doc = links.find(eq("_id", new ObjectId(id))).first();
        return link(doc);
    }

    public List<Link> allLinks() {
        List<Link> allLinks = new ArrayList<>();
        for (Document doc : links.find().sort(new BasicDBObject("_id", -1))) {
            allLinks.add(link(doc));
        }
        return allLinks;
    }

    public Link saveLink(Link link) {
        Document doc = new Document();
        doc.append("url", link.getUrl());
        doc.append("description", link.getDescription());
        links.insertOne(doc);
        Link returnedLink = new Link(doc.getObjectId("_id").toString(), doc.getString("url"), doc.getString("description"));
        return returnedLink;
    }
    private Link link(Document doc) {
        return new Link(
                doc.get("_id").toString(),
                doc.getString("url"),
                doc.getString("description"));
    }
}
