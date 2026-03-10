package dev.fixfis.ecolim.server.request;

import dev.fixfis.ecolim.server.Metrics;

import java.util.UUID;

public class AdminRequierd<T> {

    private UUID adminuuid = Metrics.getUserUUID();
    private T data;

    public UUID getAdminuuid() {
        return adminuuid;
    }

    public void setAdminuuid(UUID adminuuid) {
        this.adminuuid = adminuuid;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AdminRequierd{" +
                "adminuuid=" + adminuuid +
                ", data=" + data +
                '}';
    }
}
