package com.orbitz.consul.model.agent;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckTest {

    @Test(expected = IllegalStateException.class)
    public void buildingCheckThrowsIfMissingMethod() {
        ImmutableCheck.builder()
                .setId("id")
                .setInterval("10s")
                .setName("name")
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void buildingCheckWithHttpThrowsIfMissingInterval() {
        ImmutableCheck.builder()
                .setId("id")
                .setHttp("http://foo.local:1337/health")
                .setName("name")
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void buildingCheckWithGrpcThrowsIfMissingInterval() {
        ImmutableCheck.builder()
                .setId("id")
                .setGrpc("localhost:12345")
                .setName("name")
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void buildingCheckWithArgsThrowsIfMissingInterval() {
        ImmutableCheck.builder()
                .setId("id")
                .setArgs(Collections.singletonList("/bin/echo \"hi\""))
                .setName("name")
                .build();
    }

    @Test
    public void severalArgsCanBeAddedToCheck() {
        Check check = ImmutableCheck.builder()
                .setId("id")
                .setArgs(Lists.newArrayList("/bin/echo \"hi\"", "/bin/echo \"hello\""))
                .setInterval("1s")
                .setName("name")
                .build();

        assertTrue("Args should be present in check", check.getArgs().isPresent());
        assertEquals("Check should contain 2 args", 2, check.getArgs().get().size());
    }

    @Test
    public void serviceTagsAreNotNullWhenNotSpecified() {
        Check check = ImmutableCheck.builder()
                .setTtl("")
                .setName("name")
                .setId("id")
                .build();

        assertEquals(Collections.emptyList(), check.getServiceTags());
    }

    @Test
    public void serviceTagsCanBeAddedToCheck() {
        Check check = ImmutableCheck.builder()
                .setTtl("")
                .setName("name")
                .setId("id")
                .addServiceTags("myTag")
                .build();

        assertEquals(Collections.singletonList("myTag"), check.getServiceTags());
    }
}
