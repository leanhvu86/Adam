package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class Products {

    public Products() {

    }

    public void delete(String masp) {

    }

    public void insert(String maSP, String tenSP, float gia) {

    }

    public void update(String maSP, String tenSP, float gia) {

    }

    public List<Product> showProduct(String type) {//show product dang muốn show mới nhất chưa làm vì giữ code
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLBanHang";
            Connection con = DriverManager.getConnection(url, "sa", "123456789");
            String sql = "SELECT top 10 * FROM Product";
            if (!type.equals("")) {
                sql += " WHERE type LIKE '%" + type + "%'";
            }
            Statement st = con.createStatement();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String type1 = rs.getString("Type");
                String price = rs.getString("Price");
                String color = rs.getString("color");
                String quantity = rs.getString("quantity");
                String sold = rs.getString("sold");
                Product sp = new Product(code, name, price, type1, color, quantity, sold);
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> showProductListTop() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLBanHang";
            Connection con = DriverManager.getConnection(url, "sa", "123456789");
            String sql = "SELECT top 10 * FROM Product";

            sql += " order by sold desc";
            System.out.println(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String price = rs.getString("Price");
                Product sp = new Product(code, name, price);
                list.add(sp);
            }
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
