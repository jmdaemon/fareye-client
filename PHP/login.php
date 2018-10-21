<?php
//include '/php/getUserInfo.php';
// Make sure to create an entry in the SQL table for the login
$account_num = $_POST['account_num'];
$password = $_POST['password'];

$con = new mysqli("localhost", "id6011112_joseph", "Password", "id6011112_atm");

if ($con->connect_error) 
  {
    die("Connection failed: " . $conn->connect_error);
  }
 
  $query = "SELECT * FROM USERS WHERE acct_number LIKE '$account_num' AND password LIKE '$password'";
  
  $sql = mysqli_query($con, $query);
if(mysqli_num_rows($sql) > 0)
  {
      // if a row was found with these credentials â€”> login successful
      echo "Login successful.";
      //getUserInfo($account_num, $password);
  }
  else
  {
    if (!mysqli_query($con,$query))
    {
        die('Error: ' . mysqli_error($con));
    }
    else
    {
        echo "Login failed.";
    }
  }

  $con->close();
?>
