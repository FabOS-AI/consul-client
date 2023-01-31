package com.orbitz.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Optional;
import org.immutables.value.Value;

import java.math.BigInteger;


@Value.Immutable
@JsonSerialize(as = ImmutableAclResponse.class)
@JsonDeserialize(as = ImmutableAclResponse.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AclResponse {

    @JsonProperty("CreateIndex")
    public abstract BigInteger getCreateIndex();

    @JsonProperty("ModifyIndex")
    public abstract BigInteger getModifyIndex();

    @JsonProperty("ID")
    public abstract Optional<String> getId();

    @JsonProperty("Name")
    public abstract Optional<String> getName();

    @JsonProperty("Type")
    public abstract Optional<String> getType();

    @JsonProperty("Rules")
    public abstract Optional<String> getRules();
}
