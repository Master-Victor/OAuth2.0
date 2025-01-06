package io.openliberty.guides.oauth;

public class GoogleOAuthProvider implements OAuthProvider {

    @Override
    public String getClientId() {
        return "315285799849-ehad03jql8ualoeotimsacd5gq4tm8nd.apps.googleusercontent.com";
    }

    @Override
    public String getClientSecret() {
        return "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";
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