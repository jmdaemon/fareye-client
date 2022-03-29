package app.utility;

import java.util.Random;

public class Random {
  /**
    * Generate a random number between lower-upper
    * @param lower The minimum number that could be generated (inclusive).
    * @param upper The maximum number that could be generated (inclusive).
    * @return A random number inbetween lower and upper (inclusive).
    */
  public static int generateRandomNumber(int lower, int upper) {
    Random randGen = new Random();
    int result = randGen.nextInt(upper) + lower;
    return result;
  }
}
