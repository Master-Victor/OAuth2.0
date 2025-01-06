package io.openliberty.guides.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import io.openliberty.guides.oauth.GitHubOAuthProvider;
import io.openliberty.guides.oauth.GoogleOAuthProvider;
import io.openliberty.guides.oauth.LogoutService;
import io.openliberty.guides.oauth.OAuthProvider;
import io.openliberty.guides.oauth.OAuthService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/oauth")
public class AuthResource {

    // private final OAuthProvider provider = new GoogleOAuthProvider(); // Cambia
    // el proveedor aca
    // private final OAuthService oauthService = new OAuthService(provider);
    private final LogoutService logoutService = new LogoutService();

    @GET
    @Path("/callback")
    @Produces(MediaType.TEXT_HTML)
    public String handleCallback(@QueryParam("code") String code) {

        OAuthProvider provider = new GoogleOAuthProvider();
        OAuthService oauthService = new OAuthService(provider);

        try {
            System.out.println("Código recibido: " + code);

            // Intercambiar el código por un token de acceso
            String accessToken = oauthService.exchangeCodeForAccessToken(code);

            // Obtener la información del usuario
            Map<String, Object> userInfo = oauthService.getUserInfo(accessToken);

            // Generar respuesta HTML
            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<script>" +
                    "window.opener.postMessage(" + new ObjectMapper().writeValueAsString(userInfo) + ", '*');" +
                    "window.close();" +
                    "</script>" +
                    "</body>" +
                    "</html>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<h1>Error durante el flujo OAuth</h1>" +
                    "<pre>" + e.getMessage() + "</pre>" +
                    "</body>" +
                    "</html>";
        }
    }

    @GET
    @Path("/logout")
    public Response logout(@QueryParam("provider") String provider) {
        String logoutUrl = logoutService.performLogout(provider);
        // Redirige al usuario al endpoint de logout
        return Response.seeOther(URI.create(logoutUrl)).build();
    }

    @GET
    @Path("/github")
    @Produces(MediaType.TEXT_HTML)
    public String handleCallbackGithub(@QueryParam("code") String code) {

        OAuthProvider provider = new GitHubOAuthProvider();
        OAuthService oauthService = new OAuthService(provider);
    
        try {
            System.out.println("Código recibido: " + code);
    
            // Intercambiar el código por un token de acceso
            String accessToken = oauthService.exchangeCodeForAccessTokenGithub(code);
    
            // Obtener la información del usuario
            Map<String, Object> userInfo = oauthService.getUserInfo(accessToken);
    
            // Generar respuesta HTML
            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<script>" +
                    "window.opener.postMessage(" + new ObjectMapper().writeValueAsString(userInfo) + ", '*');" +
                    "window.close();" +
                    "</script>" +
                    "</body>" +
                    "</html>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<h1>Error durante el flujo OAuth</h1>" +
                    "<pre>" + e.getMessage() + "</pre>" +
                    "</body>" +
                    "</html>";
        }
    }
}