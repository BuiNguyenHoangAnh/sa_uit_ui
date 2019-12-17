<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Thống kê phân tích</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>
	<div class="container">
		<h1>MỨC ĐỘ QUAN TÂM ĐỐI VỚI CÁC CHIẾN DỊCH TUYỂN SINH</h1>
	
		<div class="statistics">
			<div class="control">
				<form method="post" action="statistic">					
					<span>Hiển thị: </span>
					<select class="filter" name="filter">
		  				<option type="radio" value="month" title="Hiển thị kết quả trong vòng 6 tháng gần nhất"> Tháng</option>
		  				<option type="radio" value="day" title="Hiển thị kết quả trong vòng 7 ngày gần nhất"> Ngày</option>
		  				<option type="radio" value="hour" title="Hiển thị kết quả trong vòng 48 giờ gần nhất"> Giờ</option>
					</select>
					
					<input type="submit" value="Lọc">
				</form>
			</div>

			<div class="tab">
				<button class="tablinks training" onclick="">Đào tạo</button>
  				<button class="tablinks facility" onclick="">Cơ sở vật chất</button>
			</div>
		</div>

		<div id="tab1" class="tabcontent">
			<h3>Biểu đồ thống kê cảm xúc</h3>
			<canvas  id="chart" class="chart"></canvas >

			<script type="text/javascript">
				Chart.defaults.global.elements.line.fill = false;
				var barChartData = {
			  		labels: ['2016', '2017', '2018', '2019'],
			  		datasets: [{
				    	type: 'bar',
					    label: 'Tiêu cực',
					    yAxisID: "y-axis-0",
					    backgroundColor: "rgba(217,83,79,0.75)",
					    data: [1000, 2000, 4000, 5000]
			  		}, {
					    type: 'bar',
					    label: 'Tích cực',
					    yAxisID: "y-axis-0",
					    backgroundColor: "rgba(92,184,92,0.75)",
					    data: [500, 600, 700, 800]
				  	}, {
					    type: 'line',
					    label: 'Số lượng đề cập',
					    yAxisID: "y-axis-0",
					    backgroundColor: "rgba(151,187,205,1)",
					    data: [1500, 2600, 4700, 5800]
				  	}]
				};
	
	
				var ctx = document.getElementById("chart").getContext("2d");
				var ch = new Chart(ctx, {
			  		type: 'bar',
			  		data: barChartData,
			  		options: {
						tooltips: {
							mode: 'label'
						},
						responsive: true,
						scales: {
							xAxes: [{
								stacked: true
							}],
							yAxes: [{
								stacked: true,
								position: "left",
								id: "y-axis-0",
							}]
						}
				  	}
				});
			</script>
		</div>
		
		<div class="demo-link" style="font-size: 20px; margin-top: 16px;">
			<a href="sample.jsp">Demo phân tích cảm xúc</a>
		</div>
	</div>

	<style>
		h1 {
			text-align: center;
		}
		
		.demo-link {
			text-align: right;
			cursor: pointer;
		}
		
		.tab {
 				overflow: hidden;
 				border: 1px solid #ccc;
 				background-color: #f1f1f1;
		}

		.tab button {
		  	background-color: inherit;
		  	float: left;
		  	border: none;
		  	outline: none;
		  	cursor: pointer;
		  	padding: 14px 16px;
		  	transition: 0.3s;
		  	font-size: 17px;
			width: 50%;
			font-weight: 500;
		}

		.tab button:hover {
 				background-color: #ddd;
			border-bottom-width: 3px;
			font-weight: 700;
		}

		.tab button.active {
 				background-color: #ccc;
			border-bottom-width: 3px;
			font-weight: 700;
		}

		.tabcontent {
 				// display: none;
 				padding: 6px 12px;
 				border: 1px solid #ccc;
 				border-top: none;
		}

		.tab .training {
			border-bottom: 2px solid red;
		}

		.tab .facility {
			border-bottom: 2px solid green;
		}

		.chart {
			margin-left: auto;
			margin-right: auto;
		}

		select {
			margin-left: 4px;
			margin-bottom: 8px;
			font-size: 16px;
			height: 30px;
			border-radius: 4px;
		}

		.filter {
			width: 100px;
		}

		.options {
			width: 300px;
			margin-right: 16px;
		}

		.control {
			text-align: right;
		}
		
		span {
			color: blue;
		}
		
		h3 {
			text-align: center;
			color: blue;
		}
	</style>
</body>
</html>
