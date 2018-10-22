<?php
$password = $_POST['password'];
//$new_password = $_POST['newPass'];
$fir_name = $_POST['firstName'];
$mid_name = $_POST['middleName'];
$las_name = $_POST['lastName'];
$account_num = $_POST['accountNum'];
$choice = $_POST['choice'];

$con=new mysqli('localhost', 'id6011112_joseph', 'Password','id6011112_atm'); 

if ($con->connect_error) 
  {
    die("Connection failed: " . $con->connect_error);
  }
  $sql;
  switch($choice)
  {
    case 'Withdraw': 
        // Might have to put this outside of this switch statement.
        $balance = $_POST['balance'];
        
        $sql = "UPDATE USERS SET balance='$balance' WHERE acct_number='$account_num' AND password='$password'"; break;
        if($con->query($sql) === TRUE)
          {
              echo "Account Creation Successful.";
              
          }
          else
          {
            print("Account Creation Failed.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }
          
    case 'WithdrawFrom': 
        $balance = $_POST['balance'];
        
        $sql = "UPDATE USERS SET balance = '$balance' WHERE acct_number='$account_num' AND password='$password'"; break;
        
        if($con->query($sql) === TRUE)
          {
              echo "Account Creation Successful.";
              
          }
          else
          {
            print("Account Creation Failed.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }
        
    case 'Deposit': 
        
        $sql = "UPDATE USERS SET balance='$balance' WHERE acct_number='$account_num' AND password='$password'"; break;
        if($con->query($sql) === TRUE)
          {
              echo "Account Creation Successful.";
              
          }
          else
          {
            print("Account Creation Failed.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }
    
    case 'DepositTo': 
        $sql = " WHERE acct_number='$account_num' AND password='$password'"; break;
          
        if($con->query($sql) === TRUE)
          {
              echo "Account Creation Successful.";
              
          }
          else
          {
            print("Account Creation Failed.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }
          
    case 'ResetPass': 
        $sql = " WHERE acct_number='$account_num' AND password='$password'"; break;
        if($con->query($sql) === TRUE)
          {
              echo "Account Creation Successful.";
              
          }
          else
          {
            print("Account Creation Failed.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }
    case 'EmptyAccount': $sql = " WHERE acct_number='$account_num' AND password='$password'"; break;
        if($con->query($sql) === TRUE)
          {
              echo "Account Creation Successful.";
              
          }
          else
          {
            print("Account Creation Failed.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }
    case 'History': $sql = " WHERE acct_number='$account_num' AND password='$password'"; break;
        if($con->query($sql) === TRUE)
          {
              echo "Account Creation Successful.";
              
          }
          else
          {
            print("Account Creation Failed.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }
    default: break;
  }
  
  

  
  $con->close();
  
?>
