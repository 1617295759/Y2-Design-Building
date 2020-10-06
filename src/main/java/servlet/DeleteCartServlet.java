//package servlet;
//
//import beans.Cart;
//import beans.User;
//import dao.UserDAO;
//import dao.impl.UserDAOImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.Timestamp;
//
//@WebServlet("/deletecart")
//public class DeleteCartServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session=req.getSession();
//		User user = (User) session.getAttribute("user");
//		UserDAO dao =  new UserDAOImpl();
//
//		Cart cart = new Cart();
//		cart.setUser(user.getUsername());
//		cart.setProduct(req.getParameter("deletedproduct"));
//		cart.setTime(Timestamp.valueOf(req.getParameter("deletedtime")));
//
//		dao.deleteCart(cart);
//
//		req.getRequestDispatcher("./login").forward(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doPost(req, resp);
//	}
//
//}
