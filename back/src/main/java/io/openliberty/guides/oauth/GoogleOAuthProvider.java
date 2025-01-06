package io.openliberty.guides.oauth;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class GoogleOAuthProvider implements OAuthProvider {

    @Override
    public String getClientId() {
        return "GOOGLE_CLIENT_ID";
    }

    @Override
    public String getClientSecret() {
        return "GOOGLE_CLIENT_SECRET";
    }

    @Override
    public String getRedirectUri() {
        return "http://127.0.0.1:9080/api/oauth/callback";
    }

    @Override
    public String getTokenEndpoint() {
        return "https://oauth2.googleapis.com/token";
    }

    @Override
    public String getUserInfoEndpoint() {
        return "https://openidconnect.googleapis.com/v1/userinfo";
    }
}