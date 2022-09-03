<?php
$username=$_POST["username"];
$password=$_POST["password"];
$local_host="localhost";
$db_user="id19033636_binary420";
$db_pass="#Ua65G&2b4W&)c0~";
$db_name="id19033636_mysummerproject";
$conn=mysqli_connect($local_host,$db_user,$db_pass,$db_name);
$query="SELECT * FROM LoginDetail WHERE email='$username' AND password='$password'";
$result=mysqli_query($conn,$query);
/*if(mysqli_query($conn,$query))
{
echo "true";
}
else
echo "false";*/
if(mysqli_num_rows($result)==0)
{
 echo "false";}
 else
 {
 echo "true";}
  mysqli_close($conn);

?>