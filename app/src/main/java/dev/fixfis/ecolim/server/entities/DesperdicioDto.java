package dev.fixfis.ecolim.server.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link dev.fixfis.ecolim.entities.Desperdicio}
 */
public class DesperdicioDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final double cantidad;
    private final long idTipoResiduo;
    public DesperdicioDto(Long id, String nombre, double cantidad, long idTipoResiduo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.idTipoResiduo = idTipoResiduo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesperdicioDto entity = (DesperdicioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.cantidad, entity.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, cantidad);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "cantidad = " + cantidad + ")";
    }

    public long getIdTipoResiduo() {
        return idTipoResiduo;
    }
}