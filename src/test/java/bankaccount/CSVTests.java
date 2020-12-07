package test.log.csv;

import static app.log.csv.CSV.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*; 

import java.util.List;

import org.unix4j.*;
import org.unix4j.unix.cat.*;
import org.unix4j.unix.Grep.*;
import org.unix4j.line.*;

public class CSVTests {

  @Test
  public void searchLog_WithKeyword_ReturnsResult() {
    assertEquals("Deposit Successful", searchLog("Deposit Successful"));
  }

  @Test
  public void searchLogAll_Star_ReturnsAllResults() {
    List<Line> entries = Unix4j.cat("./transaction_history.csv").grep("").toLineList();
    String[] results = new String[entries.size()];
    for (int i = 0; i < entries.size(); i++) {
      results[i] = entries.get(i).getContent();
    }

    String[] funcResults = searchLogAll("");
    for (int i = 0; i < results.length; i++) {
      assertEquals(results[i], funcResults[i]); 
    }
  }
}
