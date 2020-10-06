package servlet.User;

import beans.User;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import net.sf.json.JSONObject;

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

	UserDAO dao = new UserDAOImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//1.设置编码
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/json;charset=utf-8");

		HttpSession session = req.getSession();
		User olduser = (User) session.getAttribute("user");
		User user = new User();
		user.setName(olduser.getName());
		user.setPassword(req.getParameter("password"));
		user.setGender(req.getParameter("sex"));
		user.setEmail(req.getParameter("email"));
		user.setTelNo(req.getParameter("phone"));
		user.setAddress(req.getParameter("address"));
		//调用dao更新数据
		boolean flag = dao.updateInfo(olduser.getName(),user);
		//在session中更新用户信息
		session.setAttribute("user", dao.getUser(olduser.getName()));

		JSONObject json = new JSONObject();  //创建Json对象
		json.put("flag", flag);
		res.getWriter().write(json.toString());
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		this.doPost(req,res);
	}
}
