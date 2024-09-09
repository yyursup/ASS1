/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGINCONTROLLER1 = "SIGN IN";
    private static final String LOGINCONTROLLER = "LoginController";
    private static final String ERROR = "error.jsp";
    private static final String SIGNUP = "Sign Up";
    private static final String SIGNUP2 = "signUpController";
    private static final String filtercategory = "filtercategory";
    private static final String filtercategory_CONTROLLER = "filtercategoryController";
    private static final String filter = "filter";
    private static final String filter_CONTROLLER = "filterController";
    private static final String filter_by_price = "Filter By Price";
    private static final String filter_by_price_CONTROLLER = "FilterByPriceController";
    private static final String SHOP = "Shop";
    private static final String SHOP_CONTROLLER = "ShopController";
    private static final String HOME = "Home";
    private static final String clothe_Controller = "clotheController";
    private static final String Filter_By_Price_With_Caterogies = "Filter By Price With Caterogies";
    private static final String Filter_By_Price_With_Caterogies_CONTROLLER = "FilterByPriceWithCaterogies";
    private static final String DETAILS = "Detail";
    private static final String DETAILS_CONTROLLER = "DetailController";
    private static final String View_Cart = "View Cart";
    private static final String View_Cart_CONTROLLER = "ViewCartController";
    private static final String Delete_Cart = "Delete Cart";
    private static final String Delete_Cart_CONTROLLER = "DeleteCartController";
    private static final String Add_to_cart = "Add to cart";
    private static final String Add_to_cart_CONTROLLER = "AddToCartController";

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
        String url = ERROR;
        String action = request.getParameter("action");
        try {
            if (LOGINCONTROLLER1.equals(action)) {
                url = LOGINCONTROLLER;
            } else if (SIGNUP.equals(action)) {
                url = SIGNUP2;

            } else if (filtercategory.equals(action)) {
                url = filtercategory_CONTROLLER;
            } else if (filter.equals(action)) {
                url = filter_CONTROLLER;
            } else if (filter_by_price.equals(action)) {
                url = filter_by_price_CONTROLLER;
            } else if (SHOP.equals(action)) {
                url = SHOP_CONTROLLER;
            } else if (Filter_By_Price_With_Caterogies.equals(action)) {
                url = Filter_By_Price_With_Caterogies_CONTROLLER;
            } else if (HOME.equals(action)) {
                url = clothe_Controller;
            } else if (DETAILS.equals(action)) {
                url = DETAILS_CONTROLLER;
            } else if (Add_to_cart.equals(action)) {
                url = Add_to_cart_CONTROLLER;
            } else if (View_Cart.equals(action)) {
                url = View_Cart_CONTROLLER;
            } else if (Delete_Cart.equals(action)) {
                url = Delete_Cart_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
