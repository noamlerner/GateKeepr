<?php
	session_start();
    header("Access-Control-Allow-Origin: *");
 
    //Autoload functionality 
    function __autoload($class_name) {
        include $class_name . '.php';
    }

	if(isset($_SESSION['name'])){

		if(isset($_POST['visitor']) && isset($_POST['arrival_time']) && isset($_POST['end_arrival_time']) && isset($_POST['duration'])){
		$visitor = $_POST['visitor'];
		$arrival_time = $_POST['arrival_time'];
		$end_arrival_time = $_POST['end_arrival_time'];
		$duration = $_POST['duration'];
		$name = $_SESSION['name'];

		$dbA = new DBAccess();
		$pdo = $dbA->initialize();

		$building_id_q = $pdo->prepare("SELECT * FROM users WHERE name = ?");
		$building_id_q->execute(array($name));
		$building_id_r = $building_id_q->fetch(PDO::FETCH_ASSOC);
		$building_id = $building_id_r['building_id'];



		$query = $pdo->prepare("INSERT INTO appointments (building_id, visitor, tenant_id, arrival_time, end_arrival_time, duration) VALUES (?,?,?,?,?,?)");
		$query->execute(array($building_id, $visitor, $name, $arrival_time, $end_arrival_time, $duration));

		header("Location: success.php");
}
}else {
		header("Location: index.php");
	}

?>
<!DOCTYPE html>
<html style = "background-color: #27ae60">
	<head> 
		<title> Account </title>
				<link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
		<link rel = "stylesheet" href = "css/index.css">
	</head>
	<body bgcolor = "#27ae60">
		<div id = "header">
			<span style = "float: left; font-size: 30px;"> <span style = "color:#d35400">Gate</span>keepr<sup style = "font-size: 15px">&#946;eta</sup>  </span>
			<span style = "float: right; font-size: 20px; padding-right: 50px; padding-top: 10px;"> Hello, <?php echo $_SESSION['name']; ?> </span>
		</div>

		<div id = "createAppointment">
			<span style = " font-size: 20px; padding-top: 25px;"> Create Appointment </span><br /><br />
			<form method = "post" action = "account.php"> 
				Arrival Time: <br /><input type="datetime-local" name="arrival_time" class = "inputForm"><br />
				Arrival Timeout:<br /> <input type = "datetime-local" name = "end_arrival_time" class = "inputForm"> <br /><br />
				 <input type = "text" name = "duration" class = "inputForm" placeholder = "Visit Duration (Minutes):"><br /><br /> 
				 <input type = "text" name = "visitor" class = "inputForm" placeholder = "Visitor Name"> <br /><br />
				 <input type = "submit" name = "submit" class = "inputSubmit" value = "Set Appointment!"> <br />
			</form>
		</div>
	</body>
</html>