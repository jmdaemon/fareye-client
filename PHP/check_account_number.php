<?php
    function checkNum($acctNum)
    {
        $account_num = $_POST['accountNum'];
        $password = $_POST['password'];
        $fir_name = $_POST['firstName'];
        $mid_name = $_POST['middleName'];
        $las_name = $_POST['lastName'];
        
        $con = new mysqli('localhost', 'id6011112_joseph', 'Password','id6011112_atm'); 
        
        if ($con->connect_error) 
          {
            die("Connection failed: " . $con->connect_error);
          }
          
          $query = "SELECT * FROM USERS WHERE acct_number LIKE '$acctNum'";
        
        $sql = mysqli_query($con, $query);
    if(mysqli_num_rows($sql) > 0)
    {
      // if a row was found with these credentials â€”> login successful
      echo "Unique.";
      return TRUE;
    }
  else
  {
    if (!mysqli_query($con,$query))
    {
        die('Error: ' . mysqli_error($con));
    }
    else
    {
        echo "Not unique.";
        return FALSE;
    }
    
    /*
        // Check for uniqueness
          if($con->query($sql) === FALSE)
          {
              echo "Account number is unique.";
              return TRUE;
          }
          
          else
          {
            print("Account already associated with a user.");
            echo "Error: " . $sql . "<br>" . $con->error;
            return FALSE;
          }
          */
         $con->close();
    
    }
    }
    
?>
