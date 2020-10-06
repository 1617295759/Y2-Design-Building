package servlet;

import beans.Order;
import beans.User;
import dao.OrderDAO;
import dao.impl.OrderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
@WebServlet("/deleteorder")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDAO dao =  new OrderDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		User user = (User) session.getAttribute("user");
		Order order = new Order();
		order.setUserId(user.getUserId());
		//order.setProduct(req.getParameter("deletedorder"));
		order.setOrderTime(Timestamp.valueOf(req.getParameter("deletedtime")));

		dao.deleteOrder(order);
		
		req.getRequestDispatcher("./login").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
