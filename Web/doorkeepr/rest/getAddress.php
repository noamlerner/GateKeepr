<?php

    //Autoload functionality 
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $building_id = $_GET['building_id'];

    $query = $pdo->prepare("SELECT address FROM buildings WHERE building_id = ?");
    $query->execute(array($building_id));

    $res = $query->fetch(PDO::FETCH_ASSOC);

    print $res['address'];