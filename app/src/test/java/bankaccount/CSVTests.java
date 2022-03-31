package test.log.csv;

import static app.utils.csv.CSV.*;
import static app.utils.log.Log.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*; 

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.unix4j.*;
import org.unix4j.unix.cat.*;
import org.unix4j.unix.Grep.*;
import org.unix4j.line.*;

public class CSVTests {
  private static final String filepath = "./transaction_history.csv";

  @BeforeEach
  public void setUp() throws IOException {
    String logEntry1 = (genTimeStamp() + ", " + "New Bank Account Created, \n");
    String logEntry2 = (genTimeStamp() + ", " + "Deposit Successful\t[$100.0], \n" );
    initializeFile(logEntry1, logEntry2);
  }

  @AfterEach
  public void tearDown() {
    File logFile = new File(filepath);
    logFile.delete(); 
  }

  private static void initializeFile(String msg1, String msg2) throws IOException { 
    BufferedWriter writer = new BufferedWriter(new FileWriter(filepath)); 
    writer.write(msg1); 
    writer.write(msg2);
    writer.close();
  } 

  @Test
  public void searchLog_WithKeyword_ReturnsResult() {
    assertEquals("Deposit Successful\t[$100.0]", searchLog("Deposit Successful", filepath));
  }

  @Test
  public void searchLogAll_Star_ReturnsAllResults() {
    List<Line> entries = Unix4j.cat(filepath).grep("").toLineList();
    String[] results = new String[entries.size()];
    for (int i = 0; i < entries.size(); i++) {
      results[i] = entries.get(i).getContent();
    }

    String[] funcResults = searchLogAll("", filepath);
    for (int i = 0; i < results.length; i++) {
      assertEquals(results[i], funcResults[i]); 
    }
  }

  @Test
  public void writeToFile_TestMessage_AppendsToLog() throws IOException {
    writeToFile("Test Message\n", filepath);
    String res = Unix4j.cat(filepath).grep("Test Message").toLineList().get(0).getContent();
    assertEquals("Test Message", res);
  }
}
