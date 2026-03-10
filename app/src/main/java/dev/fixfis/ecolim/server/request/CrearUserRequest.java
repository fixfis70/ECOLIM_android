package dev.fixfis.ecolim.server.request;



import dev.fixfis.ecolim.server.entities.security.UsuarioDto;

import java.util.UUID;

public class CrearUserRequest {
    UUID adminuuid;
    UsuarioDto usuario;

    public UUID getAdminuuid() {
        return adminuuid;
    }

    public void setAdminuuid(UUID adminuuid) {
        this.adminuuid = adminuuid;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
}
