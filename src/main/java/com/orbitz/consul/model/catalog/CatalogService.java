package com.orbitz.consul.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(init = "set*")
@JsonSerialize(as = ImmutableCatalogService.class)
@JsonDeserialize(as = ImmutableCatalogService.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CatalogService {

    @JsonProperty("ID")
    public abstract UUID getID();

    @JsonProperty("Node")
    public abstract String getNode();

    @JsonProperty("Address")
    public abstract String getAddress();

    @JsonProperty("Datacenter")
    public abstract Optional<String> getDatacenter();

    @JsonProperty("TaggedAddresses")
    public abstract Optional<TaggedAddresses> getTaggedAddresses();

    @JsonProperty("ServiceName")
    public abstract String getServiceName();

    @JsonProperty("ServiceID")
    public abstract String getServiceId();

    @JsonProperty("ServiceAddress")
    public abstract String getServiceAddress();

    @JsonProperty("ServiceEnableTagOverride")
    public abstract Optional<Boolean> getServiceEnableTagOverride();

    @JsonProperty("ServicePort")
    public abstract int getServicePort();

    @JsonProperty("ServiceTags")
    public abstract List<String> getServiceTags();

    @JsonProperty("ServiceMeta")
    public abstract Map<String,String> getServiceMeta();

    @JsonProperty("ServiceWeights")
    public abstract Optional<ServiceWeights> getServiceWeights();

    @JsonProperty("NodeMeta")
    public abstract Map<String,String> getNodeMeta();

    @JsonProperty("CreateIndex")
    public abstract OptionalLong getCreateIndex();

    @JsonProperty("ModifyIndex")
    public abstract OptionalLong getModifyIndex();
}
