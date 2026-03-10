package dev.fixfis.ecolim.server.request;


import dev.fixfis.ecolim.server.Metrics;
import dev.fixfis.ecolim.server.entities.DesperdicioDto;

import java.util.UUID;

public class AddDesperdicioTandaRequest {
    private UUID uuidpersona = Metrics.getUserUUID();
    private Long idTanda;
    private DesperdicioDto desperdicio;

    public UUID getUuidpersona() {
        return uuidpersona;
    }

    public void setUuidpersona(UUID uuidpersona) {
        this.uuidpersona = uuidpersona;
    }

    public Long getIdTanda() {
        return idTanda;
    }

    public void setIdTanda(Long idTanda) {
        this.idTanda = idTanda;
    }

    public DesperdicioDto getDesperdicio() {
        return desperdicio;
    }

    public void setDesperdicio(DesperdicioDto desperdicio) {
        this.desperdicio = desperdicio;
    }
}
