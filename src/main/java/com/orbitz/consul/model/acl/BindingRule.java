package com.orbitz.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.math.BigInteger;
import java.util.Optional;
import java.util.OptionalLong;

@Value.Immutable
@JsonSerialize(as = ImmutableBindingRule.class)
@JsonDeserialize(as = ImmutableBindingRule.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BindingRule {
    @JsonProperty("ID")
    public abstract Optional<String> getId();

    @JsonProperty("Description")
    public abstract Optional<String> getDescription();

    @JsonProperty("AuthMethod")
    public abstract String getAuthMethod();

    @JsonProperty("Selector")
    public abstract Optional<String> getSelector();

    @JsonProperty("BindType")
    public abstract String getBindType();

    @JsonProperty("BindName")
    public abstract String getBindName();

    @JsonProperty("CreateIndex")
    public abstract OptionalLong getCreateIndex();

    @JsonProperty("ModifyIndex")
    public abstract OptionalLong getModifyIndex();
}
