package com.orbitz.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;

public abstract class BaseRoleResponse {

    @JsonProperty("ID")
    public abstract String getId();

    @JsonProperty("Name")
    public abstract String getName();

    @JsonProperty("Description")
    public abstract String getDescription();

    @JsonProperty("Policies")
    public abstract List<Role.RolePolicyLink> getPolicies();

    @JsonProperty("ServiceIdentities")
    public abstract List<Role.RoleServiceIdentity> getServiceIdentities();

    @JsonProperty("NodeIdentities")
    public abstract List<Role.RoleNodeIdentity> getNodeIdentities();

    @JsonProperty("CreateIndex")
    public abstract BigInteger getCreateIndex();

    @JsonProperty("ModifyIndex")
    public abstract BigInteger getModifyIndex();

    @JsonProperty("Hash")
    public abstract String getHash();

}
