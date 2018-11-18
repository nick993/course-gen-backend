package org.learn.nick.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class SupplierSerializer implements Serializer<Supplier> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topicName, Supplier data) {
        int sizeOfName;
        int sizeOfDate;

        byte[] serializerName;
        byte[] serializerDate;
        try {
            if (data == null) {
                return null;
            }

            serializerName = data.getSupplierName().getBytes(encoding);
            sizeOfName = serializerName.length;

            serializerDate = data.getSupplierDate().toString().getBytes(encoding);
            sizeOfDate = serializerDate.length;

            ByteBuffer buf = ByteBuffer.allocate(4 + 4 + sizeOfName + 4 + sizeOfDate);
            buf.putInt(data.getSuppplierID());
            buf.putInt(sizeOfName);
            buf.put(serializerName);
            buf.putInt(sizeOfDate);
            buf.put(serializerDate);


            return buf.array();

        } catch(Exception e) {
            throw new SerializationException("Error Serializing Supplier to byte.");
        }

    }

    @Override
    public void close() {

    }
}
