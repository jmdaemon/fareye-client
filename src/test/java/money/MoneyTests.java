package test.money;

import static app.money.convert.Convert.*;
import app.money.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*; 

import java.math.BigDecimal;

public class MoneyTests {
  private Money five;
  private Bank bank;
  private Expression fiveBucks;
  private Expression tenFrancs;

  @BeforeEach
  public void setUp() {
    this.five = Money.dollar(5);
    this.bank = new Bank();
    this.bank.addRate("CHF", "USD", 2);
    this.fiveBucks = Money.dollar(5);
    this.tenFrancs = Money.franc(10);
  }

  @Test
  public void equals_Dollars_ReturnTrue() {
    assertTrue(Money.dollar(5).equals(Money.dollar(5)));
  }

  @Test
  public void times_Dollars_ReturnsProduct() {
    assertEquals(Money.dollar(10), five.times(2));
  }

  @Test
  public void currency_DollarsFrancs_ReturnsDollarsFrancs() {
    assertEquals("USD", Money.dollar(1).currency());
    assertEquals("CHF", Money.franc(1).currency());
  }

  @Test
  public void plus_Money_ReturnsSum() {
    Expression sum = five.plus(five);
    Money reduced = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(10), reduced);
  }

  @Test
  public void plus_FiveDollars_ReturnsSum() {
    Expression result = five.plus(five);
    Sum sum = (Sum) result;
    assertEquals(five, sum.augend);
    assertEquals(five, sum.addend);
  }

  @Test
  public void sum_Dollars_ReturnsMoney() {
    Expression sum = new Sum (Money.dollar(3), Money.dollar(4));
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(7), result);
  }

  @Test
  public void reduce_Dollars_ReturnsMoney() {
    Money result = bank.reduce(Money.dollar(1), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  public void reduce_DifferentCurrency_ReturnsMoney() {
    Money result = bank.reduce(Money.franc(2), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  public void rate_SameCurrency_Return1() {
    assertEquals(toBigDecimal(1), new Bank().rate("USD", "USD"));
  }

  @Test
  public void sum_Franc_ReturnSum() {
    Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
    assertEquals(Money.dollar(10), result);
  }

  @Test
  public void sum_DollarFranc_ReturnSum() {
    Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(15), result);
  }

  @Test
  public void times_DollarFranc_ReturnsProduct() {
    Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(20), result);
  }

  @Test
  public void multiply_BigDecimal_ReturnsProduct() { 
    BigDecimal b1 = toBigDecimal(5); 
    BigDecimal b2 = toBigDecimal(2); 
    BigDecimal b3 = b1.multiply(b2); 
    assertEquals(10, b3.intValue());
    assertEquals(toBigDecimal(10), b3);
  }

  @Test
  public void minus_Franc_ReturnsDifference() {
    Money result = bank.reduce(fiveBucks.minus(tenFrancs), "USD");
    assertEquals(Money.dollar(0), result);
  }

  @Test
  public void minus_DollarFranc_ReturnDifference() {
    Expression diff = new Difference(fiveBucks, tenFrancs).minus(fiveBucks);
    Money result = bank.reduce(diff, "USD");
    assertEquals(Money.dollar(-5), result);
  }
}
