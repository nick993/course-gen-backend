package org.learn.env;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Properties;

@Data
public class KafkaConfig {
    @NonNull
    String userName;
    @NonNull
    String password;
    @NonNull
    List<String> bootstrapServers;

    public String prefixTopicName(String topicName) {
        return userName + "-" + topicName;
    }

    private String getJaasConfig() {
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        return String.format(jaasTemplate, userName, password);
    }

    public Properties getBasicProperties() {
        Properties props = new Properties();
        String bootstrapServersString = bootstrapServers.stream()
                .reduce("", (out, s) -> out + "," + s)
                .substring(1);
        props.put("bootstrap.servers", bootstrapServersString);
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", getJaasConfig());
        return props;
    }


}

