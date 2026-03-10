package dev.fixfis.ecolim.server.entities;


import dev.fixfis.ecolim.server.entities.security.UsuarioDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link dev.fixfis.ecolim.entities.Tanda}
 */
public class TandaDto implements Serializable {
    private final Long id;
    private final LocalDateTime fechaAsignado;
    private final UsuarioDto usuario;
    private final LugarDto lugar;
    private final Set<TandaDesperdicioDto> tandasDesperdicios;

    public TandaDto(Long id, LocalDateTime fechaAsignado, UsuarioDto usuario, LugarDto lugar, Set<TandaDesperdicioDto> tandasDesperdicios) {
        this.id = id;
        this.fechaAsignado = fechaAsignado;
        this.usuario = usuario;
        this.lugar = lugar;
        this.tandasDesperdicios = tandasDesperdicios;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaAsignado() {
        return fechaAsignado;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public LugarDto getLugar() {
        return lugar;
    }

    public Set<TandaDesperdicioDto> getTandasDesperdicios() {
        return tandasDesperdicios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TandaDto entity = (TandaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.fechaAsignado, entity.fechaAsignado) &&
                Objects.equals(this.usuario, entity.usuario) &&
                Objects.equals(this.lugar, entity.lugar) &&
                Objects.equals(this.tandasDesperdicios, entity.tandasDesperdicios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaAsignado, usuario, lugar, tandasDesperdicios);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fechaAsignado = " + fechaAsignado + ", " +
                "usuario = " + usuario + ", " +
                "lugar = " + lugar + ", " +
                "tandasDesperdicios = " + tandasDesperdicios + ")";
    }
}