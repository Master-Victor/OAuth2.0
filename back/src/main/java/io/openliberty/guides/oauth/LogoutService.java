package io.openliberty.guides.oauth;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class LogoutService {

    private final Config config = ConfigProvider.getConfig();

    /**
     * Realiza el logout basado en el proveedor.
     * 
     * @param provider El proveedor OAuth (por ejemplo: google, github).
     * @return La URL de logout para redirigir al usuario.
     * @throws IllegalArgumentException Si el proveedor es desconocido o la URL no es válida.
     */
    public String performLogout(String provider) {
        String logoutUrl = config.getOptionalValue( provider + ".logout.endpoint", String.class)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor OAuth desconocido: " + provider));
        return logoutUrl;
    }
}
