<?php
    header("Access-Control-Allow-Origin: *");
 
    //Autoload functionality 
    function __autoload($class_name) {
        include $class_name . '.php';
    }

    if(isset($_POST['name']) &&  isset($_POST['password'])){

  		$dbA = new DBAccess();
      $pdo = $dbA->initialize();


        $name = $_POST['name'];
        $password = $_POST['password'];


      $query = $pdo->prepare("SELECT * FROM users WHERE name = ? and password = ?");
      $query->execute(array($name, $password));

      $res = $query->fetchAll(PDO::FETCH_ASSOC);

      if(count($res)){
        session_start();
        $_SESSION['name'] = $name;
        header("Location: account.php");
      }else{
        echo "Try again";
      }
  		  
    }else{


 
?>

<!DOCTYPE html>
<html>
	<head>
		<title> Sign In </title>
		<link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
		<link href = "css/index.css" rel = "stylesheet">

	</head>
	<body>
		<div id = "signUpMain">
			Sign In <br / ><br />
			<form method = "post" action = "signIn.php">
				<input type = "text" name = "name" class = "inputForm" placeholder = "Username/Email" /> <br/>
				<input type = "password" name = "password" class = "inputForm" placeholder = "Password" /> <br /> <br />
			
				<input type = "submit" name = "submit" class = "inputSubmit" value = "Sign In!" />
			</form>

		
		</div>
	</body>

	<?php
}
	?>
</html>

