package org.learn.nick.kafka;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.Scanner;

public class SensorProducer {

    public static void main(String[] args) {
        String topicName = "SensorTopic";
        String key = "Key1";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "org.learn.nick.kafka.SensorPartitioner");
        props.put("speed.sensor.name", "TSS");


        Producer<String, String> producer = new KafkaProducer<>(props);

        for(int i=0;i<10;i++) {
            producer.send(new ProducerRecord<>(topicName, "SSN" + i, "500" + i));
        }

        for(int i=0;i<10;i++) {
            producer.send(new ProducerRecord<>(topicName, "TSS", "500" + i));
        }


        producer.close();


        System.out.println("Producer Completed");
    }

}

