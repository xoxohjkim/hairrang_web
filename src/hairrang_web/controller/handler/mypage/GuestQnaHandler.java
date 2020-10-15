package hairrang_web.controller.handler.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hairrang_web.controller.Command;
import hairrang_web.dto.Guest;
import hairrang_web.dto.QnA;
import hairrang_web.service.QnaService;

public class GuestQnaHandler implements Command {
	private QnaService service = new QnaService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Guest loginUser = (Guest) session.getAttribute("loginUser");
		
		ArrayList<QnA> list = service.qnaListById(new Guest(loginUser.getGuestId()));
		System.out.println(list);
		request.setAttribute("list", list);
		return "mypage/guest_qna.jsp";
	}

}
