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

        //System.out.println(prodao.sortByAddedtimeAsc());
        //System.out.println( new Timestamp(System.currentTimeMillis()));
//        JSONObject json = new JSONObject();  //创建Json对象
//        int commodityID = 2;
//        json.put("products", prodao.sortByAddedtimeAsc());
//        System.out.println(json.toString());

        String user_schema = "iotbackstage2.user";
//        String sql = "INSERT INTO "+ user_schema +
//                " (`name`, `gender`, `telNo`, `password`, `address`, `email`, `deleted`) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?)";
//        int i = template.update(sql,"jassika","man","123",
//                "123","beijing","@",0);

        String sql = "select userID from " + user_schema + " where name=?";
        int count = template.queryForObject(sql, Integer.class,"jck");
        System.out.println("数据总数：" + count);
    }
}
