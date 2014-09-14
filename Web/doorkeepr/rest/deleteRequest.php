<?php

    //Autoload functionality 
header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $id = $_GET['id'];
    $query = $pdo->prepare("DELETE  FROM requests WHERE id = ?");

    $query->execute(array($id));

