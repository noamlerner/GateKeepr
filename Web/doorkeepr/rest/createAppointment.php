 
 <?php
    //Autoload functionality 
     header("Access-Control-Allow-Origin: *");
	include "../DBAccess.php";

    $dbA = new DBAccess();
    $pdo = $dbA->initialize();

    $visitor = $_GET['visitor'];
    $tenant_id = $_GET['tenant_id'];

    $building_id_q = $pdo->query("SELECT building_id FROM users WHERE name = '$tenant_id'");
    $building_id_a = $building_id_q->fetch(PDO::FETCH_ASSOC);
    $building_id = $building_id = $building_id_a['building_id'];
    $datetime = new DateTime();
    $end_arrival_time = $datetime->add(new DateInterval('PT1H'));
    $end_arrival_time = $end_arrival_time->format('Y-m-d H:i:s');

    $datetime2 =  new DateTime();
    $arrival_time = $datetime2->sub(new DateInterval('PT1H'));
    $arrival_time = $arrival_time->format('Y-m-d H:i:s');

    $query = $pdo->prepare("INSERT INTO  appointments (tenant_id, visitor, arrival_time, end_arrival_time, building_id) VALUES (?,?,NOW(), ?,  ?)");	
    $query->execute(array($tenant_id, $visitor , $end_arrival_time,  $building_id));


