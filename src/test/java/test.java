import dao.*;
import dao.impl.*;
import database.DBUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class test {


    public static void main(String[] args) {
        JdbcTemplate template = DBUtils.getTemplate();
        CartDAO cartdao = new CartDAOImpl();
        OrderDAO orderdao = new OrderDAOImpl();
        SortingDAO sortdao = new SortingDAOImpl();
        UserDAO userdao = new UserDAOImpl();
        ProductDAO prodao = new ProductDAOImpl();

        String user_schema = "iotbackstage2.user";
        String cart_schema = "iotbackstage2.cart";
        String dbname = "iotbackstage2";

    for (int i = 300000006; i <300000051;i++ ){
        String sql0 = "SELECT `imgsrc` FROM `iotbackstage2`.`commodity` WHERE (`commodityID` = ?)";
        String sql = "UPDATE `iotbackstage2`.`commodity` SET `imgsrc` = ? WHERE (`commodityID` = ?)";
        String s = template.queryForObject(sql0,String.class, i);
        System.out.println(s);
        s = s.replace("D:/I","i");
        System.out.println(s);
        template.update(sql,s,i);
    }

    }
}
