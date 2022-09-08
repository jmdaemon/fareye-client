package fareye;

import static toolbox.RandomUtility.generateRandomNumber;

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
    this.lastName = lname;
    this.password = pass;
  }

  // Getters
  public String getFirstName()  { return this.firstName; }
  public String getMiddleName() { return this.middleName; }
  public String getLastName()   { return this.lastName; }
  public String getPassword() { return this.password; }

  // Setters
  public void setFirstName(String fname)  { this.firstName = fname; }
  public void setMiddleName(String mname) { this.middleName = mname; }
  public void setLastName(String lname)   { this.lastName = lname; }
  public void setPassword(String pass)  { this.password = pass; }

  /**
    * Generates secure, random passwords.
    * @param len The length of the password to generate
    */
  public static String generatePassword(int len) {
    // Our given character set to choose from
    final String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    char[] password = new char[len];
    for (int i = 0; i < len; i++) {
      password[i] = charset.charAt(generateRandomNumber(0, charset.length()));
    }
    String result = String.valueOf(password);
    return result;
  }

  /**
    * Check if password matches the user's password
    */
  public boolean checkPassword(String pass) {
    boolean result = (this.password.equals(pass)) ? true : false;
    return result;
  }

}
