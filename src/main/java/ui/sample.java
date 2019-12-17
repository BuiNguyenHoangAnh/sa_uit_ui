package ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.sentimentAnalyser;
import segmentation.segmentationBUS;
import standardize.standardizeBUS;
import stopword.removeStopWordsBUS;
import util.sparkConfigure;

/**
 * Servlet implementation class sample
 */
public class sample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private sparkConfigure spark = new sparkConfigure();
	
	private standardizeBUS standardizer = new standardizeBUS();
	private segmentationBUS wordSegmentation = new segmentationBUS();
	private removeStopWordsBUS removeStopWords = new removeStopWordsBUS();
	private sentimentAnalyser model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sample() {
        super();
//         TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		String inputText = request.getParameter("input");
		System.out.println("input servlet: " + inputText);
		
//		RequestDispatcher req = request.getRequestDispatcher("sample.jsp");
//		req.include(request, response);
			
		String temp = null;
		
		temp = standardizer.standardizeData(this.spark, inputText);
		
		temp = wordSegmentation.wordSegmentation(this.spark, temp);
		
		try {
			  temp = removeStopWords.correctString(this.spark, temp); 
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		model = new sentimentAnalyser();
		double[] result = model.testSample(temp);
		
		out.println(
				"<!DOCTYPE html>"+
				"<html>"+
				"<head>"+
					"<title>Demo</title>"+
				
					"<style type=\"text/css\">"+
						".content {"+
							"width: 40%;"+
							"margin-left: auto;"+
							"margin-right: auto;"+
						"}"+
						".chart {"+
							"margin-left: auto;"+
							"margin-right: auto;"+
						"}"+
					"</style>"+
						
					"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">"+
					"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>"+
					"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>"+
					
					"<script src=\"https://cdn.jsdelivr.net/npm/chart.js@2.8.0\"></script>"+
				"</head>"+
				"<body>"+
				"<div class=\"content\" style=\"font-size: 16px;\">"+
					"<a href=\"sample.jsp\"><-- Nhập lại</a>"+
				"</div>"+
					"<h1 class=\"content\" style=\"text-align: center;\">KẾT QUẢ PHÂN TÍCH</h1>"+
					"<div class=\"content\">"+
						"<h4>Khía cạnh</h4>"+
						"<canvas  id=\"aspect\" class=\"chart\"></canvas >"+
						"<script type=\"text/javascript\">"+
							"var ctx = document.getElementById(\"aspect\").getContext('2d');"+
							"var myChart = new Chart(ctx, {"+
								"type: 'pie',"+
								"data: {"+
									"labels: [\"Đào tạo\", \"Cơ sở vật chất\"],"+
									"datasets: [{"+
										"label: '%',"+
										"data: [" + result[0] + "," + result[1] + "],"+
										"backgroundColor: ["+
											"'rgba(75, 192, 192, 0.2)',"+
											"'rgba(220, 20, 60, 0.2)'"+
										"],"+
										"borderColor: ["+
											"'rgba(75, 192, 192, 1)',"+
											"'rgba(220, 20, 60, 1)'"+
										"],"+
										"borderWidth: 1"+
									"}]"+
								"},"+
							"});"+
						"</script>"+
					"</div>"+
						
					"<div class=\"content\">"+
						"<h4>Cảm xúc</h4>"+
						"<canvas  id=\"sentiment\" class=\"chart\"></canvas >"+
						"<script type=\"text/javascript\">"+
						"var ctx = document.getElementById(\"sentiment\").getContext('2d');"+
						"var myChart = new Chart(ctx, {"+
							"type: 'pie',"+
							"data: {"+
								"labels: [\"Tích cực\", \"Tiêu cực\"],"+
								"datasets: [{"+
									"label: '%',"+
									"data: [" + result[2] + "," + result[3] + "],"+
									"backgroundColor: ["+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(220, 20, 60, 0.2)'"+
									"],"+
									"borderColor: ["+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(220, 20, 60, 1)'"+
									"],"+
									"borderWidth: 1"+
								"}]"+
							"},"+
						"});"+
					"</script>"+
					"</div>"+
				"</body>"+
				"</html>");
	}

}
