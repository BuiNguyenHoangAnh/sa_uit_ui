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
  
  <script src="https://code.highcharts.com/highcharts.js"></script>
  <script src="https://code.highcharts.com/modules/series-label.js"></script>
  <script src="https://code.highcharts.com/modules/exporting.js"></script>
  <script src="https://code.highcharts.com/modules/export-data.js"></script>
  <script src="https://code.highcharts.com/modules/accessibility.js"></script>
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
      <figure class="highcharts-figure">
        <div id="chart"></div>
      </figure>
      
      <%
      	String label = (String) request.getAttribute("label");
      
      	String posTrainingComment = (String) request.getAttribute("posTrainingComment");
      	String negTrainingComment = (String) request.getAttribute("negTrainingComment");
      	String posTrainingPost = (String) request.getAttribute("posTrainingPost");
      	String negTrainingPost = (String) request.getAttribute("negtrainingPost");
      	String totalTraining = (String) request.getAttribute("totalTraining");
      	
      	String posFacilityComment = (String) request.getAttribute("posFacilityComment");
      	String negFacilityComment = (String) request.getAttribute("negFacilityComment");
      	String posFacilityPost = (String) request.getAttribute("posFacilityPost");
      	String negFacilityPost = (String) request.getAttribute("negFacilityPost");
      	String totalFacility = (String) request.getAttribute("totalFacility");
      %>

      <script type="text/javascript">
        Highcharts.chart('chart', {
          chart: {
              type: 'bar'
          },
          title: {
              text: 'Biểu đồ thống kê cảm xúc'
          },
          subtitle: {
              text: 'Biểu đồ thống kê số lượng Bài đăng/Bình luận Tích cực và Tiêu cực dựa trên các khía cạnh Đào tạo và Cơ sở vật chất'
          },
          xAxis: [{
              title: {
                  text: 'ĐÀO TẠO',
              },
              categories: <%= label%>,
              reversed: false,
              labels: {
                  step: 1
              },
              accessibility: {
                  description: 'Age (male)'
              }
          }, { // mirror axis on right side
              title: {
                  text: 'CƠ SỞ VẬT CHẤT',
              },
              opposite: true,
              reversed: false,
              categories: <%= label%>,
              linkedTo: 0,
              labels: {
                  step: 1
              },
              accessibility: {
                  description: 'Age (female)'
              }
          }],
          yAxis: {
              title: {
                text: null,
              },
              labels: {
                  formatter: function () {
                      return Math.abs(this.value);
                  }
              },
              accessibility: {
                  description: 'Percentage population',
                  rangeDescription: 'Range: 0 to 5%'
              }
          },
          plotOptions: {
              series: {
                  stacking: 'normal'
              }
          },
          tooltip: {
              formatter: function () {
                  return '<b>' + this.point.category + '</b><br/>'+ this.series.name + ': '   +
                      Highcharts.numberFormat(Math.abs(this.point.y), 1);
              }
          },
          series: [{
              type: 'column',
              name: 'Bình luận Tích cực',
              data: <%= posFacilityComment%>,
              stack: 1
            }, {
              type: 'column',
              name: 'Bình luận Tiêu cực',
              data: <%= negFacilityComment%>,
              stack: 1
            }, {
              type: 'column',
              name: 'Bài đăng Tích cực',
              data: <%= posFacilityPost%>,
              stack: 1
            }, {
              type: 'column',
              name: 'Bài đăng Tiêu cực',
              data: <%= negFacilityPost%>,
              stack: 1
            }, {
              type: 'spline',
              name: 'Tổng đề cập',
              data: <%= totalFacility%>,
              stack: 1,
              marker: {
                  lineWidth: 2,
                  lineColor: Highcharts.getOptions().colors[3],
                  fillColor: 'white'
              }
            }, {
              type: 'column',
              name: 'Bình luận Tích cực',
              data: <%= posTrainingComment%>,
              stack: 1
            }, {
              type: 'column',
              name: 'Bình luận Tiêu cực',
              data: <%= negTrainingComment%>,
              stack: 1
            }, {
              type: 'column',
              name: 'Bài đăng Tích cực',
              data: <%= posTrainingPost%>,
              stack: 1
            }, {
              type: 'column',
              name: 'Bài đăng Tiêu cực',
              data: <%= negTrainingPost%>,
              stack: 1
            }, {
              type: 'spline',
              name: 'Tổng đề cập',
              data: <%= totalTraining%>,
              stack: 2,
              marker: {
                  lineWidth: 2,
                  lineColor: Highcharts.getOptions().colors[3],
                  fillColor: 'white'
              }
            }
          ]
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