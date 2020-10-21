package servlet.User;

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
		int userid = Integer.parseInt(req.getParameter("userid"));

//		User user = new User();
//		user.setName(req.getParameter("name"));
//		user.setPassword(req.getParameter("password"));
//		user.setGender(req.getParameter("sex"));
//		user.setEmail(req.getParameter("email"));
//		user.setTelNo(req.getParameter("phone"));
//		user.setAddress(req.getParameter("address"));

		boolean flag = false;//调用dao更新数据
		if(req.getParameter("name")!=null){
			flag = dao.updateInfo(userid,"name",req.getParameter("name"));
		}else if(req.getParameter("gender")!=null){
			flag = dao.updateInfo(userid,"gender",req.getParameter("gender"));
		}else if(req.getParameter("telNo")!=null){
			flag = dao.updateInfo(userid,"telNo",req.getParameter("telNo"));
		}else if(req.getParameter("address")!=null){
			flag = dao.updateInfo(userid,"address",req.getParameter("address"));
		}else if(req.getParameter("email")!=null){
			flag = dao.updateInfo(userid,"email",req.getParameter("email"));
		}else if(req.getParameter("password")!=null){
			flag = dao.updateInfo(userid,"password",req.getParameter("password"));
		}

		//在session中更新用户信息
		session.setAttribute("user", dao.getUser(userid));

		JSONObject json = new JSONObject();  //创建Json对象
		json.put("flag", flag);
		res.getWriter().write(json.toString());
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		this.doPost(req,res);
	}
}
