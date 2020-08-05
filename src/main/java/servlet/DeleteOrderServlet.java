package servlet;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import vo.Orders;
import vo.User;

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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		User user = (User) session.getAttribute("user");
		UserDAO dao =  new UserDAOImpl();
		
		Orders order = new Orders();
		order.setUser(user.getUsername());
		order.setProduct(req.getParameter("deletedorder"));
		order.setOrderedtime(Timestamp.valueOf(req.getParameter("deletedtime")));

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
