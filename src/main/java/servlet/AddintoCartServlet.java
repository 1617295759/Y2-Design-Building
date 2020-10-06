package servlet;

import beans.User;
import dao.CartDAO;
import dao.impl.CartDAOImpl;

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
	CartDAO dao = new CartDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			int product = Integer.parseInt(req.getParameter("addintocartproduct"));
			dao.addCart(user.getUserId(),product,0);
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
