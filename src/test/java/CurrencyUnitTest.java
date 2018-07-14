import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CurrencyUnitTest {
    @Test
    public void getCurrency() {
        CurrencyUnit usd = Monetary.getCurrency("BRL");

        assertNotNull(usd);
        assertEquals("BRL", usd.getCurrencyCode());
        assertEquals(2, usd.getDefaultFractionDigits());
    }
    @Test
    public void getAmount() {
        Money ten = Money.of(10, "BRL");
        Money twenty = Money.of(20, "BRL");
        assertNotNull(ten);
        MonetaryAmount sub = twenty.subtract(ten);
        assertEquals(Money.of(10, "BRL"), sub, "Wrong subtraction");
    }

    // precision and arithmetic
    @Test
    public void getPrecision() {
        MonetaryAmount[] amounts = new MonetaryAmount[]{ Money.of(10.25, "BRL"), Money.of(1.13, "BRL"), Money.of(6.18, "BRL")};

        MonetaryAmount res = Money.of(0, "BRL");
        for (MonetaryAmount ma : amounts) {
            res = res.add(ma);
        }

        System.out.println("Result: " + res);
        assertEquals(Money.of(17.56, "BRL"), res);
    }

    @Test
    public void currencyConversion() {
        Money dezReais = Money.of(10, "BRL");
        CurrencyConversion cc = MonetaryConversions.getConversion("USD");
        Money emDolar = dezReais.with(cc);
        Money emDolarArr = emDolar.with(Monetary.getDefaultRounding());

        System.out.println("Conversão para dólar arredondado: " + emDolarArr);
        assertNotNull(emDolar);
    }
}
