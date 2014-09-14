<?php

    //Autoload functionality 
header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $firstName = $_GET['firstName'];
    $lastName = $_GET['lastName'];
    $visitor = $firstName." ".$lastName;
    $query = $pdo->prepare("SELECT * FROM appointments WHERE visitor = ?");
    $query->execute(array($visitor));

   	$res = $query->fetchAll(PDO::FETCH_ASSOC);

   	if(count($res)){
   		print_r(json_encode($res));
   	}else{
   		print '[]';
   	}