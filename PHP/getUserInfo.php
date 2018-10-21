<?php

// Initialize our values

//function getInfo($acct_num, $pass)
//{
    $account_num = $_POST['accountNum'];
    $password = $_POST['password'];
    
    // Establish a connection
    $con = new mysqli("localhost", "id6011112_joseph", "Password", "id6011112_atm");
    
    // Error Checking
    if ($con->connect_error) 
      {
        die("Connection failed: " . $conn->connect_error);
      }
      
      // Query the database
      $sql = "SELECT * FROM Users WHERE acct_number LIKE '$account_num' AND password LIKE '$password'";
      
      if ($result = $con->query($sql))
      {
          print $result;
          while ($row = $result->mysqli_fetch_row())
          {
            print (json_encode($row[0]));
          }
      }
      
      //$result = $con->query($sql);
      // This queries the connection to the database, and returns a bool value.
      
      
      //$row = mysql_fetch_row($con->query($sql));
      //$row = mysqli_fetch_assoc($result);
      
      //$row = $result->fetch_row();
      // Cannot fetch a row on a boolean value. Can only fetch a value if it exists.
      //print (json_encode($row));
      /*
    if ($row = $con->query($sql))
    {
        while ($row = $result->fetch_assoc())
        {
            print (json_encode($row));
        }
    }
    */
    /*
     if($con->query($sql) === TRUE)
      {
          echo "Account Creation Successful.";
          
      }
      else
      {
        print("Account Creation Failed.");
        echo "Error: " . $sql . "<br>" . $con->error;
      }
    */ 
    mysqli_free_result($result);
      $con->close();
//    }
?>
