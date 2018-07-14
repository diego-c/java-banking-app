import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

public class Compra {
    private UUID ID;
    private LocalDateTime horário;
    private Money valor;
    private String estabelecimento;

    public Compra(Money valor, String estabelecimento) {
        this.ID = UUID.randomUUID();
        this.horário = LocalDateTime.now(ZoneId.ofOffset("GMT", ZoneOffset.of("-03:00")));
        this.valor = valor;
        this.estabelecimento = estabelecimento;
    }

    public UUID getID() {
        return ID;
    }

    public LocalDateTime getHorario() {
        return horário;
    }

    public Money getValor() {
        return valor;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }
}
