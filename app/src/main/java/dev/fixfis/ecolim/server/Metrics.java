package dev.fixfis.ecolim.server;

import java.util.UUID;

public class Metrics {
    public static String url = "http://144.126.150.234:25555";
    private static UUID userUUID = UUID.fromString("6f24e7c7-4e2a-41ce-8941-065c5c1e469f");
    private static Long idTandaActiva = null;
    public static UUID getUserUUID() {
        if (userUUID == null) throw new RuntimeException("NO HAY UN UNA UUID");
        return userUUID;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Metrics.url = url;
    }

    public static Long getIdTandaActiva() {
        return idTandaActiva;
    }

    public static void setIdTandaActiva(Long idTandaActiva) {
        System.out.println("Se guardo con el id "+idTandaActiva);
        Metrics.idTandaActiva = idTandaActiva;
    }

    public static void setUserUUID(UUID userUUID) {
        Metrics.userUUID = userUUID;
    }
}
