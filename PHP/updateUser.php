<?php
$password = $_POST['password'];
//$new_password = $_POST['newPass'];
$balance = $_POST['balance'];
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
  
function updateHistory($con, $message)
{
    $sql = "UPDATE USERS SET history = CONCAT(ifnull(history, ''), $message) WHERE acct_number=$account_num";
    
    if ($con->query($sql))
    {
        print "Account history updated\r\n";
        return True;
    }
    
    else
    {
        print("History failed to update.");
        echo "Error: " . $sql . "<br>" . $con->error;
        return False;
    }
}

function processTransaction($con, $sql)
{
    if ($con->query($sql))
    {
        //print "In processTransaction $amount, $account_num, $password";
        print "Transaction successful\r\n";
        return True;
    }
    
    else
    {
        print("Transaction failed\r\n");
        echo "Error: " . $sql . "<br>" . $con->error;
        return False;
    }
          
}

  switch($choice)
  {
    case "Withdraw": 
        $amount = $_POST['amount'];
        if ($amount <= $balance)
        {
            $sql = "UPDATE USERS SET balance=balance-$amount WHERE acct_number=$account_num";
            if (processTransaction($con, $sql))
            {
                $message = ("Withdrawal of Amount $" . $amount . " made.\n");
                updateHistory($con, $message);
            }
            
        }
            
        else if ($amount > $balance)
        {
            $sql = "UPDATE USERS SET balance=balance-$amount WHERE acct_number=$account_num";
            
        
            if (processTransaction($con, $sql) == True)
            {
                $sql = "UPDATE USERS SET balance=balance-25.0 WHERE acct_number='$account_num' AND password ='$password'";
                
                if (processTransaction($con, $sql))
                {
                    $message = ("A bank overdraft fee of $25 will be charged to your account. We thank you for your business.\n");
                    updateHistory($con, $message);
                }
            }
        } break;
        
    case 'WithdrawFrom': 
        $amount = $_POST['amount'];
        $otherUB = $_POST['otherUserBal'];
        $otherUN = $_POST['otherUserNum'];
        $otherUP = $_POST['otherUserPas'];
        
        if ($amount <= $otherUB)
        {
            // Give money to the current bank account
            $sql = "UPDATE USERS SET balance=balance+$amount WHERE acct_number=$account_num";
            
            if (processTransaction($con, $sql))
            {
				// Take money from the other bank account
                $sql = "UPDATE USERS SET balance=balance-$amount WHERE acct_number=$otherUN";
                
                if (processTransaction($con, $sql))
                {
                    $message = "Transfer of $" . $amount . " dollars from " .
						$otherUN . " complete.\n";
			    	updateHistory($con, $message);
                }
            }
        }
        
        else if ($amount > $otherUB)
        {
            $sql = "UPDATE USERS SET balance=balance-25 WHERE acct_number=$account_num";
            
            if (processTransaction($con, $sql))
            {
                $message = ("Transfer of $" . $amount . " dollars from account " 
						. $otherUN . 
						" unsuccessful due to insufficient funds. A fee of $25 was applied\n");
				updateHistory($con, $message);
            }
        } break;
        
    case 'Deposit': 
        $amount = $_POST['amount'];
        
        if ($amount >= 0)
        {
            $sql = "UPDATE USERS SET balance=balance+$amount WHERE acct_number=$account_num"; 
            
            if (processTransaction($con, $sql))
            {
                $message = ("Deposit of amount $" . $amount . " made.\n");
                updateHistory($con, $message);
            }
            
        }
        
        else if ($amount < 0)
        {
            $message = ("Deposit unsuccessful: invalid amount.\n");
            
            updateHistory($con, $message);
        } break;
        
    case 'DepositTo': 
        $amount = $_POST['amount'];
        $otherUB = $_POST['otherUserBal'];
        $otherUN = $_POST['otherUserNum'];
        $otherUP = $_POST['otherUserPas'];
        if ($amount <= $otherUB)
        {
            // Take money from us
            $sql = "UPDATE USERS SET balance=balance-$amount WHERE acct_number=$account_num";
            if (processTransaction($con, $sql))
            {
                // Give money to
                $sql = "UPDATE USERS SET balance=balance+$amountWHERE acct_number=$otherUN";
                $message = ("Transfer of $" . $amount . " dollars to account " .
				$otherUN . " complete.\n");
                updateHistory($con, $message);
            }
        }
        
        else
        {
            $sql = "UPDATE USERS SET balance=balance-25 WHERE acct_number=$account_num";
            if (processTransaction($sql) == True)
            {
                $message = ("Overdraft during transfer. Transaction not processed. "
					. "Please contact us for further assistance. A fee of \$25 was applied.\n");   
		        updateHistory($con, $message);
            }
        } break;
          
    case 'ResetPass': 
        // Work on this during class
        $newpass = $_POST['newPass'];
        $sql = "UPDATE USERS SET password=$newpass WHERE acct_number=$account_num AND password='$password'";
        
        if($con->query($sql))
          {
              $message = ("Password successfully updated.\n");
              updateHistory($con, $message);
          }
        
        else
          {
            $message = ("Password Reset Unsuccessful . Please Try Again.\n");
            updateHistory($message);
          }break;
          
    case 'EmptyAccount': 
        $sql = "UPDATE USERS SET balance=0 WHERE acct_number='$account_num'"; 
        if($con->query($sql))
          {
              echo "Account successfully emptied.";
              $message = "So much for \"Leaving something for a rainy day!\"";
              updateHistory($con, $message);
          }
          else
          {
            print("Account failed to empty.");
            echo "Error: " . $sql . "<br>" . $con->error;
          }break;
          
    case 'History': 
        $sql = "SELECT * USERS WHERE acct_number='$account_num' AND password='$password'";
        
        $result = $con->query($sql);
        $row = mysqli_fetch_assoc($result);
        print (json_encode($row));
        break;
        
    default: 
        
        break;
    }
  $con->close();
  
?>
