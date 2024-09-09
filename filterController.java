/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Catagories.CategoriesDao;
import Catagories.CategoriesDTO;
import Product.ProductDAO;
import Product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "filterController", urlPatterns = {"/filterController"})
public class filterController extends HttpServlet {

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
            int CategoriesID = Integer.parseInt(request.getParameter("CategoriesID"));
            CategoriesDao CategoriesDAO = new CategoriesDao();
            List<CategoriesDTO> list_C = CategoriesDAO.getAll();
            String name = CategoriesDAO.getName(CategoriesID);
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list_p = dao.Find_By_CategoriesID_Paging(CategoriesID,1,6);
            int count = dao.Count_Product_By_Caid(CategoriesID);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            }
            HttpSession session = request.getSession();
            String his_url = "MainController?CategoriesID="+CategoriesID+"&action=filter";
            session.setAttribute("his_url", his_url);
            request.setAttribute("endPage", endPage);
            request.setAttribute("name", name);
            request.setAttribute("list_C",list_C );
            request.setAttribute("list_p", list_p);
            request.getRequestDispatcher("categories.jsp").forward(request, response);
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
