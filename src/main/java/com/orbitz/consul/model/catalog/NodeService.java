package com.orbitz.consul.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableNodeService.class)
@JsonDeserialize(as = ImmutableNodeService.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NodeService {
    @JsonProperty("ID")
    public abstract String getId();

    @JsonProperty("Service")
    public abstract String getService();

    @JsonProperty("TaggedAddresses")
    public abstract Optional<TaggedAddresses> getTaggedAddresses();

    @JsonProperty("Tags")
    @JsonDeserialize(as = ImmutableList.class, contentAs = String.class)
    public abstract List<String> getTags();

    @JsonProperty("Address")
    public abstract Optional<String> getAddress();

    @JsonProperty("Meta")
    public abstract Map<String, String> getMeta();

    @JsonProperty("Port")
    public abstract Optional<Integer> getPort();

    @JsonProperty("Namespace")
    public abstract Optional<String> getNamespace();
}
