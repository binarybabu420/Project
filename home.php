<?php
//$received=$_POST["name"];
$host="localhost";
$user="id19033636_binary420";
$pass="#Ua65G&2b4W&)c0~";
$db_name="id19033636_mysummerproject";
$conn=mysqli_connect("$host","$user","$pass","$db_name");
$query="SELECT * FROM sush121";
$res=mysqli_query($conn,$query);
$name=array();
while($row=mysqli_fetch_assoc($res))
{
$name[]=$row;
}
echo json_encode($name);

?>