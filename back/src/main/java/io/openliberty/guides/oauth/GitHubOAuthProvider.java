package io.openliberty.guides.oauth;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class GitHubOAuthProvider implements OAuthProvider {

    @Override
    public String getClientId() {
        return "GitHub_Client_ID";
    }

    @Override
    public String getClientSecret() {
        return "GitHub_Client_Secret";
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
