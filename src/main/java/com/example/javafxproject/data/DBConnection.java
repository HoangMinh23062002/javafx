package com.example.javafxproject.data;

import com.example.javafxproject.data.models.ProductIcon;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection connection;

    public static final String URL = "jdbc:mysql://localhost/javafx";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public DBConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ProductIcon> getProducts(){
        ArrayList<ProductIcon> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try {
            ResultSet results = connection.prepareStatement(sql).executeQuery();
            while (results.next()){
                ProductIcon pro = new ProductIcon(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getString("image"),
                        results.getInt("price"),
                        results.getString("description")
                );
                list.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void insertProduct(ProductIcon pro){
        String sql = "INSERT INTO product (name, image, price, description) VALUE ('"+ pro.name+"','"+ pro.image+"','"+ pro.price+"','"+ pro.description+"')";
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(ProductIcon pro){
        String sql = "UPDATE product SET name = '"+ pro.name +"', image = '"+ pro.image+"', price = '"+ pro.price+"', description = '"+ pro.description+"' WHERE id = "+ pro.id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int id){
        String sql = "DELETE FROM product WHERE id = "+ id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
