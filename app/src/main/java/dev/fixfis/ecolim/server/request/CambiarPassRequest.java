package dev.fixfis.ecolim.server.request;

import java.util.UUID;

import dev.fixfis.ecolim.server.Metrics;

public class CambiarPassRequest {
    String newPassword;
    String oldPassword;
    UUID uuid = Metrics.getUserUUID();

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
