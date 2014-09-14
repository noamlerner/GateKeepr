<?php
    header("Access-Control-Allow-Origin: *");
 
    //Autoload functionality 
    function __autoload($class_name) {
        include $class_name . '.php';
    }

    if(isset($_POST['name']) && isset($_POST['address']) && isset($_POST['password'])){
    
    	$dbA = new DBAccess();
    	$pdo = $dbA->initialize();
  		  $name = $_POST['name'];
  		  $password = $_POST['password'];
  		  $address = $_POST['address'];

  		  	$building_id = uniqid();
  		  	$sql1 = "INSERT INTO users (name, password, status, building_id) VALUES (?, ?, ?, ?)";
  		    $query = $pdo->prepare($sql1);
    		$query->execute(array($name, $password, 1, $building_id));

    		$sql2 = "INSERT INTO buildings (landlord, building_id, address) VALUES (?, ?, ?)";
    		$query = $pdo->prepare($sql2);
    		$query->execute(array($name, $building_id, $address));
     

  		  
    }else{


 
?>

<!DOCTYPE html>
<html>
	<head>
		<title> Sign Up </title>
		<link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
		<link href = "css/index.css" rel = "stylesheet">

	</head>
	<body>
		<div id = "signUpMain">
			Sign Up <br / ><br />
			<form method = "post" action = "buildingSignUp.php">
				<input type = "text" name = "name" class = "inputForm" placeholder = "Desired Username/Email" /> <br/>
				<input type = "password" name = "password" class = "inputForm" placeholder = "Password" /> <br />
				<input type = "text" name = "address" class = "inputForm" placeholder = "Address" /> <br /> <br />
				<input type = "submit" name = "submit" class = "inputSubmit" value = "Sign Up!" />
			</form>
		</div>
	</body>

	<?php
}
	?>
</html>

