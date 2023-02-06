package com.orbitz.consul.model.query;

import com.google.common.collect.Lists;
import org.junit.Test;

public class FailoverTest {

    @Test
    public void creatingFailoverWithDatacentersIsValid() {
        ImmutableFailover.builder()
                .setDatacenters(Lists.newArrayList("dc1", "dc2"))
                .build();
    }

    @Test
    public void creatingFailoverWithNearestIsValid() {
        ImmutableFailover.builder()
                .setNearestN(2)
                .build();
    }

    @Test
    public void creatingFailoverWithNearestAndDatacentersIsValid() {
        ImmutableFailover.builder()
                .setDatacenters(Lists.newArrayList("dc1", "dc2"))
                .setNearestN(2)
                .build();
    }
}
