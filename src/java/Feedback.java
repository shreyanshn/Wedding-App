/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell-pc
 */
@WebServlet(urlPatterns = {"/Feedback"})
public class Feedback extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Feedback</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Feedback at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        response.setContentType("text/html");

        try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

        System.out.println("MySQL JDBC Driver Registered!");
	Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root", "12345678");
            if(conn!=null)
            {
                Statement st = conn.createStatement();
                st.executeUpdate("insert into shreyansh.t1 values ('" + request.getParameter("name") + "'," + request.getParameter("number") + ",'" + request.getParameter("message") + "','" + request.getParameter("mail") + "')");   
                conn.close();
            }
                        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PrintWriter out = response.getWriter();
            String title = "Using get to read form data";
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +"transitional//en\">\n";
            
            
            
            out.println(docType + "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<ul>\n" +
                    "  <li><b>Name</b>: "
                    + request.getParameter("name") + "\n" +
                    "  <li><b>Number</b>: "
                    + request.getParameter("number") + "\n" +
                    "  <li><b>Email ID</b>: "
                    + request.getParameter("mail") + "\n" +
                    "  <li><b>Message</b>: "
                    + request.getParameter("message") + "\n" +
                    "  <li><b>Number of people</b>: "
                    +request.getParameter("people") +
                    "</ul>\n" +
                    "<p style=\"text-align: center;\"><img src=\"https://cdn.jotfor.ms/img/check-icon.png\" alt=\"\" width=\"128\" height=\"128\" /></p>\n" +
                    "<div style=\"text-align: center;\">\n" +
                    "<h1 style=\"text-align: center;\">Thank You!</h1>\n" +
                    "<p style=\"text-align: center;\">Your submission has been received.</p>\n" +
                    "</div>"+
                    "</body></html>");
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
        doGet(request, response);
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
