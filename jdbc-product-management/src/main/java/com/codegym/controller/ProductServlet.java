package com.codegym.controller;

import com.codegym.dao.Product.ProductDao;
import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private IProductService productService;

    public ProductServlet() {

        this.productService = new ProductService(new ProductDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action =" ";
        }
        switch (action) {
            case "create" ->{
                showCreateForm(req, resp);
            }
            case "delete" ->{
                showDeleteForm(req, resp);
            }
            case "edit" ->{
                showEditForm(req, resp);
            }
            case "view" ->{
                showProductDetail(req, resp);
            }
            default -> {
                showListProduct(req, resp);
            }
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/delete.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/edit.jsp");
        dispatcher.forward(req, resp);
    }

    private static void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void showProductDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product",product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/view.jsp");
        dispatcher.forward(req, resp);
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create" -> {
                createProduct(req, resp);
            }
            case "delete" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                productService.deleteById(id);
                resp.sendRedirect("/product");
            }
            case "edit" -> {
                editProduct(req, resp);
            }
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        Product product = new Product(name,price,description);
        boolean isUpdated = productService.updateById(id,product);
        String message;
        if (isUpdated){
            message = "Cập nhật thành công";
        } else {
            message = "Cập nhật không thành công, xảy ra lỗi";
        }
        req.setAttribute("message", message);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/edit.jsp");
        dispatcher.forward(req, resp);
        resp.sendRedirect("/products");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        Product product = new Product(name,price,description);
        productService.insertProductUsingProcedure(product);
        resp.sendRedirect("/products");
    }
}
