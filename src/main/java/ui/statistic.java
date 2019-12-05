package ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String option = request.getParameter("options");
		String filter = request.getParameter("filter");

		RequestDispatcher req = request.getRequestDispatcher("statistic.jsp");
		req.include(request, response);
		
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html>"+
				"<html>"+
				"<body>"+
					"<script type=\"text/javascript\">"+
							"var ctx = document.getElementById(\"columnchart\").getContext('2d');"+
							"var myChart = new Chart(ctx, {"+
							"type: 'bar',"+
							"data: {"+
								"labels: [\"Jan\", \"Feb\", \"Mar\", \"Apr\", \"May\", \"Jun\", \"Jul\", \"Aug\", \"Sep\", \"Otc\", \"Nov\", \"Dec\"],"+
								"datasets: [{"+
									"label: 'Đề cập',"+
									"data: [0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10],"+	
									"backgroundColor: ["+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)',"+
										"'rgba(75, 192, 192, 0.2)'"+
									"],"+
									"borderColor: ["+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)',"+
										"'rgba(75, 192, 192, 1)'"+
									"],"+
									"borderWidth: 1"+
								"}]"+
							"},"+
							"options: {"+
								"scales: {"+
									"yAxes: [{"+
										"ticks: {"+
											"beginAtZero: true"+
										"}"+
									"}]"+
								"}"+
							"}"+
						"});"+
					"</script>"+
				"</body>"+
				"</html>");
	}

}
