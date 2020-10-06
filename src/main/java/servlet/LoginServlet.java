package servlet;


import beans.Cart;
import beans.Order;
import beans.User;
import dao.CartDAO;
import dao.OrderDAO;
import dao.UserDAO;
import dao.impl.CartDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.UserDAOImpl;

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
	CartDAO cartdao = new CartDAOImpl();
	OrderDAO orderdao = new OrderDAOImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
		HttpSession session=req.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			req.getRequestDispatcher("./login.jsp").forward(req, res);
		}else {

			ArrayList<Cart> carts = cartdao.sortCartBytime(user.getUserId());
			ArrayList<Order> orders = orderdao.sortOrderByTime(user.getUserId());
			session.setAttribute("carts", carts);
			session.setAttribute("orders", orders);
			req.getRequestDispatcher("./userinfo.jsp").forward(req, res);
		}
	 }
	
	 public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{

		//1.设置编码
		 req.setCharacterEncoding("utf-8");
		 res.setContentType("text/json;charset=utf-8");
		 //2.获取请求参数
		 String userID = req.getParameter("userID");
		 String phone = req.getParameter("phone");

		 User user = new User();
		 user.setName(req.getParameter("username"));
		 user.setPassword(req.getParameter("password"));
		 
		 UserDAO dao = new UserDAOImpl();   
	     int flag = 0;
	     flag = dao.queryByUsername(user);
		 if(flag == 1){   
			 HttpSession session=req.getSession();
			 ArrayList<Cart> carts = cartdao.sortCartBytime(user.getUserId());
			 ArrayList<Order> orders = orderdao.sortOrderByTime(user.getUserId());

			 session.setAttribute("carts", carts);
			 session.setAttribute("orders", orders);
			 session.setAttribute("user", dao.getUser(user.getName()));
	         req.getRequestDispatcher("./userinfo.jsp").forward(req, res);
	         } else {   
	         res.sendRedirect("./error.jsp");
	        }
	 }
}
	 