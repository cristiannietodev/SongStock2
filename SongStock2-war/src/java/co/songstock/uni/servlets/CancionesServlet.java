/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.songstock.uni.servlets;

import co.songstock.uni.dao.CancionDao;
import co.songstock.uni.entity.Cancion;
import co.songstock.uni.entity.Vinilo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
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
@WebServlet(name = "cancionesServlet", urlPatterns = {"/cancionesServlet"})
public class CancionesServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CancionesServlet.class);
    
    @EJB
    private CancionDao cancionDao;

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

        if (action.equals("listarcanciones")) {
            listarCanciones(request, response);
        } else {
            logger.error("Sin accion - proveedor");
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

    

    private void listarCanciones(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<Cancion> canciones = cancionDao.findAll();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de canciones</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylecss3.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de canciones</h1>");
            out.println("<table class=\"responstable\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Id canción</td>");
            out.println("<td>Nombre cancion</td>");
            out.println("<td>Precio</td>");
            out.println("<td>Duracion</td>");
            out.println("<td>Tamaño(MB)</td>");
            out.println("<td>Calidad(Kbps)</td>");
            out.println("<td>Comprar discos</td>");
            out.println("<td>Comprar vinilo</td>");
            out.println("</tr>");
            out.println("</thead>");
            for (Cancion c : canciones) {
                Double precio=c.getPrecio()==null?new Double(0):c.getPrecio();
                Double tamano=c.getTamano()==null?new Double(0):c.getTamano();
                Double calidad=c.getCalidad()==null?new Double(0):c.getCalidad();
                
                out.println("<tr>");
                out.println("<td>" + c.getIdcancion() + "</td>");
                out.println("<td>" + c.getNombre() + "</td>");
                out.println("<td>" + precio + "</td>");
                out.println("<td>" + c.getDuracionmin()+":" +c.getDuracionseg()+ "</td>");
                out.println("<td>" +tamano + "(MB)</td>");
                out.println("<td>" +calidad+ "(MB)</td>");
                out.println("<td><a href=\"PedidoServlet?action=pedidocancion&idcancion="+c.getIdcancion()+"\" title=\"Agregar cancion al carrito de compras\"><img alt=\"Agregar cancion al carrito de compras\" src=\"img/cart.png\"></a></td>");
                List<Vinilo> vinilos=c.getViniloList();
                if(!vinilos.isEmpty()){
                    out.println("<td>");
                    for(Vinilo v:vinilos){
                        out.println("<a href=\"PedidoServlet?action=pedidovinilo&idvinilo="+v.getIdvinilo()+"\" title=\"Agregar vinilo al carrito de compras\"><img alt=\"Agregar vinilo al carrito de compras\" src=\"img/cart.png\"></a>");
                        out.println("<br />");
                    }
                    out.println("</td>");
                }else{
                    out.println("<td></td>");
                }
//                if (vinilo != null){
//                    out.println("<td>Agregar pedido vinilo</td>");
//                }else{
//                    out.println("<td></td>");
//                }
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
