package com.orbitz.consul.model.agent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Optional;

import org.immutables.value.Value;

import static com.google.common.base.Preconditions.checkState;

@Value.Immutable
@Value.Style(init = "set*")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(as = ImmutableCheckV2.class)
@JsonDeserialize(as = ImmutableCheckV2.class)
public abstract class CheckV2 {

    @JsonProperty("Node")
    public abstract Optional<String> getNode();

    @JsonProperty("CheckId")
    public abstract Optional<String> getId();

    @JsonProperty("Name")
    public abstract Optional<String> getName();

    @JsonProperty("Notes")
    public abstract Optional<String> getNotes();

    @JsonProperty("Status")
    public abstract Optional<String> getStatus();

    @JsonProperty("ServiceID")
    public abstract Optional<String> getServiceId();

    @JsonProperty("Namespace")
    public abstract Optional<String> getNamespace();

    @JsonProperty("Definition")
    public abstract Optional<CheckDefinition> getDefinition();


}
