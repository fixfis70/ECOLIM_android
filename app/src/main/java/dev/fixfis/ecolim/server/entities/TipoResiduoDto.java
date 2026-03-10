package dev.fixfis.ecolim.server.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link dev.fixfis.ecolim.entities.TipoResiduo}
 */
public class TipoResiduoDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final String categoria;
    private final String descripcion;
    private final String imagen;

    public TipoResiduoDto(Long id, String nombre, String categoria, String descripcion, String imagen ) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoResiduoDto entity = (TipoResiduoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.categoria, entity.categoria) &&
                Objects.equals(this.descripcion, entity.descripcion) &&
                Objects.equals(this.imagen, entity.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, categoria, descripcion, imagen);
    }

    @Override
    public String toString() {
        return categoria+" "+nombre;
    }
}