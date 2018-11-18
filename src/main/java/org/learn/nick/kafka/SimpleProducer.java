package org.learn.nick.kafka;


import org.apache.kafka.clients.producer.*;
import org.learn.env.Environment;
import org.learn.env.KafkaConfig;

import java.util.Properties;
import java.util.Scanner;

public class SimpleProducer {

    public static void main(String[] args) {
        KafkaConfig config = Environment.getKafkaConfig();

        String topicName = config.prefixTopicName("default");
        String key = "Key1";

        Properties props = config.getBasicProperties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Text to Send : ");
        while(true) {
            String str1 = scn.nextLine();
            if (str1.equals("!quit")) {
                break;
            }
            ProducerCallback callback = new ProducerCallback();
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, str1);
            producer.send(record, callback);


        }
        producer.close();

        System.out.println("Producer Completed");
    }

}

class ProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            System.out.println("Asynchronous Error from Producer");
        } else {
            System.out.println("Asynchronous callback Success");
        }
    }
}
