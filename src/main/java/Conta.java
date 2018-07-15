package src.main.java;

import org.javamoney.moneta.Money;

public interface Conta {
    Money saldo();
    boolean sacar(Money valor);
    Money fatura(int mes, int ano);
}
