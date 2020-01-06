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
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.0/Chart.bundle.min.js"></script>
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
      Chart.defaults.groupableBar = Chart.helpers.clone(Chart.defaults.bar);
		Chart.defaults.global.elements.line.fill = false;

		var helpers = Chart.helpers;
		Chart.controllers.groupableBar = Chart.controllers.bar.extend({
		  calculateBarX: function (index, datasetIndex) {
		    // position the bars based on the stack index
		    var stackIndex = this.getMeta().stackIndex;
		    return Chart.controllers.bar.prototype.calculateBarX.apply(this, [index, stackIndex]);
		  },

		  hideOtherStacks: function (datasetIndex) {
		    var meta = this.getMeta();
		    var stackIndex = meta.stackIndex;

		    this.hiddens = [];
		    for (var i = 0; i < datasetIndex; i++) {
		      var dsMeta = this.chart.getDatasetMeta(i);
		      if (dsMeta.stackIndex !== stackIndex) {
		        this.hiddens.push(dsMeta.hidden);
		        dsMeta.hidden = true;
		      }
		    }
		  },

		  unhideOtherStacks: function (datasetIndex) {
		    var meta = this.getMeta();
		    var stackIndex = meta.stackIndex;

		    for (var i = 0; i < datasetIndex; i++) {
		      var dsMeta = this.chart.getDatasetMeta(i);
		      if (dsMeta.stackIndex !== stackIndex) {
		        dsMeta.hidden = this.hiddens.unshift();
		      }
		    }
		  },

		  calculateBarY: function (index, datasetIndex) {
		    this.hideOtherStacks(datasetIndex);
		    var barY = Chart.controllers.bar.prototype.calculateBarY.apply(this, [index, datasetIndex]);
		    this.unhideOtherStacks(datasetIndex);
		    return barY;
		  },

		  calculateBarBase: function (datasetIndex, index) {
		    this.hideOtherStacks(datasetIndex);
		    var barBase = Chart.controllers.bar.prototype.calculateBarBase.apply(this, [datasetIndex, index]);
		    this.unhideOtherStacks(datasetIndex);
		    return barBase;
		  },

		  getBarCount: function () {
		    var stacks = [];

		    // put the stack index in the dataset meta
		    Chart.helpers.each(this.chart.data.datasets, function (dataset, datasetIndex) {
		      var meta = this.chart.getDatasetMeta(datasetIndex);
		      if (meta.bar && this.chart.isDatasetVisible(datasetIndex)) {
		        var stackIndex = stacks.indexOf(dataset.stack);
		        if (stackIndex === -1) {
		          stackIndex = stacks.length;
		          stacks.push(dataset.stack);
		        }
		        meta.stackIndex = stackIndex;
		      }
		    }, this);

		    this.getMeta().stacks = stacks;
		    return stacks.length;
		  },
		});

		var data = {
		  labels: <%= label%>,
		  datasets: [
		    {
		      label: "Tích cực",
		      backgroundColor: "rgba(99,255,132,0.5)",
		      data: <%= posTraining %>,
		      stack: 1
		    },
		    {
		      label: "Tiêu cực",
		      backgroundColor: "rgba(255,99,132,0.5)",
		      data: <%= negTraining %>,
		      stack: 1
		    },
		    {
		      type: 'line',
		      label: 'Lượng đề cập Đào tạo',
		      stack: 3,
		      backgroundColor: "rgba(151,255,0,1)",
		      data: <%= totalTraining %>
			},
			{
		      type: 'line',
		      label: 'Lượng đề cập Cơ sở vật chất',
		      stack: 4,
		      backgroundColor: "rgba(151,187,205,1)",
		      data: <%= totalFacility %>
			},
		    {
		      label: "Tích cực",
		      backgroundColor: "rgba(99,255,132,0.5)",
		      data: <%= posFacility %>,
		      stack: 2
		    },
		    {
		      label: "Tiêu cực",
		      backgroundColor: "rgba(255,99,132,0.5)",
		      data: <%= negFacility %>,
		      stack: 2
		    }
		  ]
		};

		var ctx = document.getElementById("chart").getContext("2d");
		new Chart(ctx, {
		  type: 'groupableBar',
		  data: data,
		  options: {
		    legend: {
		      labels: {
		        generateLabels: function(chart) {
		          return Chart.defaults.global.legend.labels.generateLabels.apply(this, [chart]).filter(function(item, i){
		          		return i <= 3;
		          });
		        }
		      }
		    },
		    scales: {
		      yAxes: [{
		        stacked: true,
		      }]
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
		    			if(data.datasets[tooltipItems.datasetIndex].stack == 1) {
		    				switch (tooltipItems.datasetIndex) {
		    				case 0: // dao tao tich cuc 
		    					comment = posTrainingComment[tooltipItems.index];
		    					post = posTrainingPost[tooltipItems.index];
		    					break;
		    				case 1: // dao tao tieu cuc 
		    					comment = negTrainingComment[tooltipItems.index];
		    					post = negTrainingPost[tooltipItems.index];
		    					break;
		    				}
		    				return 'Đào tạo:  ' +tooltipItems.xLabel + ': ' + tooltipItems.yLabel + '  Bình luận: '+ comment + '  Bài đăng: ' + post;
		    			} else {
		    				if(data.datasets[tooltipItems.datasetIndex].stack == 2) {
		    					switch (tooltipItems.datasetIndex) {
			    				case 4: // csvc tich cuc 
			    					comment = posFacilityComment[tooltipItems.index];
			    					post = posFacilityPost[tooltipItems.index];
			    					break;
			    				case 5: // csvc tieu cuc 
			    					comment = negFacilityComment[tooltipItems.index];
			    					post = negFacilityPost[tooltipItems.index];
			    					break;
			    				}
				    			return 'Cơ sở vật chất:  ' + tooltipItems.xLabel + ': ' + tooltipItems.yLabel + '  Bình luận: '+ comment + '  Bài đăng: ' + post;
		    				} else {
		    					if(data.datasets[tooltipItems.datasetIndex].stack == 3) {
		    						return 'Đào tạo: ' +tooltipItems.xLabel + ': ' + tooltipItems.yLabel;
		    					} else {
		    						return 'Cơ sở vật chất: ' +tooltipItems.xLabel + ': ' + tooltipItems.yLabel;
		    					}
		    				}
		    			}
		    		}
		    	}
		    },
		    title: {
		    	display: true,
		    	text: 'Biểu đồ thống kê cảm xúc'
		    },
		    subtitle: {
		    	display: true,
		    	text: 'Biểu đồ thống kê số lượng Bài đăng/Bình luận Tích cực và Tiêu cực dựa trên các khía cạnh Đào tạo và Cơ sở vật chất'
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