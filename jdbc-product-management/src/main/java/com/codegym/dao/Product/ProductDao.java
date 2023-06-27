package com.codegym.dao.Product;

import com.codegym.dao.DBConnection;
import com.codegym.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao
{
    private Connection connection = DBConnection.getConnection();
    public ProductDao(){};

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM product"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                Product product = new Product(id,name,price,description);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM product where id = ?;"
            );
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                product = new Product(id,name,price,description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Product> products = new ArrayList<>();

        return product;
    }

    @Override
    public boolean create(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO product(name, price, description) values (?,?,?)"
            );
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE product SET name = ?, price = ?, description = ? WHERE id = ?"
            );
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setInt(4,id);
            return  preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM product WHERE id = ?"
            );
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean insertProductUsingProcedure(Product product) {
        try {
            CallableStatement callableStatement = connection.prepareCall("call insert_product(?, ?, ?)");
            callableStatement.setString(1, product.getName());
            callableStatement.setDouble(2, product.getPrice());
            callableStatement.setString(3, product.getDescription());
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;
    }
}
