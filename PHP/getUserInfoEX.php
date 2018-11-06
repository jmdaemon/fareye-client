<?php

    $account_num = $_POST['accountNum'];
    
    // Establish a connection
    $con = new mysqli("localhost", "id6011112_joseph", "Password", "id6011112_atm");
    
    // Error Checking
    if ($con->connect_error) 
      {
        die("Connection failed: " . $conn->connect_error);
      }
      
      // Query the database
      $sql = "SELECT * FROM USERS WHERE acct_number=$account_num";
      $result = $con->query($sql);
      //$row = $result->fetch_row();
    
       $row = mysqli_fetch_assoc($result);
      print (json_encode($row));


      //if ($result = $con->query($sql))
      
      
      //if ($result = $con->query($sql))
      //{
        //if (!$result) 
        //{
        //    die('Query failed: ' . mysql_error());
        //}
        
        //$result = mysql_query($sql);
        //    if (!$result) {
        //    echo 'Could not run query: ' . mysql_error();
        //    exit;
        //}
        //$row = mysql_fetch_row($result);
        //printf(json_encode($row));
        //print $result;
        //while ($row = $con->query($result))
        //{
          //printf(json_encode($row[0]));
        //  printf (json_encode("%s (%s)\n",$row[0],$row[1]));
        //}
      //}
      
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
      //$result->close();
      $con->close();
//    }
?>
