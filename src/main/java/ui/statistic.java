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
		doPost(request, response);
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
		String posTraining = "";
		String negTraining = "";
		String posTrainingPost= "";
		String negTrainingPost= "";
		String posFacilityPost= "";
		String negFacilityPost= "";
		String posFacility = "";
		String negFacility = "";
		String totalTraining= "";
		String totalFacility= "";
		
		connectDatabase connectDB = new connectDatabase();
		List<Statistic> list = connectDB.getDataFromDB();
		
		if (filter.contentEquals("month")) {
			labels = this.createMonthLabels(getCurrentMonth());
			for(int i = 0; i <= labels.length-1; i++) {
				if(i == 0) {
					label += "['" + labels[i] + "','";
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
					posTrainingComment += "[" + this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + ",";
					negTrainingComment += "[" + this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + ",";
					posTrainingPost += "[" + this.getDataFromList(list, labels[i], "POST", this.posTraining) + ",";
					negTrainingPost += "[" + this.getDataFromList(list, labels[i], "POST", this.negTraining) + ",";
					posTraining += "[" + (this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i], "POST", this.posTraining)) + ",";
					negTraining += "[" + (this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i], "POST", this.negTraining)) + ",";
					totalTraining += "[" + (this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i], "POST", this.posTraining) + this.getDataFromList(list, labels[i], "POST", this.negTraining)) + ",";
					
					posFacilityComment += "[" + this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + ",";
					negFacilityComment += "[" + this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + ",";
					posFacilityPost += "[" + this.getDataFromList(list, labels[i], "POST", this.posFacility) + ",";
					negFacilityPost += "[" + this.getDataFromList(list, labels[i], "POST", this.negFacility) + ",";
					posFacility += "[" + (this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i], "POST", this.posFacility)) + ",";
					negFacility += "[" + (this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i], "POST", this.negFacility)) + ",";
					totalFacility += "[" + (this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i], "POST", this.posFacility) + this.getDataFromList(list, labels[i], "POST", this.negFacility)) + ",";
				} else {
					if(i == labels.length-1) {
						posTrainingComment += this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + "]";
						negTrainingComment += this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + "]";
						posTrainingPost += this.getDataFromList(list, labels[i], "POST", this.posTraining) + "]";
						negTrainingPost += this.getDataFromList(list, labels[i], "POST", this.negTraining) + "]";
						posTraining += (this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i], "POST", this.posTraining)) + "]";
						negTraining += (this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i], "POST", this.negTraining)) + "]";
						totalTraining += (this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i], "POST", this.posTraining) + this.getDataFromList(list, labels[i], "POST", this.negTraining)) + "]";
						
						posFacilityComment += this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + "]";
						negFacilityComment += this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + "]";
						posFacilityPost += this.getDataFromList(list, labels[i], "POST", this.posFacility) + "]";
						negFacilityPost += this.getDataFromList(list, labels[i], "POST", this.negFacility) + "]";
						posFacility += (this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i], "POST", this.posFacility)) + "]";
						negFacility += (this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i], "POST", this.negFacility)) + "]";
						totalFacility += (this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i], "POST", this.posFacility) + this.getDataFromList(list, labels[i], "POST", this.negFacility)) + "]";
					} else {
						posTrainingComment += this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + ",";
						negTrainingComment += this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + ",";
						posTrainingPost += this.getDataFromList(list, labels[i], "POST", this.posTraining) + ",";
						negTrainingPost += this.getDataFromList(list, labels[i], "POST", this.negTraining) + ",";
						posTraining += (this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i], "POST", this.posTraining)) + ",";
						negTraining += (this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i], "POST", this.negTraining)) + ",";
						totalTraining += (this.getDataFromList(list, labels[i], "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i], "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i], "POST", this.posTraining) + this.getDataFromList(list, labels[i], "POST", this.negTraining)) + ",";
						
						posFacilityComment += this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + ",";
						negFacilityComment += this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + ",";
						posFacilityPost += this.getDataFromList(list, labels[i], "POST", this.posFacility) + ",";
						negFacilityPost += this.getDataFromList(list, labels[i], "POST", this.negFacility) + ",";
						posFacility += (this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i], "POST", this.posFacility)) + ",";
						negFacility += (this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i], "POST", this.negFacility)) + ",";
						totalFacility += (this.getDataFromList(list, labels[i], "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i], "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i], "POST", this.posFacility) + this.getDataFromList(list, labels[i], "POST", this.negFacility)) + ",";
					}
				}
			}
		} else {
			labels = this.createDayLabels(getCurrentDay());
			for(int i = 0; i <= labels.length-1; i++) {
				if(i == 0) {
					label += "['" + labels[i] + "','";
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
					posTrainingComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + ",";
					negTrainingComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + ",";
					posTrainingPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + ",";
					negTrainingPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + ",";
					posTraining += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining)) + ",";
					negTraining += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
					totalTraining += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
					
					posFacilityComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + ",";
					negFacilityComment += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + ",";
					posFacilityPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + ",";
					negFacilityPost += "[" + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + ",";
					posFacility += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility)) + ",";
					negFacility += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
					totalFacility += "[" + (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
				} else {
					if(i == labels.length-1) {
						posTrainingComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + "]";
						negTrainingComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + "]";
						posTrainingPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + "]";
						negTrainingPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + "]";
						posTraining += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining)) + "]";
						negTraining += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + "]";
						totalTraining += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + "]";
						
						posFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + "]";
						negFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + "]";
						posFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + "]";
						negFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + "]";
						posFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility)) + "]";
						negFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + "]";
						totalFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + "]";
					} else {
						posTrainingComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + ",";
						negTrainingComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + ",";
						posTrainingPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + ",";
						negTrainingPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining) + ",";
						posTraining += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining)) + ",";
						negTraining += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
						totalTraining += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posTraining) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negTraining)) + ",";
						
						posFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + ",";
						negFacilityComment += this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + ",";
						posFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + ",";
						negFacilityPost += this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility) + ",";
						posFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility)) + ",";
						negFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
						totalFacility += (this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "COMMENT", this.negFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.posFacility) + this.getDataFromList(list, labels[i].toUpperCase(), "POST", this.negFacility)) + ",";
					}
				}
			}
		}

//		RequestDispatcher req = request.getRequestDispatcher("statistic.jsp");
//		req.include(request, response);
		
		request.setAttribute("label", label);
		
		request.setAttribute("posTrainingComment", posTrainingComment);
		request.setAttribute("negTrainingComment", negTrainingComment);
		request.setAttribute("posTrainingPost", posTrainingPost);
		request.setAttribute("negTrainingPost", negTrainingPost);
		request.setAttribute("posTraining", posTraining);
		request.setAttribute("negTraining", negTraining);
		request.setAttribute("totalTraining", totalTraining);
		
		request.setAttribute("posFacilityComment", posFacilityComment);
		request.setAttribute("negFacilityComment", negFacilityComment);
		request.setAttribute("posFacilityPost", posFacilityPost);
		request.setAttribute("negFacilityPost", negFacilityPost);
		request.setAttribute("posFacility", posFacility);
		request.setAttribute("negFacility", negFacility);
		request.setAttribute("totalFacility", totalFacility);
		
		this.getServletContext().getRequestDispatcher("/statistic.jsp").forward(request, response);
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
		
		return 0;
	}
}
