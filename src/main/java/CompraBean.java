package src.main.java;

import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.util.UUID;

public class CompraBean {
    private UUID ID;
    private LocalDateTime horario;
    private Money valor;
    private String estabelecimento;

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorário(LocalDateTime horário) {
        this.horario = horário;
    }

    public Money getValor() {
        return valor;
    }

    public void setValor(Money valor) {
        this.valor = valor;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}
