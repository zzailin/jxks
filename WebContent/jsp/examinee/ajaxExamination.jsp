<%@ page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>"></base>
<title>驾校考试系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<style type="text/css">
body {
	padding-top: 16px;
	background-color: #eee;
}

.jumbotron {
	width: 70%;
	margin: 0 auto;
}

.left {
	float: left;
	width: 50%;
}

.right {
	float: left;
	width: 50%;
}

label {
	font-weight: normal;
}
</style>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3>
				考试剩余时间:<span id="testTime" class="label label-danger"></span>
			</h3>
			<div class="panel panel-default">
				<div class="panel-heading">
					科目${sessionScope.userEntity.userInfo.subject} 题目<span id="page">${sessionScope.page}</span>
				</div>
				<div class="panel-body">
					<input id="formPage" name="page" type="hidden" value="${sessionScope.page}">
					<input id="id" name="id" type="hidden" value="${sessionScope.examEntity.id}">
					<span id="answer" class="hide">${sessionScope.examEntity.answer}</span>
					<div class="left">
						<p id="question" class="text-left">${sessionScope.examEntity.question}</p>
						<p class="text-left">
							 <input id="input1" type="radio" name="answer"
								value="1"><label for="input1" id="item1">${sessionScope.examEntity.item1}</label>
						</p>
						<p class="text-left">
							<input id="input2" type="radio" name="answer"
								value="2"><label for="input2" id="item2">${sessionScope.examEntity.item2}</label>
						</p>
							<p class="text-left">
								<input id="input3" type="radio" name="answer" 
									value="3"><label for="input3" id="item3">${sessionScope.examEntity.item3}</label>
							</p>
							<p class="text-left">
								<input id="input4" type="radio" name="answer"
									value="4"><label for="input4" id="item4">${sessionScope.examEntity.item4}</label>
							</p>
						</form>
						<c:if test="${sessionScope.page!=1}">
							<button id="pageUp" class="btn btn-primary" role="button"
								onclick="pageChange(this)" value="pageUp">上一题</button>
						</c:if>
						<span id="examNum" class="hide">${sessionScope.examNum}</span>
							<button id="pageDown" class="btn btn-primary" role="button"
								onclick="pageChange(this)" value="pageDown">下一题</button>
						<button id="submit" class="btn btn-primary pull-right" role="button">交卷</button>
						<a id="a" class="hide"></a>
					</div>
					<div class="right">
						<img src="${sessionScope.examEntity.url}" alt="..."
							class="img-rounded img-responsive" width="300px">
					</div>
				</div>
			</div>
		</div>
	</div>
	<canvas height="106" width="1366" style="position: fixed; top: 0px; left: 0px; z-index: -1; opacity: 0.5;" id="c_n16"></canvas>
<script type="text/javascript" color="255,0,0" opacity="0.5" count="99" src="js/canvas.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#answer").text().length>1){
				document.getElementById("input1").type="checkbox";
				document.getElementById("input2").type="checkbox";
				document.getElementById("input3").type="checkbox";
				document.getElementById("input4").type="checkbox";
			};
			
			if($("#item3").text().length==0)
				$("#input3").hide();
			else
				$("#input3").show();
			if($("#item4").text().length==0)
				$("#input4").hide();
			else
				$("#input4").show();
			
			if($("input[name='answer']:checked").val()==null)
				$("#pageDown").attr("disabled","disabled");
			$("input[name='answer']").change(function(){
				$("#pageDown").removeAttr("disabled");
			});
		});
		$("#submit").click(function(){
			var id = $("#id").val();
			var answer = $("input[name='answer']:checked").val();
			var url = "<%=basePath%>doResult.action?answer="+answer+"&id="+id;
			$("#a").attr("href",url);
			document.getElementById("a").click();
		});
	</script>
	<script type="text/javascript">
		var value = function(){
			var aa = "";
			$("input[name='answer']:checked").each(function(){ 
				aa+=$(this).val()+", ";
				});
			return aa.substring(0,aa.length-2);
		};
		
		var pageChange = function(button) {
			$.ajax({
				url : 'ajaxAction.action',
				type : 'post',
				data : {
					'status' : $(button).val(),
					'page' : $("#page").text(),
					'answer' : value(),
					'id':$("#id").val()
				},
				dataType : 'json',
				success : function(data) {
					if(data.examEntity.answer.length>1){
						document.getElementById("input1").type="checkbox";
						document.getElementById("input2").type="checkbox";
						document.getElementById("input3").type="checkbox";
						document.getElementById("input4").type="checkbox";
					}
					if(data.examEntity.answer.length==1){
						document.getElementById("input1").type="radio";
						document.getElementById("input2").type="radio";
						document.getElementById("input3").type="radio";
						document.getElementById("input4").type="radio"; 
					}
					
					var inputList = $("input[name='answer']");
					for(var x=0;x<inputList.length;x++){
						inputList[x].checked=false;  //取消选中
					} 
					
					if($("input[name='answer']:checked").val()==null)
						$("#pageDown").attr("disabled","disabled");
					
					$("#id").val(data.examEntity.id);
					$("#page").html(data.page);
					
					if(data.page==$("#examNum").text()){
						$("#pageDown").hide();
					}
					
					$("#formPage").val(data.page);
					$("#question").html(data.examEntity.question);
					$("#item1").text(data.examEntity.item1);
					$("#item2").text(data.examEntity.item2);
					
					if(data.examEntity.item3.length!=0)
						$("#input3").show();
					else
						$("#input3").hide();
					$("#item3").text(data.examEntity.item3);
					if(data.examEntity.item4.length!=0)
						$("#input4").show();
					else
						$("#input4").hide();
					$("#item4").text(data.examEntity.item4);
					if(data.examEntity.url.length!=0)
						$("img").attr("src",data.examEntity.url);
					else
						$("img").attr("src","");
				},
				error : function(xhr, textStatus) {
					alert('网络繁忙')
				},
				complete : function() {
				}
			});
		};
	</script>
	<script type="text/javascript">
		var interval = 1000;
		var timer = window.setInterval(function() {
			ShowCountDown();
		}, interval);
		var leftsecond = 270;//考试时间
		function ShowCountDown() {
			leftsecond--;
			if (leftsecond >= 0) {
				var minute = Math.floor(leftsecond / 60);
				if (minute < 10) {
					minute = "0" + minute;
				}
				var second = Math.floor(leftsecond - minute * 60);
				if (second < 10)
					second = "0" + second;
				var cc = document.getElementById("testTime");
				cc.innerHTML = minute + ":" + second;
			if (leftsecond === 0) {
				alert("test over!!!");
				
				var id = $("#id").val();
				var answer = value();
				var url = "<%=basePath%>doResult.action?answer="+answer+"&id="+id;
				$("#a").attr("href",url);
				document.getElementById("a").click();
				
				clearInterval(timer);
			}
			}
		};
	</script>
</body>
</html>