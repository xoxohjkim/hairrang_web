package hairrang_web.controller.handler.admin.guest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import hairrang_web.controller.Command;
import hairrang_web.dto.Guest;
import hairrang_web.dto.TimeTable;
import hairrang_web.service.GuestService;
import hairrang_web.utils.Paging;

public class AdminGuestListExampleHandler implements Command {
	private GuestService service = new GuestService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String url = "/admin/guest/guest_list.jsp";

		String nowPage = request.getParameter("nowPage");
		String cntPerPage = request.getParameter("cntPerPage");
		String del = null;
		String where = request.getParameter("opt");
		String query = request.getParameter("value");

		if (where != null && query != null) {
			if(!where.trim().equals("") && !query.trim().equals("") ) {
				request.setAttribute("opt", where);
				request.setAttribute("value", query);
			}
		} else {
			where = null;
			query = null;
		}
		
		if(nowPage == null) {
			nowPage = "1";
		}
		if(cntPerPage == null) {
			cntPerPage ="10";
		}

		
		int total = 0;
		Paging paging = new Paging();

		paging.setNowPage(Integer.parseInt(nowPage));
		paging.setCntPerPage(Integer.parseInt(cntPerPage));
		
		paging = new Paging(Integer.parseInt(nowPage), total, Integer.parseInt(cntPerPage));
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("cntPerPage", cntPerPage);
		request.setAttribute("paging", paging);
		
		ArrayList<Guest> list = service.selectGuestByCondition(paging, del, where, query);

		/*if(value != null) {
			value = value.trim();
		}
		
		if (value == null || value == "" || opt.equals("0") || opt == null) {
			// ?????? ????????? ?????? ?????? -> all list
			total = service.countGuest();
			paging = new Paging(Integer.parseInt(nowPage), total, Integer.parseInt(cntPerPage));
			list = service.pagingGuestByAll(paging);
		} else if (opt.equals("1")) { // ???????????? ??????
			total = service.countIdSearch(value);
			paging = new Paging(Integer.parseInt(nowPage), total, Integer.parseInt(cntPerPage));
			list = service.searchGuestById(paging, value);
		} else if (opt.equals("2")) {// ???????????? ??????
			total = service.countNameSearch(value);
			paging = new Paging(Integer.parseInt(nowPage), total, Integer.parseInt(cntPerPage));
			list = service.searchGuestByName(paging, value);
		} else if (opt.equals("3")) { // ????????? ??????
			total = service.countPhoneSearch(value);
			paging = new Paging(Integer.parseInt(nowPage), total, Integer.parseInt(cntPerPage));
			list = service.searchGuestByPhone(paging, value);
		}*/
		
		System.out.println("?????? ????????? =>" + nowPage);
		System.out.println("???????????? ????????? ??? =>" + cntPerPage);
		System.out.println("??? ????????? ?????? =>" + total);
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println(getClass().getSimpleName() + " >> GET");
			// ????????? ???????????? ?????? ????????? ???

			String id = request.getParameter("id");

			request.setAttribute("total", total);
			request.setAttribute("id", id);

			request.setAttribute("paging", paging);
			request.setAttribute("cnt", cntPerPage);
			request.setAttribute("list", list);

			return url;

		} else {
			System.out.println(getClass().getSimpleName() + " >> POST");
			// ajax??? JSON ???????????? ????????? ????????????.

			Gson gson = new Gson();
			String resultGuest = gson.toJson(list, new TypeToken<ArrayList<Guest>>() {}.getType());
			System.out.println(resultGuest);
			
			String resultPaging = gson.toJson(paging, new TypeToken<Paging>() {}.getType());
			System.out.println(resultPaging);
			
			String resultAll = "{\"guestList\":" + resultGuest + ", \"paging\":" + resultPaging + "}";
			System.out.println(resultAll);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("Application/json");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);

			PrintWriter pw = response.getWriter();
			pw.print(resultAll);
			pw.flush();

			return null;
		}
		
	}

}
