<?php
    header("Access-Control-Allow-Origin: *");
 
    //Autoload functionality 
    function __autoload($class_name) {
        include $class_name . '.php';
    }

    if(isset($_POST['name']) && isset($_POST['building_id']) && isset($_POST['password']) && isset($_POST['full_name'])){
    	  $dbA = new DBAccess();
  		  $pdo = $dbA->initialize();


  		  $name = $_POST['name'];
  		  $building_id = $_POST['building_id'];
  		  $password = $_POST['password'];
        $full_name = $_POST['full_name'];

  		  $sql1 = "SELECT * FROM buildings WHERE building_id = ?";

  		  $query = $pdo->prepare($sql1);
  		  $query->execute(array($building_id));
  		  $res = $query->fetchAll(PDO::FETCH_ASSOC);

  		  if(count($res)){


  		  $sql2 = "INSERT INTO users (name, full_name, password, building_id) VALUES (?,?,?,?)";
  		  $query = $pdo->prepare($sql2);
  		  $query->execute(array($name, $full_name, $password, $building_id));
  		  header("Location: success.php");
  		}else{
  			echo "Enter a valid building id";
  		}
  		  
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
			<form method = "post" action = "signUp.php">
				<input type = "text" name = "name" class = "inputForm" placeholder = "Desired Username/Email" /> <br/>
        <input type = "text" name = "full_name" class = "inputForm" placeholder = "Full Name" /> <br/>
				<input type = "password" name = "password" class = "inputForm" placeholder = "Password" /> <br />
				<input type = "text" name = "building_id" class = "inputForm" placeholder = "Building ID (ask landlord)" /> <br /> <br />
				<input type = "submit" name = "submit" class = "inputSubmit" value = "Sign Up!" />
			</form>

			<a href = "buildingSignUp.php" style = "color: #34495e; font-size: 12px; text-decoration: none;"> Click here if you are a landlord. </a>
		</div>
	</body>

	<?php
}
	?>
</html>

