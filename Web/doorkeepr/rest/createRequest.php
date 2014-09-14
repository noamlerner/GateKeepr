 
 <?php
    //Autoload functionality 
     header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $first = $_GET['firstName'];
    $last = $_GET['lastName'];
    $visitor = $first . " " . $last;
    $tenant_id = $_GET['tenant_id'];
    $phone = $_GET['phone'];

    $query = $pdo->prepare("INSERT INTO  requests  (phone, tenant_id, visitor) VALUES (?,?,?)");	
    $query->execute(array($phone, $tenant_id, $visitor));


