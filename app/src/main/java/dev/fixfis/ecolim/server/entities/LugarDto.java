package dev.fixfis.ecolim.server.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link dev.fixfis.ecolim.entities.Lugar}
 */
public class LugarDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final String direccion;
    private final String descripcion;

    public LugarDto(Long id, String nombre, String direccion, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LugarDto entity = (LugarDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.direccion, entity.direccion) &&
                Objects.equals(this.descripcion, entity.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, direccion, descripcion);
    }

    @Override
    public String toString() {
        return nombre+" "+direccion;
    }
}