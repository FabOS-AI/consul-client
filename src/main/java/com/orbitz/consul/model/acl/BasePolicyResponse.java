package com.orbitz.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public abstract class BasePolicyResponse {

    @JsonProperty("ID")
    public abstract String getId();

    @JsonProperty("Name")
    public abstract String getName();

    @JsonProperty("Description")
    public abstract Optional<String> getDescription();

    @JsonProperty("Datacenters")
    public abstract Optional<List<String>> getDatacenters();

    @JsonProperty("Hash")
    public abstract String getHash();

    @JsonProperty("CreateIndex")
    public abstract BigInteger getCreateIndex();

    @JsonProperty("ModifyIndex")
    public abstract BigInteger getModifyIndex();
}
