package dao.impl;

import beans.Sorting;
import dao.SortingDAO;
import database.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class SortingDAOImpl implements SortingDAO {
    JdbcTemplate template;
    String user_schema = "iotbackstage2.sorting";
    public SortingDAOImpl() {
        template = DBUtils.getTemplate();
    }

    @Override
    public ArrayList<Sorting> getSorting(int orderID) {
        List<Sorting> list = new ArrayList<Sorting>();
        String sql = "select * from `iotbackstage2`.`sorting` " +
                "where `deleted`=0 and orderID=?";
        list = template.query(sql,new BeanPropertyRowMapper<Sorting>(Sorting.class),orderID);
        return (ArrayList<Sorting>) list;
    }
}
