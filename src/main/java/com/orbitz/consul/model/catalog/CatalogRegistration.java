package com.orbitz.consul.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;
import java.util.Optional;

import com.orbitz.consul.model.agent.Check;
import com.orbitz.consul.model.agent.CheckV2;
import com.orbitz.consul.model.health.Service;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(init = "set*")
@JsonSerialize(as = ImmutableCatalogRegistration.class)
@JsonDeserialize(as = ImmutableCatalogRegistration.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CatalogRegistration {

    @JsonProperty("ID")
    public abstract Optional<String> getId();

    @JsonProperty("Datacenter")
    public abstract Optional<String> getDatacenter();

    @JsonProperty("Node")
    public abstract String getNode();

    @JsonProperty("Address")
    public abstract Optional<String> getAddress();

    @JsonProperty("NodeMeta")
    public abstract Map<String, String> getNodeMeta();

    @JsonProperty("TaggedAddresses")
    public abstract Optional<TaggedAddresses> getTaggedAddresses();

    @JsonProperty("Service")
    public abstract Optional<Service> getService();

    @JsonProperty("Check")
    public abstract Optional<CheckV2> getCheck();

    @JsonProperty("WriteRequest")
    public abstract Optional<WriteRequest> getWriteRequest();

    @JsonProperty("SkipNodeUpdate")
    public abstract Optional<Boolean> getSkipNodeUpdate();
}
