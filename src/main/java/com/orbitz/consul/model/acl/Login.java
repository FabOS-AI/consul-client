package com.orbitz.consul.model.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableLogin.class)
@JsonDeserialize(as = ImmutableLogin.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Login {

    @JsonProperty("AuthMethod")
    public abstract String getAuthMethod();

    @JsonProperty("BearerToken")
    public abstract String getBearerToken();

    @JsonProperty("Meta")
    public abstract Optional<Map<String, String>> getMeta();

}
