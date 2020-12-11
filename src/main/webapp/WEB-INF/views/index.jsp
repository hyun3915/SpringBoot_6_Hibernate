<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./template/bootStrap.jsp"></c:import>
<link href="./css/index.css" rel="stylesheet">
</head>
<body>
<c:import url="./template/header.jsp"></c:import>


<div class="container">


  <h3>V1</h3>
  <p>The .navbar-right class is used to right-align navigation bar buttons.</p>

  
    <a href="./rest/member/sub/1">Click</a>
  
  <button class="btn btn-danger" id="btn">Click Me</button>
  <button class="btn btn-primary" id="list">Click Me</button>
  
</div>

<script type="text/javascript">

	$("#list").click(function(){
		$.get("board/boardList?curPage=1", function(data) {
			console.log(data);})
	});

/*	var v = '{"name":"sub", "age":27, "job":{"sing":"top","actor":"second"}, "food":["berger", "milk", "steak"]}';
	v = JSON.parse(v);
	//변수명.json의 키(name)
	alert(v.name);
	alert(v.age);
	alert(v.job.sing);
	alert(v.job.actor);
	alert(v.food[2]);
	for(i=0; i<v.food.length; i++) {
		console.log(v.food[i]);
	}
*/

$('#btn').click(function(){

/*	$.get("https://api.manana.kr/calendar/2020/12/00/all/kr.json",function(data){
		for(i=0; i<data.length; i++){
		console.log(data[i].date);
		console.log(data[i].name);
		}
		}); */


	$.ajax({
		type:"GET",
		url:"board/boardSelect",
		data:{
			num:122
		},
		success:function(data){
			alert(data);
		}
		
	});
	
});


</script>

</body>
</html>