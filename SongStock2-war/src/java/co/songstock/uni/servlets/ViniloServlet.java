/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.songstock.uni.servlets;

import co.songstock.uni.entity.Cancion;
import co.songstock.uni.entity.Usuario;
import co.songstock.uni.entity.Vinilo;
import co.songstock.uni.dao.CancionDao;
import co.songstock.uni.dao.ViniloDao;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "ViniloServlet", urlPatterns = {"/ViniloServlet"})
public class ViniloServlet extends HttpServlet {

    //private static final Logger logger = LoggerFactory.getLogger(ViniloServlet.class);
    
    @EJB
    private CancionDao cancionDao;
    
    @EJB
    private ViniloDao viniloDao;
    
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
        if (action.equals("registrar")) {
            registrarVinilo(request, response);
        } else {
            if (action.equals("registrarcanciones")) {
                registrarCanciones(request, response);
            } else {
                if (action.equals("listarvinilos")) {
                    listarVinilos(request, response);
                }
                //logger.error("Sin accion - proveedor");
            }
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
    private void registrarVinilo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {

            String nombre = request.getParameter("nombre");
            String artista = request.getParameter("artista");
            String ano = request.getParameter("ano");
            String precio = request.getParameter("precio");
            String unidades = request.getParameter("unidades");
            String canciones = request.getParameter("canciones");

            Vinilo vinilo = new Vinilo();
            vinilo.setNombrevinilo(nombre);
            vinilo.setArtista(artista);
            vinilo.setAnosalida(Integer.parseInt(ano));
            vinilo.setPrecio(Double.parseDouble(precio));
            vinilo.setUnidades(Integer.parseInt(unidades));
            vinilo.setUsuario(usuario);

            viniloDao.crear(vinilo);

            Integer numcanciones = Integer.parseInt(canciones);

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Canciones vinilo</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylecss3.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<form id=\"registravinilo\" action=\"ViniloServlet\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"action\" value=\"registrarcanciones\" />");
            out.println("<input type=\"hidden\" name=\"idvinilo\" value=\"" + vinilo.getIdvinilo() + "\" />");
            out.println("<table class=\"responstable\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>#</td>");
            out.println("<td>Nombre</td>");
            out.println("<td>Duración (min)</td>");
            out.println("<td>Duración (seg)</td>");
            out.println("<tr>");
            out.println("</thead>");
            out.print("<tbody>");
            for (int i = 0; i < numcanciones; i++) {
                out.println("<tr>");
                out.println("<td>" + (i + 1) + "</td>");
                out.println("<td><input type=\"text\" name=\"cancion\" required=\"required\" /> </td>");
                out.println("<td><input type=\"number\" name=\"duracionmin\" required=\"required\" min=\"0\" max=\"60\"/> </td>");
                out.println("<td><input type=\"number\" name=\"duracionseg\" required=\"required\" min=\"0\" max=\"60\"/> </td>");
                out.println("</tr>");
            }
            out.print("</tbody>");
            out.println("</table>");
            out.println("<input type=\"submit\" value=\"Registrar canciones\" /> ");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        } else {
            response.sendRedirect(request.getContextPath()+"/pages/usuario/loginusuario.jsp");
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    private void registrarCanciones(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {

            PrintWriter out = response.getWriter();

            String[] canciones = request.getParameterValues("cancion");
            String[] duracionmin = request.getParameterValues("duracionmin");
            String[] duracionseg = request.getParameterValues("duracionseg");
            String idvin = request.getParameter("idvinilo");

            Integer idvinilo = Integer.parseInt(idvin);

            Vinilo vinilo = viniloDao.findById(idvinilo);

            for (int i = 0; i < canciones.length; ++i) {

                Cancion can = new Cancion();

                String cancion = canciones[i];
                Integer duramin = Integer.parseInt(duracionmin[i]);
                Integer duraseg = Integer.parseInt(duracionseg[i]);

                can.setNombre(cancion);
                can.setDuracionmin(duramin);
                can.setDuracionseg(duraseg);

//                List<Cancion> listCancion=cancionDao.findCancionesByName(cancion);
//                
//                for(Cancion c:listCancion){
//                    
//                }
                can.addVinilo(vinilo);

                //cancionDao.create(can);
                vinilo.addCancion(can);

            }

            viniloDao.actualizar(vinilo);
            
            //TODO: 

            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

//        } catch (DatoException e) {
//            logger.error("Error al insertar vinilo en la BD", e);
        } catch (IOException e) {
            //logger.error("Error proceso creacion vinilo", e);
        } catch (ServletException ex) {
            //logger.error("Error proceso respuesta vinilo", ex);
        } finally {
            out.close();
        }
    }

    private void listarVinilos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Vinilo> vinilos = viniloDao.findAll();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListarVinilos</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylecss3.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de vinilos</h1>");
            out.println("<table class=\"responstable\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Nombre vinilo</td>");
            out.println("<td>Artista</td>");
            out.println("<td>Año</td>");
            out.println("<td>Canciones</td>");
            out.println("<td>Precio</td>");
            out.println("<td>Cantidad inventario</td>");
            out.println("<td>Comprar vinilo</td>");
            out.println("</tr>");
            out.println("</thead>");
            for (Vinilo v : vinilos) {
                out.println("<tr>");
                out.println("<td>" + v.getNombrevinilo() + "</td>");
                out.println("<td>" + v.getArtista() + "</td>");
                out.println("<td>" + v.getAnosalida() + "</td>");
                out.println("<td>");
                for (Cancion c : v.getCancionList()) {
                    out.println(c.getIdcancion() + " - " + c.getNombre() + " - " + c.getDuracionmin() + ":" + c.getDuracionseg());
                    out.println("<br />");
                }
                out.println("</td>");
                out.println("<td>" + v.getPrecio() + "</td>");
                out.println("<td>" + v.getUnidades() + "</td>");
                out.println("<td><a href=\"PedidoServlet?action=pedidovinilo&idvinilo=" + v.getIdvinilo() + "\" title=\"Agregar vinilo al carrito de compras\"><img alt=\"Agregar vinilo al carrito de compras\" src=\"img/cart.png\"></a></td>");
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
