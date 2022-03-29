package app.fareye;

public class Client {
  /**
    * Client stores information for a Fareye Financial client
   */

  private String firstName;
  private String middleName;
  private String lastName;
  private String password;

  public Client(String fname, String mname, String lname, String pass) {
    this.firstName = fname;
    this.middleName = mname;
    this.lastName = lastName;
    this.password = password;
  }

  // Getters
  public String getFirstName()  { return this.firstName; }
  public String getMiddleName() { return this.middleName; }
  public String getLastName()   { return this.lastName; }

  // Setters
  public void setFirstName(String fname)  { this.firstName = fname; }
  public void setMiddleName(String mname) { this.middleName = mname; }
  public void setLastName(String lname)   { this.lastName = lname; }

  /**
    * Generate a random number between lower-upper
    * @param lower The minimum number that could be generated (inclusive).
    * @param upper The maximum number that could be generated (inclusive).
    * @return A random number inbetween lower and upper (inclusive).
    */
  private static int genRandNum(int lower, int upper) {
    Random randGen = new Random();
    int result = randGen.nextInt(upper) + lower;
    return result;
  }

  /**
    * Generates secure, random passwords.
    * @param len The length of the password to generate
    */
  private static String generatePassword(int len) {
    // Our given character set to choose from
    final String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    char[] password = new char[len];
    for (int i = 0; i < len; i++) {
      password[i] = charset.charAt(genRandNum(charset.length()));
    }
    String result = String.valueOf(password);
    return result;
  }
}
