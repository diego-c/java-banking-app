package src.main.java;

import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class Main {
    public static void main(String[] args) {
        /*CLI cli = CLI.getInstance();
        cli.iniciar();*/
        CompraBean compra = new CompraBean();
        compra.setHorário(LocalDateTime.now(ZoneId.ofOffset("GMT", ZoneOffset.ofHours(-3))));
        compra.setEstabelecimento("Extra");
        compra.setValor(Money.of(23.52, "BRL"));

        CompraDAO compraDAO = new CompraDAO();
        compraDAO.insertCompra(compra);
    }
}
