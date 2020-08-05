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
@WebServlet("/addintocart")
public class AddintoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			String product = req.getParameter("addintocartproduct");
			UserDAO dao = new UserDAOImpl();
			dao.addCart(user.getUsername(),product);
			req.getRequestDispatcher("./login").forward(req, resp);
		}else {
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
		}
		//To do tell the customer it is added 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
