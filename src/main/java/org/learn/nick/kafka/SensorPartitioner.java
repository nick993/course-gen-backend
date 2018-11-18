package org.learn.nick.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class SensorPartitioner implements Partitioner {

    private String speedSensorName;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int noPartitions = partitions.size();
        int sp = (int)Math.abs(noPartitions * 0.3);
        int p = 0;

        if (keyBytes == null || (!(key instanceof String))) {
            throw new InvalidRecordException("All messages must have sensor name as Key");
        }

        if (key.equals(speedSensorName)) {
            p = Utils.toPositive(Utils.murmur2(valueBytes)) % sp;
        } else {
            p = Utils.toPositive(Utils.murmur2(keyBytes)) % (noPartitions - sp) + sp;
        }

        System.out.println("Key : " + key + " : Partition : " + p);
        return p;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {
        speedSensorName = map.get("speed.sensor.name").toString();
    }
}
