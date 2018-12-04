<?php
//include '/php/getUserInfo.php';
// Make sure to create an entry in the SQL table for the login

// $encryptedData = $_POST['request'];
// $encryptedKey = $_POST['key'];

require ("encrypt.php");

$encryption = new Encryption;

$con = new mysqli("localhost", "id6011112_joseph", "Password", "id6011112_atm");

if ($con->connect_error) 
  {
    die("Connection failed: " . $conn->connect_error);
  }



// $query = "SELECT * FROM ENCRYPTION";

// $account_num = $_POST['account_num'];

/*
$account_num = $_POST['account_num'];
$password = $_POST['password'];
$secretKey = $_POST['secretKey'];
$iv = $_POST['iv'];


$encryption->setKey($secretKey);
$account_num = $encryption->decrypt($account_num, $iv);
$password = $encryption->decrypt($password, $iv);
*/

$secretKey = $_POST['secretKey'];
$iv = $_POST['iv'];
$account_num = $encryption->decrypt($_POST['account_num'], $iv);
$password = $encryption->decrypt($_POST['password'], $iv);

// $encryption->setKey($secretKey);

$sql = "SELECT * FROM USERS WHERE acct_number LIKE '$account_num' AND password LIKE '$password'";
// $result = $con->query($sql);
// $row = mysqli_fetch_assoc($result);
// print (json_encode($row)); Never print this out.
  

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
