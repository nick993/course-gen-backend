package org.learn.nick.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.learn.env.Environment;
import org.learn.env.KafkaConfig;

import java.util.Arrays;
import java.util.Properties;

public class SupplierConsumer {

    public static void main(String[] args) {

        KafkaConfig kafkaConfig = Environment.getKafkaConfig();

        String topicName = kafkaConfig.prefixTopicName("SimpleProducer");




        Properties props = kafkaConfig.getBasicProperties();
        props.put("group.id", kafkaConfig.getUserName() + "-consumer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.learn.nick.kafka.SupplierDeserializer");

        KafkaConsumer<String, Supplier> consumer = new KafkaConsumer<String, Supplier>(props);
        consumer.subscribe(Arrays.asList(topicName));

        while(true) {
            ConsumerRecords<String, Supplier> records = consumer.poll(100);
            records.forEach(record -> {
                System.out.println("ID : " + record.value().getSuppplierID() + " : Name : " + record.value().getSupplierName() + " : Date : " + record.value().getSupplierDate());
            });
        }
    }

}
