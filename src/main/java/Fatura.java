package src.main.java;

import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Fatura {
    private UUID ID;
    private Set<Compra> compras;
    private LocalDate dataDeDebito;
    private Money valorAtual;

    public Fatura(LocalDate dataDeDebito) {
        this.ID = UUID.randomUUID();
        this.compras = new LinkedHashSet<>();
        this.dataDeDebito = dataDeDebito;
        this.ID = UUID.randomUUID();
    }

    public Compra addCompra(Money valor, String estabelecimento) {
        Compra compra = new Compra(valor, estabelecimento);
        this.compras.add(compra);
        return compra;
    }

    public Set<Compra> getCompras() {
        return compras;
    }

    public LocalDate getDataDeDebito() {
        return dataDeDebito;
    }

    public Money getValorAtual() {
        return valorAtual;
    }
}
