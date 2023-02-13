package com.orbitz.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableRole.class)
@JsonDeserialize(as = ImmutableRole.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Role {

    @JsonProperty("Name")
    public abstract String getName();

    @JsonProperty("ID")
    public abstract Optional<String> getId();

    @JsonProperty("Description")
    public abstract Optional<String> getDescription();

    @JsonProperty("Policies")
    @JsonDeserialize(as = ImmutableList.class, contentAs = RolePolicyLink.class)
    public abstract List<RolePolicyLink> getPolicies();

    @JsonProperty("ServiceIdentities")
    @JsonDeserialize(as = ImmutableList.class, contentAs = RoleServiceIdentity.class)
    public abstract List<RoleServiceIdentity> getServiceIdentities();

    @JsonProperty("NodeIdentities")
    @JsonDeserialize(as = ImmutableList.class, contentAs = RoleNodeIdentity.class)
    public abstract List<RoleNodeIdentity> getNodeIdentities();

    @JsonProperty("Namespace")
    public abstract Optional<String> getNamespace();

    @Value.Immutable
    @JsonSerialize(as = ImmutableRolePolicyLink.class)
    @JsonDeserialize(as = ImmutableRolePolicyLink.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public abstract static class RolePolicyLink {

        @JsonProperty("ID")
        public abstract Optional<String> getId();

        @JsonProperty("Name")
        public abstract Optional<String> getName();
    }

    @Value.Immutable
    @JsonSerialize(as = ImmutableRoleServiceIdentity.class)
    @JsonDeserialize(as = ImmutableRoleServiceIdentity.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public abstract static class RoleServiceIdentity {

        @JsonProperty("ServiceName")
        public abstract String getName();

        @JsonProperty("Datacenters")
        @JsonDeserialize(as = ImmutableList.class, contentAs = String.class)
        public abstract List<String> getDatacenters();
    }

    @Value.Immutable
    @JsonSerialize(as = ImmutableRoleNodeIdentity.class)
    @JsonDeserialize(as = ImmutableRoleNodeIdentity.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public abstract static class RoleNodeIdentity {

        @JsonProperty("NodeName")
        public abstract String getName();

        @JsonProperty("Datacenter")
        public abstract String getDatacenter();
    }
}
