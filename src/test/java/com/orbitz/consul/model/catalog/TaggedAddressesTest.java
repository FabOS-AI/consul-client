package com.orbitz.consul.model.catalog;

import org.junit.Ignore;
import org.junit.Test;

public class TaggedAddressesTest {

    @Test
    public void buildingTaggedAddressWithAllAttributesShouldSucceed() {
        ImmutableTaggedAddresses.builder()
                .setLan("127.0.0.1")
                .setWan("172.217.17.110")
                .build();
    }

    @Test
    public void buildingTaggedAddressWithoutLanAddressShouldSucceed() {
        ImmutableTaggedAddresses.builder()
                .setWan("172.217.17.110")
                .build();
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void buildingTaggedAddressWithoutWanAddressShouldThrow() {
        ImmutableTaggedAddresses.builder()
                .setLan("127.0.0.1")
                .build();
    }

}
