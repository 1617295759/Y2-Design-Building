package servlet;


import dao.UserDAO;
import dao.impl.UserDAOImpl;
import vo.Cart;
import vo.Orders;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
		HttpSession session=req.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			req.getRequestDispatcher("./login.jsp").forward(req, res);
		}else {
			UserDAO dao = new UserDAOImpl();
			ArrayList<Cart> carts = dao.sortCartBytime(user.getUsername());
			ArrayList<Orders> orders = dao.sortOrderByTime(user.getUsername());
			session.setAttribute("carts", carts);
			session.setAttribute("orders", orders);
			req.getRequestDispatcher("./userinfo.jsp").forward(req, res);
		}
	 }
	
	 public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
		 User user = new User();
		 user.setUsername(req.getParameter("username"));
		 user.setPassword(req.getParameter("password"));
		 
		 UserDAO dao = new UserDAOImpl();   
	     int flag = 0;
	     flag = dao.queryByUsername(user);
		 if(flag == 1){   
			 HttpSession session=req.getSession();
			 ArrayList<Cart> carts = dao.sortCartBytime(user.getUsername());
			 ArrayList<Orders> orders = dao.sortOrderByTime(user.getUsername());

			 session.setAttribute("carts", carts);
			 session.setAttribute("orders", orders);
			 session.setAttribute("user", dao.getUser(user.getUsername()));
	         req.getRequestDispatcher("./userinfo.jsp").forward(req, res);
	         } else {   
	         res.sendRedirect("./error.jsp");
	        }
	 }
}
	 