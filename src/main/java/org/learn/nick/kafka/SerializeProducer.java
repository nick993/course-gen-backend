package org.learn.nick.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.learn.env.Environment;
import org.learn.env.KafkaConfig;

import java.text.SimpleDateFormat;
import java.util.Properties;

public class SerializeProducer {

    public static void main(String[] args) {
        String key = "Key1";

        KafkaConfig kafkaCoonfig = Environment.getKafkaConfig();
        Properties props = kafkaCoonfig.getBasicProperties();

        String topicName = kafkaCoonfig.prefixTopicName("SimpleProducer");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.learn.nick.kafka.SupplierSerializer");


        Producer<String, Supplier> producer = new KafkaProducer<>(props);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        try {
            producer.send(new ProducerRecord<>(topicName, key, new Supplier(12333, "SupName", df.parse("2018-01-04"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        producer.close();


        System.out.println("Producer Completed");
    }

}

