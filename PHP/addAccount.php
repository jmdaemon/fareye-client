<?php 
//include 'check_account_number.php';
// Change to require


$password = $_POST['password'];
$fir_name = $_POST['firstName'];
$mid_name = $_POST['middleName'];
$las_name = $_POST['lastName'];
$account_num = $_POST['accountNum'];

/*
$check_number = $_POST['checkNum'];
$account_num;
if (checkNum($check_number) === TRUE)
{
    //$account_num = $_POST['accountNum'];
    $account_num = $check_number;
}

else
{
    
}

*/

$con=new mysqli('localhost', 'id6011112_joseph', 'Password','id6011112_atm'); 

if ($con->connect_error) 
  {
    die("Connection failed: " . $con->connect_error);
  }
  
  $sql = "INSERT INTO USERS (f_name, m_name, l_name, balance, acct_number, history, password)
  VALUES ('$fir_name', '$mid_name', '$las_name', '0.0', '$account_num', '', '$password');";
  
  if($con->query($sql) === TRUE)
  {
      echo "Account Creation Successful.";
      
  }
  else
  {
    print("Account Creation Failed.");
    echo "Error: " . $sql . "<br>" . $con->error;
  }

  $con->close();
  
?>
