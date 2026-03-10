package dev.fixfis.ecolim.server.entities.security;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link dev.fixfis.ecolim.entities.Usuario}
 */
public class UsuarioDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String dni;
    private final boolean activo;
    private final CredencialDto credencial;

    public UsuarioDto(Long id, String nombre, String apellido, String dni, boolean activo, CredencialDto credencial) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.activo = activo;
        this.credencial = credencial;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public boolean getActivo() {
        return activo;
    }

    public CredencialDto getCredencial() {
        return credencial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto entity = (UsuarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.apellido, entity.apellido) &&
                Objects.equals(this.dni, entity.dni) &&
                Objects.equals(this.activo, entity.activo) &&
                Objects.equals(this.credencial, entity.credencial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, activo, credencial);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "apellido = " + apellido + ", " +
                "dni = " + dni + ", " +
                "activo = " + activo + ", " +
                "credencial = " + credencial + ")";
    }
}