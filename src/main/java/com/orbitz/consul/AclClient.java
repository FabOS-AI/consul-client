package com.orbitz.consul;

import com.orbitz.consul.config.ClientConfig;
import com.orbitz.consul.model.acl.*;
import com.orbitz.consul.monitoring.ClientEventCallback;
import com.orbitz.consul.option.RoleOptions;
import com.orbitz.consul.option.TokenQueryOptions;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public class AclClient extends BaseClient {

    private static String CLIENT_NAME = "acl";

    private final Api api;

    AclClient(Retrofit retrofit, ClientConfig config, ClientEventCallback eventCallback) {
        super(CLIENT_NAME, config, eventCallback);
        this.api = retrofit.create(Api.class);
    }

    @Deprecated
    public String createAcl(AclToken aclToken) {
        return http.extract(api.createAcl(aclToken)).id();
    }

    @Deprecated
    public void updateAcl(AclToken aclToken) {
        http.handle(api.updateAcl(aclToken));
    }

    @Deprecated
    public void destroyAcl(String id) {
        http.handle(api.destroyAcl(id));
    }

    @Deprecated
    public List<AclResponse> getAclInfo(String id) {
        return http.extract(api.getAclInfo(id));
    }

    @Deprecated
    public String cloneAcl(String id) {
        return http.extract(api.cloneAcl(id)).id();
    }

    @Deprecated
    public List<AclResponse> listAcls() {
        return http.extract(api.listAcls());
    }

    public PolicyResponse createPolicy(Policy policy) {
        return http.extract(api.createPolicy(policy));
    }

    public PolicyResponse readPolicy(String id) {
        return http.extract(api.readPolicy(id));
    }

    public PolicyResponse readPolicyByName(String name) {
        return http.extract(api.readPolicyByName(name));
    }

    public PolicyResponse updatePolicy(String id, Policy policy) {
        return http.extract(api.updatePolicy(id, policy));
    }

    public void deletePolicy(String id) {
        http.extract(api.deletePolicy(id));
    }

    public List<PolicyResponse> listPolicies() {
        return http.extract(api.listPolicies());
    }

    public TokenResponse createToken(Token token) {
        return http.extract(api.createToken(token));
    }

    public TokenResponse cloneToken(String id, Token token) {
        return http.extract(api.cloneToken(id, token));
    }

    public TokenResponse readToken(String id) {
        return http.extract(api.readToken(id));
    }

    public TokenResponse readSelfToken() {
        return http.extract(api.readToken("self"));
    }

    public TokenResponse updateToken(String id, Token token) {
        return http.extract(api.updateToken(id, token));
    }

    public List<TokenListResponse> listTokens() {
        return listTokens(TokenQueryOptions.BLANK);
    }

    public List<TokenListResponse> listTokens(TokenQueryOptions queryOptions) {
        return http.extract(api.listTokens(queryOptions.toQuery()));
    }

    public void deleteToken(String id) {
        http.extract(api.deleteToken(id));
    }

    public RoleResponse createRole(Role token) {
        return http.extract(api.createRole(token));
    }

    public RoleResponse readRole(String id) {
        return http.extract(api.readRole(id));
    }

    public RoleResponse readRoleByName(String name) {
        return http.extract(api.readRoleByName(name));
    }

    public RoleResponse updateRole(String id, Role role) {
        return http.extract(api.updateRole(id, role));
    }

    public List<RoleListResponse> listRoles() {
        return listRoles(RoleOptions.BLANK);
    }

    public List<RoleListResponse> listRoles(RoleOptions roleOptions) {
        return http.extract(api.listRoles(roleOptions.toQuery()));
    }

    public void deleteRole(String id) {
        http.extract(api.deleteRole(id));
    }

    public BindingRule readBindingRule(String id){return http.extract(api.readBindingRule(id));}

    public List<BindingRule> listBindingRules(){return http.extract(api.listBindingRules());};

    public BindingRule createBindingRule(BindingRule bindingRule){return http.extract(api.createBindingRule(bindingRule));}
    public Boolean deleteBindingRule(String id){return http.extract(api.deleteBindingRule(id));}

    public LoginResponse login(Login login){return http.extract(api.login(login));}

    public void logout(){api.logout();}

    interface Api {

        @Deprecated
        @PUT("acl/create")
        Call<AclTokenId> createAcl(@Body AclToken aclToken);

        @Deprecated
        @PUT("acl/update")
        Call<Void> updateAcl(@Body AclToken aclToken);

        @Deprecated
        @PUT("acl/destroy/{id}")
        Call<Void> destroyAcl(@Path("id") String id);

        @Deprecated
        @GET("acl/info/{id}")
        Call<List<AclResponse>> getAclInfo(@Path("id") String id);

        @Deprecated
        @PUT("acl/clone/{id}")
        Call<AclTokenId> cloneAcl(@Path("id") String id);

        @Deprecated
        @GET("acl/list")
        Call<List<AclResponse>> listAcls();

        @PUT("acl/policy")
        Call<PolicyResponse> createPolicy(@Body Policy policy);

        @GET("acl/policy/{id}")
        Call<PolicyResponse> readPolicy(@Path("id") String id);

        @GET("acl/policy/name/{name}")
        Call<PolicyResponse> readPolicyByName(@Path("name") String name);

        @PUT("acl/policy/{id}")
        Call<PolicyResponse> updatePolicy(@Path("id") String id, @Body Policy policy);

        @DELETE("acl/policy/{id}")
        Call<Void> deletePolicy(@Path("id") String id);

        @GET("acl/policies")
        Call<List<PolicyResponse>> listPolicies();

        @PUT("acl/token")
        Call<TokenResponse> createToken(@Body Token token);

        @PUT("acl/token/{id}/clone")
        Call<TokenResponse> cloneToken(@Path("id") String id, @Body Token token);

        @GET("acl/token/{id}")
        Call<TokenResponse> readToken(@Path("id") String id);

        @PUT("acl/token/{id}")
        Call<TokenResponse> updateToken(@Path("id") String id, @Body Token token);

        @GET("acl/tokens")
        Call<List<TokenListResponse>> listTokens(@QueryMap Map<String, Object> query);

        @DELETE("acl/token/{id}")
        Call<Void> deleteToken(@Path("id") String id);

        @PUT("acl/role")
        Call<RoleResponse> createRole(@Body Role role);

        @GET("acl/role/{id}")
        Call<RoleResponse> readRole(@Path("id") String id);

        @GET("acl/role/name/{name}")
        Call<RoleResponse> readRoleByName(@Path("name") String name);

        @PUT("acl/role/{id}")
        Call<RoleResponse> updateRole(@Path("id") String id, @Body Role role);

        @DELETE("acl/role/{id}")
        Call<Void> deleteRole(@Path("id") String id);

        @GET("acl/roles")
        Call<List<RoleListResponse>> listRoles(@QueryMap Map<String, Object> query);

        @GET("acl/binding-rule/{id}")
        Call<BindingRule> readBindingRule(@Path("id") String id);

        @GET("acl/binding-rules")
        Call<List<BindingRule>> listBindingRules();

        @PUT("acl/binding-rule")
        Call<BindingRule> createBindingRule(@Body BindingRule bindingRule);

        @DELETE("acl/binding-rule/{id}")
        Call<Boolean> deleteBindingRule(@Path("id") String id);

        @POST("acl/login")
        Call<LoginResponse> login(@Body Login login);

        @POST("acl/logout")
        Call<Void> logout();
    }

}
