<?php

require "/home/sunil/vendor/autoload.php";
$app = new \Slim\Slim();

$app->get('/calc/:op1/:operator/:op2', function ($op1,$optr,$op2) {
$operand1 = floatval($op1);
$operator = $optr;
$operand2 = floatval($op2);
// $operand1 = preg_replace('/\s+/', '', $operand1);
// $operand2 = preg_replace('/\s+/', '', $operand2);
check($operand1,$operator,$operand2);

});

$final_ar=array();
/*------------------*/
function check($operand1=null,$operator=null,$operand2=null){
	$servername = "localhost";
	$username = "root";
	$password = "root";
	$dbname = "DAC";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);

	// Check connection
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}

	$sql = "select result from cache where op1=$operand1 && operator='$operator' && op2=$operand2;";
	
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
    // output data of each row
    	while($row = $result->fetch_assoc()) {
    		$tmp=trim($row['result']);
    		$final_ar['status']="ok";
    		$final_ar['result']=$tmp;
			echo json_encode($final_ar);
        	// echo trim($row['result']);
    	}
	} 
	else {
    insert($operand1,$operator,$operand2);
	}
$conn->close();
}
/*------------------*/

/*------------------*/

function insert($operand1=null,$operator=null,$operand2=null){
	$servername = "localhost";
	$username = "root";
	$password = "root";
	$dbname = "DAC";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);

	// Check connection
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}

	if($operator == '+'){
		$final = $operand1 + $operand2;
	}

	else if($operator == '-'){
		$final = $operand1 - $operand2;
	}

	else if($operator == '#'){
		if($operand2 == 0) {
			$final_ar['status']="nok";
    		$final_ar['result']="cannot divide by 0";
			echo json_encode($final_ar);
			// echo "cannot divide by 0";
			die();
		}
		$operator = '/';
		$final = $operand1 / $operand2;
	}

	else if($operator == 'X'){
		$final = $operand1 * $operand2;
	}
	$final = trim($final);
	if($final>99999999999999){
		$final_ar['status']="nok";
    	$final_ar['result']="result out of bound";
		// echo "result out of bound";
		die();
	}
	$sql = "insert into cache values($operand1,'$operator',$operand2,$final);";

	if ($conn->query($sql) === TRUE) {
		$final_ar['status']="ok";
    	$final_ar['result']=trim($final);
		echo json_encode($final_ar);
	    // echo trim($final);
	} 
	else {
		$final_ar['status']="nok";
    	$final_ar['result']="please try again";
		echo json_encode($final_ar);
	    // echo "Error: ";// . $sql . "<br>" . $conn->error;
	}

	$conn->close();
}
/*------------------*/
// $final_ar['status']="nok";
//     	$final_ar['result']="please try again";
// $app->response()->header('Content-Type', 'application/json');
// echo json_encode($final_ar);
// var_dump($final_ar);


$app->run();
?>