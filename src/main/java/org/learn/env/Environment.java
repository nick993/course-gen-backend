package org.learn.env;

import org.learn.nick.pojos.vos.DBProperties;

import java.util.Arrays;

public class Environment {

    private static DBProperties PROD_DB = new DBProperties("mongodb://ds235169.mlab.com:35169", "mydb", "nick", "nick123");
    private static DBProperties DEV_DB = new DBProperties("mongodb://localhost:27017", "mydb", "nick", "nick123");
    private static KafkaConfig KAFKA_PROD_CONFIG = new KafkaConfig("otz0hcrf", "A-Bzz9JOkWXZDZwarjJyogYt2R0B5qt4", Arrays.asList("ark-01.srvs.cloudkafka.com:9094",
            "ark-02.srvs.cloudkafka.com:9094", "ark-03.srvs.cloudkafka.com:9094"));
    private static KafkaConfig KAFKA_LOCAL_CONFIG = new KafkaConfig(Arrays.asList("ark-01.srvs.cloudkafka.com:9094",
            "ark-02.srvs.cloudkafka.com:9094", "ark-03.srvs.cloudkafka.com:9094"));

    private static boolean isProd() {
        return true;
    }

    public static DBProperties getEnv() {
        if (isProd()) {
            return PROD_DB;
        }
        return DEV_DB;
    }

    public static KafkaConfig getKafkaConfig() {
        return isProd() ? KAFKA_PROD_CONFIG : KAFKA_PROD_CONFIG;
    }


}
