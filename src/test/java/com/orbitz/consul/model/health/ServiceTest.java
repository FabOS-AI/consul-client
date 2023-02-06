package com.orbitz.consul.model.health;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void testEquals(){
        Service one = tagged("a", "b");
        Service two = tagged("a", "b");
        assertEquals(one, two);
    }

    @Test
    public void testNullTags(){
        Service sv = tagged();
        assertTrue(sv.getTags().isEmpty());
    }

    private Service tagged(String ... tags) {
        return ImmutableService
                .builder()
                .setAddress("localhost")
                .setService("service")
                .setId("id")
                .addTags(tags)
                .setPort(4333)
                .build();
    }

}