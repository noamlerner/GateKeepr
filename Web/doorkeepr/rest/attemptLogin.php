 
 <?php
    //Autoload functionality 
     header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $name = $_GET['name'];
    $password = $_GET['password'];

    $query = $pdo->prepare("SELECT * FROM users WHERE name = ? and password = ?");	
    $query->execute(array($name, $password));

    $res = $query->fetchAll(PDO::FETCH_ASSOC);

    if(count($res)){
    	print_r($res);
    }else{
    	print true;
    }