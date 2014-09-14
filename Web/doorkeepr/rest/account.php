<?php
	session_start();
	if(isset($_SESSION['name'])){

	}else {
		header("Location: index.php");
	}

?>
<!DOCTYPE html>
<html>
	<head> 
		<title> Account </title>
		<link rel = "stylesheet" href = "index.css">
	</head>
	<body bgcolor = "#27ae60">

	</body>
</html>