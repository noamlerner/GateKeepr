<?php

require('twilio-php/Services/Twilio.php'); 
 
$account_sid = 'AC04ae19d25b9678356361e1b7224f8184'; 
$auth_token = '02460acf1b1ff661b7e92f468ff69e97'; 
$client = new Services_Twilio($account_sid, $auth_token);

$to = $_GET['to'];
$body = $_GET['body']; 
 
$client->account->messages->create(array( 
	'To' => $to, 
	'From' => "+13477064710", 
	'Body' => $body
));