package org.learn.nick.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SupplierDeserializer implements Deserializer<Supplier> {

    private String encoding = "UTF-8";

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Supplier deserialize(String s, byte[] bytes) {

        try {
            if (bytes == null) {
                System.out.println("Null Received at Deserializer");
                return null;
            }
            ByteBuffer buf = ByteBuffer.wrap(bytes);
            int id = buf.getInt();

            int sizeOfName = buf.getInt();
            byte[] nameBytes = new byte[sizeOfName];
            buf.get(nameBytes);
            String name = new String(nameBytes);

            int sizeOfDate = buf.getInt();
            byte[] dateByte = new byte[sizeOfDate];
            buf.get(dateByte);

            SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
            Date date = df.parse(new String(dateByte, encoding));

            return new Supplier(id, name, date);

        } catch(Exception e) {
            throw new SerializationException("Error While deserialization");
        }


    }

    @Override
    public void close() {

    }
}
