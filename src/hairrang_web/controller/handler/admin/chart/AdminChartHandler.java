package hairrang_web.controller.handler.admin.chart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import hairrang_web.controller.Command;
import hairrang_web.service.ChartService;

public class AdminChartHandler implements Command {
	private ChartService service = new ChartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "chart/chart_list.jsp";

		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");

			JSONArray qnaArray  = service.selectOnlyQnA();
			JSONArray joinGuest  = service.joinGuestByOneDay();
			JSONArray gender  = service.gender();
			
			request.setAttribute("qnaArray", qnaArray);
			request.setAttribute("joinGuest", joinGuest);
			request.setAttribute("gender", gender);
			return url;
		} else {
			System.out.println("POST");
			

			
			return url;
		}
	}

}
