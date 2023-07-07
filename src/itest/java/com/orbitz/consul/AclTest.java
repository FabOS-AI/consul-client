package com.orbitz.consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.model.acl.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import org.testcontainers.containers.GenericContainer;

public class AclTest {

    public static GenericContainer<?> consulContainerAcl;
    static {
        consulContainerAcl = new GenericContainer<>("hashicorp/consul:latest")
                .withCommand("agent", "-dev", "-client", "0.0.0.0", "--enable-script-checks=true")
                .withExposedPorts(8500)
                .withEnv("CONSUL_LOCAL_CONFIG",
                        "{\n" +
                                "  \"acl\": {\n" +
                                "    \"enabled\": true,\n" +
                                "    \"default_policy\": \"deny\",\n" +
                                "    \"tokens\": {\n" +
                                "      \"master\": \"aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee\"\n" +
                                "    }\n" +
                                "  }\n" +
                                "}"
                );
        consulContainerAcl.start();
    }

    protected static Consul client;

    protected static HostAndPort aclClientHostAndPort = HostAndPort.fromParts("localhost", consulContainerAcl.getFirstMappedPort());

    @BeforeClass
    public static void beforeClass() {
        client = Consul.builder()
                .withHostAndPort(aclClientHostAndPort)
                .withAclToken("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee")
                .withReadTimeoutMillis(Duration.ofSeconds(2).toMillis())
                .build();
    }

    @Test
    public void listPolicies() {
        AclClient aclClient = client.aclClient();
        assertTrue(aclClient.listPolicies().stream().anyMatch(p -> Objects.equals(p.getName(), "global-management")));
    }

    @Test
    public void testCreateAndReadPolicy() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse policy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());
        assertThat(policy.getName(), is(policyName));

        policy = aclClient.readPolicy(policy.getId());
        assertThat(policy.getName(), is(policyName));
    }

    @Test
    public void testCreateAndReadPolicyBygetName() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse policy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());
        assertThat(policy.getName(), is(policyName));

        policy = aclClient.readPolicyByName(policy.getName());
        assertThat(policy.getName(), is(policyName));
    }

    @Test
    public void testUpdatePolicy() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());

        String newPolicyName = UUID.randomUUID().toString();
        aclClient.updatePolicy(createdPolicy.getId(), ImmutablePolicy.builder().setName(newPolicyName).build());

        PolicyResponse updatedPolicy = aclClient.readPolicy(createdPolicy.getId());
        assertThat(updatedPolicy.getName(), is(newPolicyName));
    }

    @Test
    public void testDeletePolicy() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());

        int oldPolicyCount = aclClient.listPolicies().size();
        aclClient.deletePolicy(createdPolicy.getId());
        int newPolicyCount = aclClient.listPolicies().size();

        assertThat(newPolicyCount, is(oldPolicyCount - 1));
    }

    @Test
    public void testCreateAndReadToken() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());

        String tokenDescription = UUID.randomUUID().toString();
        TokenResponse createdToken = aclClient.createToken(ImmutableToken.builder().setDescription(tokenDescription).setLocal(false).addPolicies(ImmutablePolicyLink.builder().setId(createdPolicy.getId()).build()).build());

        TokenResponse readToken = aclClient.readToken(createdToken.accessorId());

        assertThat(readToken.description(), is(tokenDescription));
        assertThat(readToken.policies().get(0).getName().get(), is(policyName));
    }

    @Test
    public void testCreateAndCloneTokenWithNewDescription() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());

        String tokenDescription = UUID.randomUUID().toString();
        TokenResponse createdToken = aclClient.createToken(
                ImmutableToken.builder()
                        .setDescription(tokenDescription)
                        .setLocal(false)
                        .addPolicies(
                                ImmutablePolicyLink.builder()
                                        .setId(createdPolicy.getId())
                                        .build()
                        ).build());

        String updatedTokenDescription = UUID.randomUUID().toString();
        Token updateToken =
                ImmutableToken.builder()
                        .setId(createdToken.accessorId())
                        .setDescription(updatedTokenDescription)
                        .build();

        TokenResponse readToken = aclClient.cloneToken(createdToken.accessorId(), updateToken);

        assertThat(readToken.accessorId(), not(createdToken.accessorId()));
        assertThat(readToken.description(), is(updatedTokenDescription));
    }

    @Test
    public void testCreateAndReadTokenWithCustomIds() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());

        String tokenId = UUID.randomUUID().toString();
        String tokenSecretId = UUID.randomUUID().toString();
        Token token = ImmutableToken.builder()
                .setId(tokenId)
                .setSecretId(tokenSecretId)
                .setLocal(false)
                .addPolicies(
                        ImmutablePolicyLink.builder()
                                .setId(createdPolicy.getId())
                                .build()
                ).build();
        TokenResponse createdToken = aclClient.createToken(token);

        TokenResponse readToken = aclClient.readToken(createdToken.accessorId());

        assertThat(readToken.accessorId(), is(tokenId));
        assertThat(readToken.secretId(), is(tokenSecretId));
    }

    @Test
    public void testReadSelfToken() {
        AclClient aclClient = client.aclClient();

        TokenResponse selfToken = aclClient.readSelfToken();
//        assertThat(selfToken.description(), is("Master Token"));
        assertThat(selfToken.description(), is("Initial Management Token"));
    }

    @Test
    public void testUpdateToken() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());

        TokenResponse createdToken = aclClient.createToken(ImmutableToken.builder().setDescription("none").setLocal(false).addPolicies(ImmutablePolicyLink.builder().setId(createdPolicy.getId()).build()).build());
        String newDescription = UUID.randomUUID().toString();
        aclClient.updateToken(createdToken.accessorId(), ImmutableToken.builder().setLocal(false).setDescription(newDescription).build());

        TokenResponse readToken = aclClient.readToken(createdToken.accessorId());
        assertThat(readToken.description(), is(newDescription));
    }

    @Test
    public void testListTokens() {
        AclClient aclClient = client.aclClient();
        List l = aclClient.listTokens();

        assertTrue(aclClient.listTokens().stream().anyMatch(p -> Objects.equals(p.description(), "Anonymous Token")));
        assertTrue(aclClient.listTokens().stream().anyMatch(p -> Objects.equals(p.description(), "Initial Management Token")));
    }

    @Test
    public void testDeleteToken() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());
        TokenResponse createdToken = aclClient.createToken(ImmutableToken.builder().setDescription(UUID.randomUUID().toString()).setLocal(false).addPolicies(ImmutablePolicyLink.builder().setId(createdPolicy.getId()).build()).build());

        int oldTokenCount = aclClient.listTokens().size();
        aclClient.deleteToken(createdToken.accessorId());

        int newTokenCount = aclClient.listTokens().size();
        assertThat(newTokenCount, is(oldTokenCount - 1));
    }

    @Test
    public void testListRoles() {
        AclClient aclClient = client.aclClient();

        String roleName1 = UUID.randomUUID().toString();
        String roleName2 = UUID.randomUUID().toString();
        aclClient.createRole(ImmutableRole.builder().setName(roleName1).build());
        aclClient.createRole(ImmutableRole.builder().setName(roleName2).build());

        assertTrue(aclClient.listRoles().stream().anyMatch(p -> Objects.equals(p.getName(), roleName1)));
        assertTrue(aclClient.listRoles().stream().anyMatch(p -> Objects.equals(p.getName(), roleName2)));
    }

    @Test
    public void testCreateAndReadRole() {
        AclClient aclClient = client.aclClient();

        String roleName = UUID.randomUUID().toString();
        RoleResponse role = aclClient.createRole(ImmutableRole.builder().setName(roleName).build());

        RoleResponse roleResponse = aclClient.readRole(role.getId());
        assertEquals(role.getId(), roleResponse.getId());
    }

    @Test
    public void testCreateAndReadRoleBygetName() {
        AclClient aclClient = client.aclClient();

        String roleName = UUID.randomUUID().toString();
        RoleResponse role = aclClient.createRole(ImmutableRole.builder().setName(roleName).build());

        RoleResponse roleResponse = aclClient.readRoleByName(role.getName());
        assertEquals(role.getName(), roleResponse.getName());
    }

    @Test
    public void testCreateAndReadRoleWithPolicy() {
        AclClient aclClient = client.aclClient();

        String policyName = UUID.randomUUID().toString();
        PolicyResponse createdPolicy = aclClient.createPolicy(ImmutablePolicy.builder().setName(policyName).build());

        String roleName = UUID.randomUUID().toString();
        RoleResponse role = aclClient.createRole(
                ImmutableRole.builder()
                        .setName(roleName)
                        .addPolicies(
                                ImmutableRolePolicyLink.builder()
                                .setId(createdPolicy.getId())
                                .build()
                        )
                        .build());

        RoleResponse roleResponse = aclClient.readRole(role.getId());
        assertEquals(role.getId(), roleResponse.getId());
        assertEquals(1, roleResponse.getPolicies().size());
        assertTrue(roleResponse.getPolicies().get(0).getId().isPresent());
        assertEquals(createdPolicy.getId(), roleResponse.getPolicies().get(0).getId().get());
    }

    @Test
    public void testUpdateRole() {
        AclClient aclClient = client.aclClient();

        String roleName = UUID.randomUUID().toString();
        String roleDescription = UUID.randomUUID().toString();
        RoleResponse role = aclClient.createRole(
                ImmutableRole.builder()
                        .setName(roleName)
                        .setDescription(roleDescription)
                        .build());

        RoleResponse roleResponse = aclClient.readRole(role.getId());
        assertEquals(roleDescription, roleResponse.getDescription());

        String roleNewDescription = UUID.randomUUID().toString();
        RoleResponse updatedRoleResponse = aclClient.updateRole(roleResponse.getId(),
                ImmutableRole.builder()
                        .setName(roleName)
                        .setDescription(roleNewDescription)
                        .build());

        assertEquals(roleNewDescription, updatedRoleResponse.getDescription());
    }

    @Test
    public void testDeleteRole() {
        AclClient aclClient = client.aclClient();

        String roleName = UUID.randomUUID().toString();
        RoleResponse role = aclClient.createRole(
                ImmutableRole.builder()
                        .setName(roleName)
                        .build());

        RoleResponse roleResponse = aclClient.readRole(role.getId());
        assertEquals(roleName, roleResponse.getName());

        aclClient.deleteRole(roleResponse.getId());

        assertThrows(ConsulException.class, () -> aclClient.readRole(roleResponse.getId()));
    }

}
