package servlet;

import beans.User;
import dao.UserDAO;
import dao.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/modifyuserinfo")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User olduser = (User) session.getAttribute("user");
		
		User user = new User();
		user.setName(olduser.getName());

		user.setPassword(req.getParameter("password"));
		user.setGender(req.getParameter("sex"));
		user.setEmail(req.getParameter("email"));
		user.setTelNo(req.getParameter("phone"));
		user.setAddress(req.getParameter("address"));
		UserDAO dao = new UserDAOImpl();
		dao.updateInfo(olduser.getName(),user);
		session.setAttribute("user", dao.getUser(olduser.getName()));
		req.getRequestDispatcher("./userinfo.jsp").forward(req, resp);
	}

}
