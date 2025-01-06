package io.openliberty.guides.oauth;

public interface OAuthProvider {
    String getClientId();
    String getClientSecret();
    String getRedirectUri();
    String getTokenEndpoint();
    String getUserInfoEndpoint();
}