package io.openliberty.guides.oauth;

public class GitHubOAuthProvider implements OAuthProvider {

    @Override
    public String getClientId() {
        return "Ov23liPya3bj2vsGdjbT";
    }

    @Override
    public String getClientSecret() {
        return "1c6aefc8aa023a05a4e398578af57f2b12878e4b";
    }

    @Override
    public String getRedirectUri() {
        return "http://127.0.0.1:9080/api/oauth/github";
    }

    @Override
    public String getTokenEndpoint() {
        return "https://github.com/login/oauth/access_token";
    }

    @Override
    public String getUserInfoEndpoint() {
        return "https://api.github.com/user";
    }
}
