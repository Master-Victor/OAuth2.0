package io.openliberty.guides.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class OAuthService {

    private final OAuthProvider provider;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OAuthService(OAuthProvider provider) {
        this.provider = provider;
    }

    public String exchangeCodeForAccessToken(String code) throws Exception {
        String formData = String.format(
                "code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
                URLEncoder.encode(code, StandardCharsets.UTF_8),
                URLEncoder.encode(provider.getClientId(), StandardCharsets.UTF_8),
                URLEncoder.encode(provider.getClientSecret(), StandardCharsets.UTF_8),
                URLEncoder.encode(provider.getRedirectUri(), StandardCharsets.UTF_8));

        HttpRequest tokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(provider.getTokenEndpoint()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        HttpResponse<String> tokenResponse = client.send(tokenRequest, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> tokenData = objectMapper.readValue(tokenResponse.body(), Map.class);
        return (String) tokenData.get("access_token");
    }

    public String exchangeCodeForAccessTokenGithub(String code) throws Exception {
        String formData = String.format(
                "client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
                URLEncoder.encode(provider.getClientId(), StandardCharsets.UTF_8),
                URLEncoder.encode(provider.getClientSecret(), StandardCharsets.UTF_8),
                URLEncoder.encode(code, StandardCharsets.UTF_8),
                URLEncoder.encode(provider.getRedirectUri(), StandardCharsets.UTF_8));

        System.out.println("Enviando redirect_uri: " + provider.getRedirectUri());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(provider.getTokenEndpoint()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verifica si la respuesta contiene un error
        if (response.body().contains("error")) {
            System.err.println("Error en la respuesta del token: " + response.body());
            throw new RuntimeException("Error en el flujo OAuth: " + response.body());
        }

        // Procesa la respuesta como application/x-www-form-urlencoded
        Map<String, String> tokenResponse = parseFormEncoded(response.body());

        System.out.println("Respuesta del token: " + tokenResponse);

        // Devuelve el access_token
        return tokenResponse.get("access_token");
    }

    public Map<String, Object> getUserInfo(String accessToken) throws Exception {
        HttpRequest userInfoRequest = HttpRequest.newBuilder()
                .uri(URI.create(provider.getUserInfoEndpoint()))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> userInfoResponse = client.send(userInfoRequest, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(userInfoResponse.body(), Map.class);
    }

    private Map<String, String> parseFormEncoded(String body) {
        Map<String, String> map = new java.util.HashMap<>();
        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = java.net.URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                String value = java.net.URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                map.put(key, value);
            }
        }
        return map;
    }
}
