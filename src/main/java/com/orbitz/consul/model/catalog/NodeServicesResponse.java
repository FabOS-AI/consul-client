package com.orbitz.consul.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import com.orbitz.consul.model.health.Node;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableNodeServicesResponse.class)
@JsonDeserialize(as = ImmutableNodeServicesResponse.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NodeServicesResponse {

    @JsonProperty("Node")
    public abstract Node getNodes();

    @JsonProperty("Services")
    public abstract List<NodeService> getServices();

}

