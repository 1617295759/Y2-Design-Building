package servlet;

import beans.Product;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		this.doPost(req,resp);
	}

	//screen the products and sort the products
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		String choice = req.getParameter("searchchoice");
		String searchthing = req.getParameter("searchthing");
		ProductDAO dao = new ProductDAOImpl();
		List<Product> products = new ArrayList<Product>();
		switch(choice) {
			case "keywords":
				//keywords
				products = dao.searchKey(searchthing);
				break;
			case "Category":
				//kind
				products = dao.searchKind(searchthing);
				break;
			case "Time":
				//ASC & DESC
				if(searchthing.equals("ASC"))
					products = dao.sortByAddedtimeAsc();
				else products = dao.sortByAddedtimeDesc();
				break;
			case "Price":
				// ASC && DESC
				if(searchthing.equals("ASC"))
					products = dao.sortByPriceAsc();
				else products = dao.sortByPriceDesc();
				break;
		}

		req.getSession().setAttribute("products",products);
		JSONObject json = new JSONObject();  //创建Json对象
		json.put("products", products);
		resp.getWriter().write(json.toString());
	}

}
