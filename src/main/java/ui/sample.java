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
		String inputText = request.getParameter("input");
		System.out.println("input servlet: " + inputText);
		
		RequestDispatcher req = request.getRequestDispatcher("sample.jsp");
		req.include(request, response);
			
		String temp = null;
		
//		temp = standardizer.standardizeData(this.spark, inputText);
//		
//		temp = wordSegmentation.wordSegmentation(this.spark, temp);
//		
//		try {
//			  temp = removeStopWords.correctString(this.spark, temp); 
//		} catch (IOException e) { 
//			e.printStackTrace();
//		}
		
		model = new sentimentAnalyser();
		double[] result = model.testSample(inputText);
		
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html>"+
				"<html>"+
				"<body>"+
					"<br>"+
					"<div>"+
						"<span>Aspect</span>"+
						"<br>"+
						"<span>Training:</span>"+
						"<span>"+
							result[0]+
						"</span>"+
						"<br>"+
						"<span>Facility:</span>"+
						"<span>"+
							result[1]+
						"</span>"+
					"</div>"+
					"<br>"+
					"<div>"+
						"<span>Sentiment</span>"+
						"<br>"+
						"<span>Positive:</span>"+
						"<span>"+
							result[2]+
						"</span>"+
						"<br>"+
						"<span>Negative:</span>"+
						"<span>"+
							result[3]+
						"</span>"+
					"</div>"+
					"<div>"+
						"<a href=\"sample.jsp\">Reset</a>"+
					"</div>"+
				"</body>"+
				"</html>");
	}

}
