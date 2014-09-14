<?php

    //Autoload functionality 
  header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $name = $_GET['name'];
    $query = $pdo->prepare("SELECT * FROM requests WHERE tenant_id = ?");
    $query->execute(array($name));

   	$res = $query->fetchAll(PDO::FETCH_ASSOC);

    if(count($res)){
      print_r(json_encode($res));
    }else{
      return false;
    }