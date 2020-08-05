package servlet;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import vo.User;

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
		user.setUsername(olduser.getUsername());
		if(req.getParameter("password")!=null) {
			user.setPassword(req.getParameter("password"));
		}else {
			user.setPassword(olduser.getPassword());
		}
		
		if(req.getParameter("sex")!=null) {
			user.setSex(req.getParameter("sex"));
		}else {
			user.setSex(olduser.getSex());
		}
		
		if(req.getParameter("age")!="") {
			user.setAge(Integer.parseInt(req.getParameter("age")));
		}else {
			user.setAge(olduser.getAge());
		}
		
		if(req.getParameter("email")!="") {
			user.setEmail(req.getParameter("email"));
		}else {
			user.setEmail(olduser.getEmail());
		}
		
		if(req.getParameter("phone")!="") {
			user.setPhone(req.getParameter("phone"));
		}else {
			user.setPhone(olduser.getPhone());
		}
		
		UserDAO dao = new UserDAOImpl();
		dao.updateInfo(olduser.getUsername(),user);
		session.setAttribute("user", user);
		req.getRequestDispatcher("./userinfo.jsp").forward(req, resp);
	}

}
