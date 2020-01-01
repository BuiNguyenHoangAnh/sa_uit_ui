package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Statistic;
import util.connectDatabase;

/**
 * Servlet implementation class statistic
 */
public class statistic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String posTraining = "posTraining";
	private String negTraining = "negTraining";
	private String posFacility = "posFacility";
	private String negFacility = "negFacility";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public statistic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String filter = request.getParameter("filter");
		String[] labels;
		String label = "";
		String posTrainingComment= "";
		String negTrainingComment= "";
		String posFacilityComment= "";
		String negFacilityComment= "";
		String posTrainingPost= "";
		String negTrainingPost= "";
		String posFacilityPost= "";
		String negFacilityPost= "";
		String totalTraining= "";
		String totalFacility= "";
		
		connectDatabase connectDB = new connectDatabase();
		List<Statistic> list = connectDB.getDataFromDB();
		
		System.out.println(list);
		System.out.println(filter);
		
		if (filter.contentEquals("month")) {
			labels = this.createMonthLabels(getCurrentMonth());
			for(int i = 0; i <= labels.length-1; i++) {
				if(i == 0) {
					label += "['" + labels[i] + "',";
				} else {
					if(i == labels.length-1) {
						label += labels[i] + "']";
					} else {
						label += labels[i] + "','";
					}
				}
			}
			
			for(int i = 0; i <= labels.length-1; i++) {
				if(i == 0) {
					posTrainingComment += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + ",";
					negTrainingComment += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + ",";
					posTrainingPost += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + ",";
					negTrainingPost += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + ",";
					totalTraining += "[-" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
					
					posFacilityComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + ",";
					negFacilityComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + ",";
					posFacilityPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + ",";
					negFacilityPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + ",";
					totalFacility += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
				} else {
					if(i == labels.length-1) {
						posTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + "]";
						negTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + "]";
						posTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + "]";
						negTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + "]";
						totalTraining += "-" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + "]";
						
						posFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + "]";
						negFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + "]";
						posFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + "]";
						negFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + "]";
						totalFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + "]";
					} else {
						posTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + ",";
						negTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + ",";
						posTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + ",";
						negTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + ",";
						totalTraining += "-" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
						
						posFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + ",";
						negFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + ",";
						posFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + ",";
						negFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + ",";
						totalFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
					}
				}
			}
			System.out.println(posFacilityComment);
		} else {
			labels = this.createDayLabels(getCurrentDay());
			for(int i = 0; i <= labels.length-1; i++) {
				if(i == 0) {
					label += "['" + labels[i] + "',";
				} else {
					if(i == labels.length-1) {
						label += labels[i] + "']";
					} else {
						label += labels[i] + "','";
					}
				}
			}
			
			for(int i = 0; i <= labels.length-1; i++) {
				if(i == 0) {
					posTrainingComment += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + ",";
					negTrainingComment += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + ",";
					posTrainingPost += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + ",";
					negTrainingPost += "[-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + ",";
					totalTraining += "[-" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
					
					posFacilityComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + ",";
					negFacilityComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + ",";
					posFacilityPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + ",";
					negFacilityPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + ",";
					totalFacility += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
				} else {
					if(i == labels.length-1) {
						posTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + "]";
						negTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + "]";
						posTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + "]";
						negTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + "]";
						totalTraining += "-" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + "]";
						
						posFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + "]";
						negFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + "]";
						posFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + "]";
						negFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + "]";
						totalFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + "]";
					} else {
						posTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + ",";
						negTrainingComment += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + ",";
						posTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + ",";
						negTrainingPost += "-" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + ",";
						totalTraining += "-" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
						
						posFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + ",";
						negFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + ",";
						posFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + ",";
						negFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + ",";
						totalFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
					}
				}
			}
		}

//		RequestDispatcher req = request.getRequestDispatcher("statistic.jsp");
//		req.include(request, response);
		
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html>"+
				"<html>"+
				"<head>"+
				  "<meta charset=\"UTF-8\">"+
				  "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
				  
				  "<title>Thống kê phân tích</title>"+
				  
				  "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">"+
				  "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>"+
				  "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>"+
				  
				  "<script src=\"https://code.highcharts.com/highcharts.js\"></script>"+
				  "<script src=\"https://code.highcharts.com/modules/series-label.js\"></script>"+
				  "<script src=\"https://code.highcharts.com/modules/exporting.js\"></script>"+
				  "<script src=\"https://code.highcharts.com/modules/export-data.js\"></script>"+
				  "<script src=\"https://code.highcharts.com/modules/accessibility.js\"></script>"+
				"</head>"+
				"<body>"+
				  "<div class=\"container\">"+
				    "<h1>MỨC ĐỘ QUAN TÂM ĐỐI VỚI CÁC CHIẾN DỊCH TUYỂN SINH</h1>"+
				  
				    "<div class=\"statistics\">"+
				      "<div class=\"control\">"+
				        "<form method=\"post\" action=\"statistic\">"+         
				          "<span class=\"text-primary\">Hiển thị: </span>"+
				          "<select class=\"filter form-control\" name=\"filter\">"+
				              "<option value=\"month\" title=\"Hiển thị kết quả trong vòng 6 tháng gần nhất\">Tháng</option>"+
				              "<option value=\"day\" title=\"Hiển thị kết quả trong vòng 7 ngày gần nhất\">Ngày</option>"+
				          "</select>"+
				          
				          "<input type=\"submit\" class=\"btn btn-primary\" value=\"Lọc\">"+
				        "</form>"+
				      "</div>"+
				    "</div>"+

				    "<div class=\"tabcontent\">"+
				      "<figure class=\"highcharts-figure\">"+
				        "<div id=\"chart\"></div>"+
				      "</figure>"+

				      "<script type=\"text/javascript\">"+
				        "Highcharts.chart('chart', {"+
				          "chart: {"+
				              "type: 'bar'"+
				          "},"+
				          "title: {"+
				              "text: 'Biểu đồ thống kê cảm xúc'"+
				          "},"+
				          "subtitle: {"+
				              "text: 'Biểu đồ thống kê số lượng Bài đăng/Bình luận Tích cực và Tiêu cực dựa trên các khía cạnh Đào tạo và Cơ sở vật chất'"+
				          "},"+
				          "xAxis: [{"+
				             " title: {"+
				                  "text: 'ĐÀO TẠO',"+
				              "},"+
				              "categories: "+ label +","+
				              "reversed: false,"+
				              "labels: {"+
				                  "step: 1"+
				              "},"+
				              "accessibility: {"+
				                  "description: 'Age (male)'"+
				              "}"+
				          "}, {"+
				             " title: {"+
				                  "text: 'CƠ SỞ VẬT CHẤT',"+
				              "},"+
				              "opposite: true,"+
				              "reversed: false,"+
				              "categories: "+ label +","+
				              "linkedTo: 0,"+
				              "labels: {"+
				                  "step: 1"+
				              "},"+
				              "accessibility: {"+
				                  "description: 'Age (female)'"+
				              "}"+
				          "}],"+
				          "yAxis: {"+
				              "title: {"+
				                "text: null,"+
				              "},"+
				              "labels: {"+
				                  "formatter: function () {"+
				                      "return Math.abs(this.value);"+
				                  "}"+
				              "},"+
				              "accessibility: {"+
				                  "description: 'Percentage population',"+
				                  "rangeDescription: 'Range: 0 to 5%'"+
				              "}"+
				          "},"+
				          "plotOptions: {"+
				              "series: {"+
				                  "stacking: 'normal'"+
				              "}"+
				          "},"+
				         " tooltip: {"+
				              "formatter: function () {"+
				                  "return '\"<b>\"+' + this.point.category + '\"</b>\"+\"<br/>\"+'+ this.series.name + ': '   +"+
				                      "Highcharts.numberFormat(Math.abs(this.point.y), 1);"+
				              "}"+
				          "},"+
				          "series: [{"+
				              "type: 'column',"+
				              "name: 'Bình luận Tích cực',"+
				              "data: "+ posFacilityComment +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'column',"+
				              "name: 'Bình luận Tiêu cực',"+
				              "data: "+ negFacilityComment +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'column',"+
				              "name: 'Bài đăng Tích cực',"+
				              "data: "+ posFacilityPost +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'column',"+
				              "name: 'Bài đăng Tiêu cực',"+
				              "data: "+ negFacilityPost +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'spline',"+
				              "name: 'Tổng đề cập',"+
				              "data: "+ totalFacility +","+
				              "stack: 1,"+
				              "marker: {"+
				                  "lineWidth: 2,"+
				                  "lineColor: Highcharts.getOptions().colors[3],"+
				                  "fillColor: 'white'"+
				              "}"+
				            "}, {"+
				              "type: 'column',"+
				              "name: 'Bình luận Tích cực',"+
				              "data: "+ posTrainingComment +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'column',"+
				              "name: 'Bình luận Tiêu cực',"+
				              "data: "+ negTrainingComment +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'column',"+
				              "name: 'Bài đăng Tích cực',"+
				              "data: "+ posTrainingPost +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'column',"+
				              "name: 'Bài đăng Tiêu cực',"+
				              "data: "+ negTrainingPost +","+
				              "stack: 1"+
				            "}, {"+
				              "type: 'spline',"+
				              "name: 'Tổng đề cập',"+
				              "data: "+ totalTraining +","+
				              "stack: 2,"+
				              "marker: {"+
				                  "lineWidth: 2,"+
				                  "lineColor: Highcharts.getOptions().colors[3],"+
				                  "fillColor: 'white'"+
				              "}"+
				            "}"+
				          "]"+
				      "});"+
				      "</script>"+
				    "</div>"+
				    
				    "<div class=\"demo-link\" style=\"font-size: 18px; margin-top: 16px;\">"+
				      "<a href=\"sample.jsp\">Demo phân tích cảm xúc\"</a>"+
				    "</div>"+
				  "</div>"+

				  "<style>"+
				    ".container {"+
				      "width: 1200px;"+
				    "}"+
				    "h1 {"+
				      "text-align: center;"+
					"}"+

				    ".statistics {"+
				    	"margin-top: 8px;"+
				    	"margin-bottom: 8px;"+
					"}"+
				    
				    ".demo-link {"+
				      "text-align: right;"+
				     " cursor: pointer;"+
					"}"+
				    ".tabcontent {"+
				        "padding: 6px 12px;"+
					"}"+
				    ".chart {"+
					  "margin-left: auto;"+
				      "margin-right: auto;"+
					"}"+
				    "select {"+
				      "margin-left: 4px;"+
				      "margin-bottom: 8px;"+
				      "font-size: 16px;"+
				      "height: 30px;"+
				      "border-radius: 4px;"+
					"}"+
				    ".filter {"+
				      "width: 100px;"+
				      "display: inline;"+
					"}"+
				    ".control {"+
				      "text-align: right;"+
					"}"+
				    
				    "h3 {"+
				      "text-align: center;"+
				      "color: blue;"+
					"}"+
				  "</style>"+
				"</body>"+
				"</html>");
	}
	
	private int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	
	private int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	
	private String[] createMonthLabels(int current) {
		String[] labels = null;
		switch(current) {
		case 11:
			labels = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
			break;
		case 0:
			labels = new String[]{"Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan"};
			break;
		case 1:
			labels = new String[]{"Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb"};
			break;
		case 2:
			labels = new String[]{"Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar"};
			break;
		case 3:
			labels = new String[]{"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr"};
			break;
		case 4:
			labels = new String[]{"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May"};
			break;
		case 5:
			labels = new String[]{"Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun"};
			break;
		case 6:
			labels = new String[]{"Aug", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"};
			break;
		case 7:
			labels = new String[]{"Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"};
			break;
		case 8:
			labels = new String[]{"Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep"};
			break;
		case 9:
			labels = new String[]{"Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct"};
			break;
		case 10:
			labels = new String[]{"Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov"};
			break;
		}
		
		return labels;
	}
	
	private String[] createDayLabels(int current) {
		String[] labels = null;
		switch(current) {
		case Calendar.MONDAY:
			labels = new String[]{"Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon"};
			break;
		case Calendar.TUESDAY:
			labels = new String[]{"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
			break;
		case Calendar.WEDNESDAY:
			labels = new String[]{"Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed"};
			break;
		case Calendar.THURSDAY:
			labels = new String[]{"Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu"};
			break;
		case Calendar.FRIDAY:
			labels = new String[]{"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
			break;
		case Calendar.SATURDAY:
			labels = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
			break;
		case Calendar.SUNDAY:
			labels = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
			break;
		}
		
		return labels;
	}
	
	private int getDataFromList(List<Statistic> list, String time, String type, String status) {
		for(Statistic stat:list) {
			if(stat.getType().contentEquals(time)) {
				if(stat.getTypeDetail().contentEquals(time)) {
					if(stat.getTypeSource().contentEquals(type)) {
						switch(status) {
						case "negTraining":
							return Integer.parseInt(stat.getNegTraining());
						case "posTraining":
							return Integer.parseInt(stat.getPosTraining());
						case "negFacility":
							return Integer.parseInt(stat.getNegFacility());
						case "posFacility":
							return Integer.parseInt(stat.getPosFacility());
						}
					}
				}
			}
		}
		
		return 0;
	}
}
