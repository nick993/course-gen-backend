package org.learn.nick.scalars;

import graphql.language.IntValue;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Scalars {

    public static GraphQLScalarType dateTime = new GraphQLScalarType("DateTime", "DateTime Scalar", new Coercing() {
        @Override
        public Object serialize(Object inp) {
            return ((ZonedDateTime) inp).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        @Override
        public Object parseValue(Object inp) {
            return serialize(inp);
        }

        @Override
        public ZonedDateTime parseLiteral(Object input) {
            if (input instanceof StringValue) {
                return ZonedDateTime.parse(((StringValue) input).getValue());
            } else if (input instanceof IntValue) {
                return Instant.ofEpochMilli(((IntValue) input).getValue().longValue()).atZone(ZoneOffset.UTC);
            } else {
                return null;
            }
        }
    });

}
