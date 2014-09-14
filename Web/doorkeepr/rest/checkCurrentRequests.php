<?php

    //Autoload functionality 
  header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $tenant_id = $_GET['tenant_id'];
    $query = $pdo->prepare("SELECT * FROM requests WHERE tenant_id = ?");
    $query->execute(array($tenant_id));

   	$res = $query->fetch(PDO::FETCH_ASSOC);

    if(count($res)){
      print_r(json_encode($res));
    }else{
      return false;
    }