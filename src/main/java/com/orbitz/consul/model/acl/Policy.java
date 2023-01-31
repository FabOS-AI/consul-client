package com.orbitz.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

@Value.Immutable
@Value.Style(init = "set*")
@JsonSerialize(as = ImmutablePolicy.class)
@JsonDeserialize(as = ImmutablePolicy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Policy {

    @JsonProperty("ID")
    public abstract Optional<String> getId();

    @JsonProperty("Description")
    public abstract Optional<String> getDescription();

    @JsonProperty("Name")
    public abstract String getName();

    @JsonProperty("Rules")
    public abstract Optional<String> getRules();

    @JsonProperty("Datacenters")
    @JsonDeserialize(as = ImmutableList.class, contentAs = String.class)
    public abstract Optional<List<String>> getDatacenters();

}
