/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DbLogin;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jrockit.jfr.parser.ParseException;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jeroen
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    String Username = null;
    String Password = null;

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

        DbLogin dblogin = new DbLogin();
        // Initialize Strings
        String Register = null;
        String UserId = null;

        // Store all data from url to string
        try (PrintWriter out = response.getWriter()) {
            //GET THE USER_ID FROM URL
            Register = request.getParameter("register");
        }
        // Check if the user must register
        if (Register == "true") {

            int Result;
            Result = dblogin.Register(Username, Password);
            //send via JSON Result
        }
        // Check if the user must login
        if (Register == null) {

            int Result;
            //als er iets anders dan 0 uitkomt login geslaagt
            Result = dblogin.Login(Username, Password);
            // send userid naar android

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
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) { /*report an error*/ }

        try {
            JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
            Username = jsonObject.getString("user_name");
            Password = jsonObject.getString("password");
        } catch (JSONException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

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
