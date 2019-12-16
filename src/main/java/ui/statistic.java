package ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.connectDatabase;

/**
 * Servlet implementation class statistic
 */
public class statistic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		connectDatabase connectDB = new connectDatabase();
		int count = connectDB.countNegTrainingPerDay();
		
		String filter = request.getParameter("filter");

		RequestDispatcher req = request.getRequestDispatcher("statistic.jsp");
		req.include(request, response);
		
		String labels;
		if(filter == "month") {
			labels = "labels: ['', '2017', '2018', '2019'],";
		} else if(filter == "day") {
			
		} else {
			
		}
		
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html>"+
				"<html>"+
				"<body>"+
					"<script type=\"text/javascript\">"+
						"Chart.defaults.global.elements.line.fill = false;"+
						"var barChartData = {"+
					  		labels+
					  		"datasets: [{"+
						    	"type: 'bar',"+
							    "label: 'Tiêu cực',"+
							    "yAxisID: \"y-axis-0\","+
							    "backgroundColor: \"rgba(217,83,79,0.75)\","+
							    "data: [1000, 2000, 4000, 5000]"+
					  		"}, {"+
							    "type: 'bar',"+
							    "label: 'Tích cực',"+
							    "yAxisID: \"y-axis-0\","+
							    "backgroundColor: \"rgba(92,184,92,0.75)\","+
							    "data: [500, 600, 700, 800]"+
						  	"}, {"+
							    "type: 'line',"+
							    "label: 'Số lượng đề cập',"+
							    "yAxisID: \"y-axis-0\","+
							    "backgroundColor: \"rgba(151,187,205,1)\","+
							    "data: [1500, 2600, 4700, 5800]"+
						  	"}]"+
						"};"+
			
						"var ctx = document.getElementById(\"chart\").getContext(\"2d\");"+
						"var ch = new Chart(ctx, {"+
					  		"type: 'bar',"+
					  		"data: barChartData,"+
					  		"options: {"+
					    		"title: {"+
						      		"display: true,"+
						      		"text: \"Biểu đồ thống kê cảm xúc\""+
						    	"},"+
						    	"tooltips: {"+
						      		"mode: 'label'"+
						    	"},"+
						    	"responsive: true,"+
						    	"scales: {"+
						      		"xAxes: [{"+
						        		"stacked: true"+
						      		"}],"+
						      		"yAxes: [{"+
						        		"stacked: true,"+
						        		"position: \"left\","+
						        		"id: \"y-axis-0\","+
						      		"}]"+
						    	"}"+
						  	"}"+
						"});"+
					"</script>"+
				"</body>"+
				"</html>");
	}

}
