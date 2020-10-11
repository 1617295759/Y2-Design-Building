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

@WebServlet(name="register",urlPatterns="/register")
public class RegisterServlet extends HttpServlet {
    UserDAO userdao = new UserDAOImpl();
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        this.doPost(req,res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        //1.设置编码
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/json;charset=utf-8");
        //2.获取请求参数  3.封装user对象
        User user = new User();
        user.setName(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setGender(req.getParameter("gender"));
        user.setAddress(req.getParameter("address"));
        user.setTelNo(req.getParameter("teleno"));
        user.setEmail(req.getParameter("email"));

        int flag = userdao.addUser(user);
        HttpSession session=req.getSession();
        session.setAttribute("user", user);
        JSONObject json = new JSONObject();  //创建Json对象
        //0-无账号，1-匹配，2-不匹配
        json.put("flag", flag);
        json.put("user", user);
        res.getWriter().write(json.toString());
    }
}
