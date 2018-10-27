/**
 *
 */
package org.learn.nick;
import java.net.UnknownHostException;
import java.util.function.Function;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class MongoContext {
    public static void main(String[] args) {
        // Creating a Mongo client
        MongoClient mongo = MongoClients.create( "mongodb://ds235169.mlab.com:35169");

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("nick", "myDb",
                "nick123".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("myDb");
        System.out.println("Credentials ::"+ credential);
    }
}