package test.log.csv;

import static app.log.csv.CSV.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*; 

public class CSVTests {

  @Test
  public void searchLog_WithKeyword_ReturnsResult() {
    assertEquals("Deposit Successful", searchLog("Deposit Successful"));
  }
}
