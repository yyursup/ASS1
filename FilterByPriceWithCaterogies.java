/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Catagories.CategoriesDao;
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
@WebServlet(name = "FilterByPriceWithCaterogies", urlPatterns = {"/FilterByPriceWithCaterogies"})
public class FilterByPriceWithCaterogies extends HttpServlet {

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
            String min_raw = request.getParameter("min");
            String max_raw = request.getParameter("max");
            String name = request.getParameter("name");
            int index = Integer.parseInt(request.getParameter("index"));
            Double min = Double.parseDouble(min_raw);
            Double max = Double.parseDouble(max_raw);
            CategoriesDao CategoriesDAO = new CategoriesDao();
            int id = CategoriesDAO.find_by_Name(name);
            ProductDAO dao = new ProductDAO();
            int count = dao.Count_Product_By_Caid(id);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            }
            HttpSession session = request.getSession();
            String his_url = "MainController?min="+min+"&max="+max+"&name="+name+"&index="+index+"&action=Filter+By+Price+With+Caterogies";
            session.setAttribute("his_url", his_url);
            if (min > max) {
                List<ProductDTO> list_p = dao.Find_By_CategoriesID_Paging(id, index,6);
                request.setAttribute("MESSAGE", "Min must < max");
                request.setAttribute("endPage", endPage);
                request.setAttribute("min", min_raw);
                request.setAttribute("max", max_raw);
                request.setAttribute("name", name);
                request.setAttribute("list_p", list_p);
            } else {
                count = dao.Count_Product_By_Price_By_Caid(min, max, id);
                endPage = count / 6;
                if (count % 6 != 0) {
                    endPage++;
                }
                List<ProductDTO> list_p = dao.Filter_by_Price_CaID_paging(min, max, id, index,6);
                request.setAttribute("endPage", endPage);
                request.setAttribute("min", min_raw);
                request.setAttribute("max", max_raw);
                request.setAttribute("name", name);
                request.setAttribute("list_p", list_p);
            }

        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher("categories.jsp").forward(request, response);
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
