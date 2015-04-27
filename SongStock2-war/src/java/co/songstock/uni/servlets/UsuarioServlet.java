/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.servlets;

import co.songstock.uni.dao.UsuarioDao;
import co.songstock.uni.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
    
    //private static final Logger logger = LoggerFactory.getLogger(UsuarioServlet.class);
    
    @EJB
    private UsuarioDao usuarioDao;
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
        String action = request.getParameter("action");
        if (action.equals("registrar")){
            registrarUsuario(request,response);
        }else{
            //logger.error("Sin accion - proveedor");
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
    
    /**
     * 
     * @param request
     * @param response 
     */
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
      
            PrintWriter out = response.getWriter();
            
            Usuario usuario=new Usuario();
            
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String correo=request.getParameter("correo");
            String contraseña=request.getParameter("contrasena");

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(correo);
            usuario.setContrasena(contraseña);
            usuario.setCantidadcompras(0);

            usuarioDao.crear(usuario);
            
            out.print("Usuario registrado exitosamente");
            response.sendRedirect(request.getContextPath()+"/pages/usuario/loginusuario.jsp");
            
       
        
    }

}
