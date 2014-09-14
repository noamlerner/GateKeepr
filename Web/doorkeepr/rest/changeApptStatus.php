<?php

    //Autoload functionality 
header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $id = $_GET['id'];
    $query = $pdo->prepare("UPDATE appointments SET status = 1 WHERE id = ?");
    $query->execute(array($id));



  