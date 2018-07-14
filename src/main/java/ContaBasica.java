import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class ContaBasica implements Conta {
    private UUID ID;
    private Money saldo;
    private List<Fatura> faturas;
    private static final Money LIMITE = Money.of(500, "BRL");

    public ContaBasica(Money saldo) {
        this.ID = UUID.randomUUID();
        this.saldo = saldo;
        this.faturas = new ArrayList<>();
    }

    public Money saldo() {
        return this.saldo;
    }

    public boolean sacar(Money valor) {
        if (this.saldo.isGreaterThanOrEqualTo(valor)) {
            this.saldo = this.saldo.subtract(valor);
            return true;
        }
        System.out.println("O valor a ser sacado é maior do que o saldo disponível");
        return false;
    }

    public Money fatura(int mes, int ano) {
        if (mes < 1 || mes > 12) {
            System.out.println("Mês inválido");
            return null;
        }
        if (ano < 2018 || ano > Calendar.getInstance().get(Calendar.YEAR)) {
            System.out.println("Ano inválido");
            return null;
        }
        Fatura selecionada = null;
        for (Fatura f : faturas) {
            if (f.getDataDeDebito().getMonth().getValue() == mes) {
                selecionada = f;
                break;
            }
        }
        if (selecionada != null) {
            return selecionada.getValorAtual();
        }
        System.out.println("Mês de débito de fatura inválido!");
        return Money.of(0, "BRL");
    }

    // adicionar compra à próxima fatura
    // determinar em qual fatura a compra deve entrar
    public UUID realizarCompra(Money valor, String estabelecimento) {
        LocalDate hoje = LocalDate.now(ZoneId.ofOffset("GMT", ZoneOffset.of("-03:00")));
        Money proximaFatura = fatura(hoje.getMonthValue() + 1, hoje.getYear());
        if (proximaFatura == null) {
            System.out.println("Cálculo errado para a próxima fatura");
            return null;
        }
        Money proximaFaturaMaisValor = proximaFatura.add(valor);
        if (proximaFaturaMaisValor.isGreaterThan(LIMITE)) {
            System.out.println("O valor da compra ultrapassa o limite da Conta Básica!");
            return null;
        }
        Compra aSerAdicionada = new Compra(valor, estabelecimento);

        return aSerAdicionada.getID();
    }

    public UUID getID() {
        return ID;
    }

    public Money getSaldo() {
        return saldo;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }
}
