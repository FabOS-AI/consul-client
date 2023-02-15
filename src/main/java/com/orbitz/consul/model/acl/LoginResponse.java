package com.orbitz.consul.model.acl;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Value.Immutable
@JsonSerialize(as = ImmutableLoginResponse.class)
@JsonDeserialize(as = ImmutableLoginResponse.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class LoginResponse {

    @JsonProperty("AccessorID")
    public abstract Optional<String> getAccessorID();

    @JsonProperty("SecretID")
    public abstract String getSecretId();

    @JsonProperty("Description")
    public abstract Optional<String> getDescription();

    @JsonProperty("Policies")
    @JsonDeserialize(as = ImmutableList.class, contentAs = Token.PolicyLink.class)
    public abstract List<Token.PolicyLink> getPolicies();

    @JsonProperty("Roles")
    @JsonDeserialize(as = ImmutableList.class, contentAs = Token.PolicyLink.class)
    public abstract List<Token.RoleLink> getRoles();

    @JsonProperty("ServiceIdentities")
    @JsonDeserialize(as = ImmutableList.class, contentAs = Role.RoleServiceIdentity.class)
    public abstract List<Role.RoleServiceIdentity> getServiceIdentities();

    @JsonProperty("NodeIdentities")
    @JsonDeserialize(as = ImmutableList.class, contentAs = Token.NodeIdentity.class)
    public abstract List<Token.NodeIdentity> getNodeIdentities();

    @JsonProperty("Local")
    public abstract Optional<Boolean> getLocal();

    @JsonProperty("AuthMethod")
    public abstract Optional<String> getAuthMethod();

    @JsonProperty("CreateTime")
    public abstract Optional<Date> getCreateTime();

    @JsonProperty("Hash")
    public abstract Optional<String> getHash();

    @JsonProperty("CreateIndex")
    public abstract OptionalLong getCreateIndex();

    @JsonProperty("ModifyIndex")
    public abstract OptionalLong getModifyIndex();

    @JsonProperty("Namespace")
    public abstract Optional<String> getNamespace();

}
