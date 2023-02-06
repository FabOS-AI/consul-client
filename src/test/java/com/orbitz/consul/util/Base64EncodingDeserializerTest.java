package com.orbitz.consul.util;

import com.google.common.io.BaseEncoding;
import com.orbitz.consul.model.event.Event;
import com.orbitz.consul.model.event.ImmutableEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Base64EncodingDeserializerTest {

    @Test
    public void shouldDeserialize() throws IOException {
        String value = RandomStringUtils.randomAlphabetic(12);
        Event event = ImmutableEvent.builder()
                .setId("1")
                .setLTime(1L)
                .setName("name")
                .setVersion(1)
                .setPayload(BaseEncoding.base64().encode(value.getBytes()))
                .build();

        String serializedEvent = Jackson.MAPPER.writeValueAsString(event);
        Event deserializedEvent = Jackson.MAPPER.readValue(serializedEvent, Event.class);

        assertEquals(value, deserializedEvent.getPayload().get());
    }
}
