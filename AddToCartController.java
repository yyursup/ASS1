/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cart.CartDAO;
import Cart.CartDTO;
import Product.ProductDAO;
import Product.ProductDTO;
import User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("login_user");
            String his_url = (String)session.getAttribute("his_url");
            if (user == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                
                String pid = request.getParameter("pid");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                ProductDAO ProductDAO = new ProductDAO();
                ProductDTO p = ProductDAO.find_by_id(pid);
                
                if (quantity > p.getQuantity()) {
                    request.setAttribute("ERROR_QUANTITY", "Not enough glasess");
                    request.getRequestDispatcher("MainController?id=" + pid + "&action=Detail").forward(request, response);
                } else {
                    double price = quantity * (p.getPrice() * (1-p.getDiscount()));
                    CartDAO dao = new CartDAO();
                    CartDTO check = dao.find_by_ID(user.getUserId());
                    if (check == null) {
                        boolean check_insert_cart = dao.insert_cart(user.getUserId());
                        check = dao.find_by_ID(user.getUserId());
                        if (check_insert_cart) {
                            CartDTO c = new CartDTO(check.getCid(), user.getUserId(), pid, quantity, price);
                            boolean check_insert_cart_details = dao.insert_cart_details(c);
                             System.out.println(check_insert_cart_details);
                            if (check_insert_cart_details) {
                                request.getRequestDispatcher(his_url).forward(request, response);
                            }
                        }
                    } else {
                        CartDTO item = dao.getItem(user.getUserId(), pid);
                        if (item != null) {
                            int new_quantity = quantity + item.getQuantity();
                            double new_price = new_quantity * p.getPrice() *(1-p.getDiscount());
                            boolean check_update = dao.UpdateItem(item.getCid(), new_price, pid, new_quantity);
                            System.out.println(check_update);
                            if (check_update) {
                                request.getRequestDispatcher(his_url).forward(request, response);
                            }
                        } else {
                            CartDTO c = new CartDTO(check.getCid(), user.getUserId(), pid, quantity, price);
                            boolean check_insert_cart_details = dao.insert_cart_details(c);
                            System.out.println(check_insert_cart_details);
                            if (check_insert_cart_details) {
                                request.getRequestDispatcher(his_url).forward(request, response);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
