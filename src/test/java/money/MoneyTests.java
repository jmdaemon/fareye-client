package test.money;

import app.money.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*; 

public class MoneyTests {

  @Test
  public void equals_Money_ReturnsTrue() {
    assertTrue(Money.dollar(5).equals(Money.dollar(5)));
  }

  @Test
  public void times_Money_ReturnsNewTotal() {
    Money five = Money.dollar(5);
    assertEquals(Money.dollar(10), five.times(2));
  }

  @Test
  public void currency_DollarsFrancs_EqualsDollarsFrancs() {
    assertEquals("USD", Money.dollar(1).currency());
    assertEquals("CHF", Money.franc(1).currency());
  }

  @Test
  public void add_Money_ReturnsNewTotal() {
    Money five = Money.dollar(5);
    Expression sum = five.plus(five);
    Bank bank = new Bank();
    Money reduced = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(10), reduced);
  }

  @Test
  public void plus_FiveDollars_ReturnsSum() {
    Money five = Money.dollar(5);
    Expression result = five.plus(five);
    Sum sum = (Sum) result;
    assertEquals(five, sum.augend);
    assertEquals(five, sum.addend);
  }

  @Test
  public void sum_3And4_ReducesTo7() {
    Expression sum = new Sum (Money.dollar(3), Money.dollar(4));
    Bank bank = new Bank();
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(7), result);
  }

  @Test
  public void reduce_Money() {
    Bank bank = new Bank();
    Money result = bank.reduce(Money.dollar(1), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  public void reduce_MoneyDifferentCurrency_ReturnMoney() {
    Bank bank = new Bank();
    bank.addRate("CHF", "USD", 2);
    Money result = bank.reduce(Money.franc(2), "USD");
    assertEquals(Money.dollar(1), result);
  }

  //@Test
  //public void array_Equals_True() {
    //assertEquals(new Object[] {"abc"}, new Object[] {"abc"});
  //}

  @Test
  public void rate_USDandUSD_Return1() {
    assertEquals(1, new Bank().rate("USD", "USD"));
  }

  @Test
  public void add_DollarFranc_ReturnSum() {
    Expression fiveBucks = Money.dollar(5);
    Expression tenFrancs = Money.franc(10);
    Bank bank = new Bank();
    bank.addRate("CHF", "USD", 2);
    Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
    assertEquals(Money.dollar(10), result);
  }
}
