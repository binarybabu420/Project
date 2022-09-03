<?php
 $idea=$_POST["idea"];
 $email=$_POST["email"];
 $user="id19033636_binary420";
 $password="#Ua65G&2b4W&)c0~";
 $host="localhost";
 $db_name="id19033636_mysummerproject";
 $conn=mysqli_connect($host,$user,$password,$db_name);
  if($conn)
 echo "true";
 $val=10;
 $sql="INSERT INTO sush121 (idea,email) VALUES ('$idea','$email')";

 if(mysqli_query($conn,$sql))
 {
 echo "posted successfully";
 }
 else
 {
 echo "post unsuccessfull";
 }
 mysqli_close($conn);
?>