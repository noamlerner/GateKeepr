<?php
 
 class DBAccess{
    public function initialize(){
 
            try{
                $pdo = new PDO('mysql:host=127.0.0.1:3305;dbname=app' , 'root', '');
                $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            }catch(PDOEXCEPTION $e){
                echo $e->getMessage();
                die();
            }
        return $pdo;
    }
}