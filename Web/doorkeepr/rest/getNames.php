<?php

    //Autoload functionality 
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $building_id = $_GET['building_id'];

    $query = $pdo->prepare("SELECT * FROM users WHERE building_id = ?");
    $query->execute(array($building_id));

   	

    print_r(json_encode($query->fetchAll(PDO::FETCH_ASSOC)));