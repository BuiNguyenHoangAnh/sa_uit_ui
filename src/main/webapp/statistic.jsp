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
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
</head>
<body>
  <div class="container">
    <h1>MỨC ĐỘ QUAN TÂM ĐỐI VỚI CÁC CHIẾN DỊCH TUYỂN SINH</h1>
  
    <div class="statistics">
      <div class="control">
        <form method="post" action="statistic">         
          <span class="text-primary">Hiển thị: </span>
          <select class="filter form-control" name="filter">
              <option value="month" title="Hiển thị kết quả trong vòng 6 tháng gần nhất"> Tháng</option>
              <option value="day" title="Hiển thị kết quả trong vòng 7 ngày gần nhất"> Ngày</option>
          </select>
          
          <input type="submit" class="btn btn-primary" value="Lọc">
        </form>
      </div>
    </div>

    <div class="tabcontent">
      <canvas id="chart"></canvas>
      
      <%
      	String label = (String) request.getAttribute("label");
      
      	String posTrainingComment = (String) request.getAttribute("posTrainingComment");
      	String negTrainingComment = (String) request.getAttribute("negTrainingComment");
      	String posTrainingPost = (String) request.getAttribute("posTrainingPost");
      	String negTrainingPost = (String) request.getAttribute("negTrainingPost");
      	String posTraining = (String) request.getAttribute("posTraining");
      	String negTraining = (String) request.getAttribute("negTraining");
      	String totalTraining = (String) request.getAttribute("totalTraining");
      	
      	String posFacilityComment = (String) request.getAttribute("posFacilityComment");
      	String negFacilityComment = (String) request.getAttribute("negFacilityComment");
      	String posFacilityPost = (String) request.getAttribute("posFacilityPost");
      	String negFacilityPost = (String) request.getAttribute("negFacilityPost");
      	String posFacility = (String) request.getAttribute("posFacility");
      	String negFacility = (String) request.getAttribute("negFacility");
      	String totalFacility = (String) request.getAttribute("totalFacility");
      %>

      <script type="text/javascript">
      window.chartColors = {
    		  red: 'rgb(255, 99, 132)',
    		  orange: 'rgb(255, 159, 64)',
    		  yellow: 'rgb(255, 205, 86)',
    		  green: 'rgb(75, 192, 192)',
    		  blue: 'rgb(54, 162, 235)',
    		  purple: 'rgb(153, 102, 255)',
    		  grey: 'rgb(231,233,237)'
    		};

    		var ctx = document.getElementById("chart").getContext("2d");

    		var myChart = new Chart(ctx, {
    		  type: 'bar',
    		  data: {
    		    labels: <%= label%>,
    		    datasets: [{
    		      type: 'line',
    		      label: 'Lương đề cập Đào tạo',
    		      borderColor: "rgba(151,255,0,1)",
    		      borderWidth: 2,
    		      fill: false,
    		      data: <%= totalTraining %>,
    		    }, {
    		      type: 'line',
    		      label: 'Lượng đề cập Cơ sở vật chất',
    		      borderColor: "rgba(151,187,205,1)",
    		      borderWidth: 2,
    		      fill: false,
    		      data: <%= totalFacility %>,
    		    }, {
    		      type: 'bar',
    		      label: 'Tích cực',
    		      backgroundColor: "rgba(99,255,132,0.5)",
    		      stack: 'Stack 0',
    		      data: <%= posTraining %>,
    		    }, {
    		      type: 'bar',
    		      label: 'Tiêu cực',
    		      backgroundColor: "rgba(255,99,132,0.5)",
    		      stack: 'Stack 0',
    		      data: <%= negTraining %>,
    		    }, {
    		      type: 'bar',
    		      label: 'Tích cực',
    		      backgroundColor: "rgba(99,255,132,0.5)",
    		      stack: 'Stack 1',
    		      data: <%= posFacility %>,
    		    }, {
    		      type: 'bar',
    		      label: 'Tiêu cực',
    		      backgroundColor: "rgba(255,99,132,0.5)",
    		      stack: 'Stack 1',
    		      data: <%= negFacility %>,
    		    }]
    		  },
    		  options: {
    		    responsive: true,
    		    title: {
    		      display: true,
    		      text: 'Chart.js Stacked Bar and Unstacked Line Combo Chart'
    		    },
    		    legend: {
    		      labels: {
    		        generateLabels: function(chart) {
    		          return Chart.defaults.global.legend.labels.generateLabels.apply(this, [chart]).filter(function(item, i){
    		          		return i <= 3;
    		          });
    		        }
    		      }
    		    },
    		    tooltips: {
    		    	mode: 'single',
    		    	enabled: true,
    		    	callbacks: {
    		    		label: function(tooltipItems, data) {
    		    			var comment = 0;
    						var post = 0;
    						
    						var posTrainingComment = <%= posTrainingComment %>;
    						var posTrainingPost = <%= posTrainingPost %>;
    						var negTrainingComment = <%= negTrainingComment %>;
    						var negTrainingPost = <%= negTrainingPost %>;
    						
    						var posFacilityComment = <%= posFacilityComment %>;
    						var posFacilityPost = <%= posFacilityPost %>;
    						var negFacilityComment = <%= negFacilityComment %>;
    						var negFacilityPost = <%= negFacilityPost %>;

    		    			switch(data.datasets[tooltipItems.datasetIndex].label) {
    		    				case 'Lượng đề cập Đào tạo': return 'Đào tạo: ' +tooltipItems.xLabel + ': ' + tooltipItems.yLabel;
    		    				case 'Lượng đề cập Cơ sở vật chất': return 'Cơ sở vật chất: ' +tooltipItems.xLabel + ': ' + tooltipItems.yLabel;
    		    				case 'Tích cực':
    		    					// dao tao tich cuc 
    		    					if(data.datasets[tooltipItems.datasetIndex].stack == 'Stack 0') {
    		    						comment = posTrainingComment[tooltipItems.index];
    			    					post = posTrainingPost[tooltipItems.index];
    			    					return 'Đào tạo:  ' +tooltipItems.xLabel + ': ' + tooltipItems.yLabel + '  Bình luận: '+ comment + '  Bài đăng: ' + post;
    		    					} else { // csvc tich cuc
    		    						comment = posFacilityComment[tooltipItems.index];
    			    					post = posFacilityPost[tooltipItems.index];
    			    					return 'Cơ sở vật chất:  ' + tooltipItems.xLabel + ': ' + tooltipItems.yLabel + '  Bình luận: '+ comment + '  Bài đăng: ' + post;
    		    					}
    		    				case 'Tiêu cực':
    		    					// dao tao tieu cuc 
    		    					if(data.datasets[tooltipItems.datasetIndex].stack == 'Stack 0') {
    		    						comment = negTrainingComment[tooltipItems.index];
    			    					post = negTrainingPost[tooltipItems.index];
    		    						return 'Đào tạo:  ' +tooltipItems.xLabel + ': ' + tooltipItems.yLabel + '  Bình luận: '+ comment + '  Bài đăng: ' + post;
    		    					} else { // csvc tieu cuc
    		    						comment = negFacilityComment[tooltipItems.index];
    			    					post = negFacilityPost[tooltipItems.index];
    		    						return 'Cơ sở vật chất:  ' + tooltipItems.xLabel + ': ' + tooltipItems.yLabel + '  Bình luận: '+ comment + '  Bài đăng: ' + post;
    		    					}
    		    			}
    		    		}
    		    	}
    		    },
    		    scales: {
    		      xAxes: [{
    		        stacked: true,
    		      }]
    		    }
    		  }
    		});
      </script>
    </div>
    
    <div class="demo-link" style="font-size: 18px; margin-top: 16px;">
      <a href="sample.jsp">Demo phân tích cảm xúc</a>
    </div>
  </div>

  <style>
    .container {
      width: 1200px;
    }
    h1 {
      text-align: center;
    }

    .statistics {
    	margin-top: 8px;
    	margin-bottom: 8px;
    }
    
    .demo-link {
      text-align: right;
      cursor: pointer;
    }
    .tabcontent {
        padding: 6px 12px;
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
      display: inline;
    }
    .control {
      text-align: right;
    }
    
    h3 {
      text-align: center;
      color: blue;
    }
  </style>
</body>
</html>