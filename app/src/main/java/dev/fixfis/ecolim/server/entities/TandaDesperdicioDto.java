package dev.fixfis.ecolim.server.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link dev.fixfis.ecolim.entities.detalles.TandaDesperdicio}
 */
public class TandaDesperdicioDto implements Serializable {
    private final Long id;
    private final DesperdicioDto desperdicios;

    public TandaDesperdicioDto(Long id, DesperdicioDto desperdicios) {
        this.id = id;
        this.desperdicios = desperdicios;
    }

    public Long getId() {
        return id;
    }

    public DesperdicioDto getDesperdicios() {
        return desperdicios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TandaDesperdicioDto entity = (TandaDesperdicioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.desperdicios, entity.desperdicios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desperdicios);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "desperdicios = " + desperdicios + ")";
    }
}