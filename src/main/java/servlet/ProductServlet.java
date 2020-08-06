package servlet;

import beans.Product;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//get the products ArrayList and turn to product.jsp
		ProductDAO dao =  new ProductDAOImpl();
		req.getSession().setAttribute("products",dao.sortByAddedtimeDesc());
		req.getRequestDispatcher("./product.jsp").forward(req, resp);
	}

	//screen the products and sort the products
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String choice = req.getParameter("searchchoice");
		ProductDAO dao = new ProductDAOImpl();
		List<Product> products = null;
		switch(choice) {
			case "searchbykeywords":
				products = dao.searchKey(req.getParameter("keywords"));
				break;
			case "searchbykinds":
				products = dao.searchKind(req.getParameter("kind"));
				break;
			case "sortbytime":	
				if(req.getParameter("howtosort").equals("ASC"))
					products = dao.sortByAddedtimeAsc();
				else products = dao.sortByAddedtimeDesc();
				break;
			case "sortbyprice":
				if(req.getParameter("howtosortbyprice").equals("ASC"))
					products = dao.sortByPriceAsc();
				else products = dao.sortByPriceDesc();
				break;
		}

		req.getSession().setAttribute("products",products);
		req.getRequestDispatcher("./product.jsp").forward(req, resp);
	}

}
