/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.songstock.uni.servlets;

import co.songstock.uni.dao.MedioPagoDao;
import co.songstock.uni.dao.PedidoDao;
import co.songstock.uni.dao.ViniloDao;
import co.songstock.uni.entity.Mediopago;
import co.songstock.uni.entity.Pedido;
import co.songstock.uni.entity.Usuario;
import co.songstock.uni.entity.Vinilo;
import co.songstock.uni.util.CorreoElectronico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.mail.MessagingException;
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
@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

    //private static final Logger logger = LoggerFactory.getLogger(PedidoServlet.class);
    
    @EJB
    private ViniloDao viniloDao;
    
    @EJB
    private PedidoDao pedidoDao;
    
    @EJB
    private MedioPagoDao mediospagoDao;
    

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

        if (action.equals("pedidocancion")) {
            //registrarProveedor(request, response);
        } else {
            if (action.equals("pedidovinilo")) {
                formPedidoVinilo(request, response);
            } else {
                if (action.equals("registrarpedido")) {
                    registrarPedidoVinilo(request, response);
                } else {
                    if (action.equals("verpedidos")) {
                        verPedidos(request, response);
                    } else {
                        if (action.equals("marcarpedido")) {
                            marcarPedido(request, response);
                        } else {
                            if (action.equals("rechazarpedido")) {
                                rechazarPedido(request, response);
                            } else {
                                if (action.equals("aceptarpedido")) {
                                    aceptarPedido(request, response);
                                } else {
                                    if (action.equals("vercompras")) {
                                        verCompras(request, response);
                                    } else {
                                        if (action.equals("calificarpedido")) {
                                            calificarPedido(request, response);
                                        } else {
                                            //logger.error("Sin accion - proveedor");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
    private void formPedidoVinilo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {

            String idvinilo = request.getParameter("idvinilo");

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registrar pedido vinilo</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylecss3.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Registrar pedido vinilo</h1>");
            out.println("<form id=\"pedidovinilo\" action=\"PedidoServlet\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"action\" value=\"registrarpedido\" />");
            out.println("<input type=\"hidden\" name=\"idvinilo\" value=\"" + idvinilo + "\" />");
            out.println("<input type=\"hidden\" name=\"idusuario\" value=\"" + usuario.getIdusuario() + "\" />");
            out.println("<br />");
            out.println("<select id=\"idmediopago\" name=\"idmediopago\" required=\"required\">");
            out.println("<option value=\"\">Seleccione</option>");
            List<Mediopago> mediospago = mediospagoDao.findAll();
            for (Mediopago m : mediospago) {
                out.println("<option value='" + m.getIdmediopago() + "'>" + m.getMediopago() + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Registrar pedido\" /> ");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
        }

    }

    private void registrarPedidoVinilo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {

            String idvinilo = request.getParameter("idvinilo");
            String idusuario = request.getParameter("idusuario");
            String idmediopago = request.getParameter("idmediopago");

            Integer idvinilo_ = Integer.parseInt(idvinilo);
            Integer idusuario_ = Integer.parseInt(idusuario);
            Integer mediopago_ = Integer.parseInt(idmediopago);

            Vinilo vinilo = viniloDao.findById(idvinilo_);

            Mediopago mediopago = mediospagoDao.findById(mediopago_);

            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setMediopago(mediopago);
            pedido.addVinilos(vinilo);
            pedido.setEnviado((short)0);
            pedido.setRechazado((short)1);

            pedidoDao.crear(pedido);
            
            try {

                CorreoElectronico correo = new CorreoElectronico();

                correo.enviar("inventario.pruebas.uni@gmail.com", "pruebasmailinv", "Su registro un nuevo pedido", "Se registro un nuevo pedido en la APP SongStock unimonito!", vinilo.getUsuario().getEmail());

            } catch (MessagingException e) {
                //logger.error("Hay un error en el servicio de envio de correos", e);
            }

            out.print("<font color=green>Se registro el pedido correctamente</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/cancionesServlet?action=listarcanciones");
            rd.include(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void verPedidos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {

            List<Pedido> pedidos = pedidoDao.findPedidosByUsuarioProveedor(usuario.getIdusuario());

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de pedidos</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylecss3.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de pedidos</h1>");
            out.println("<table class=\"responstable\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Id pedido</td>");
            out.println("<td>Nombre comprador</td>");
            out.println("<td>Correo</td>");
            out.println("<td>Numero de compras</td>");
            out.println("<td>Medio de pago</td>");
            out.println("<td>Vinilo</td>");
            out.println("<td>Artista</td>");
            out.println("<td>Marcar enviado</td>");
            out.println("<td>Rechazar pedido</td>");
            out.println("</tr>");
            out.println("</thead>");
            for (Pedido p : pedidos) {
                out.println("<tr>");
                out.println("<td>" + p.getIdpedido() + "</td>");
                out.println("<td>" + p.getUsuario().getNombre() + "</td>");
                out.println("<td>" + p.getUsuario().getEmail() + "</td>");
                out.println("<td>" + p.getUsuario().getCantidadcompras() + "</td>");
                out.println("<td>" + p.getMediopago().getMediopago() + "</td>");
                List<Vinilo> vinilos = p.getViniloList();
                out.println("<td>");
                for (Vinilo v : vinilos) {
                    out.println(v.getNombrevinilo());
                    out.println("<br />");
                }
                out.println("</td>");
                out.println("<td>");
                for (Vinilo v : vinilos) {
                    out.println(v.getArtista());
                    out.println("<br />");
                }
                out.println("</td>");

                if (p.getRechazado()==0) {
                    if (p.getEnviado()==1) {
                        out.println("<td>Pedido enviado</td>");
                    } else {
                        out.println("<td><a href=\"PedidoServlet?action=marcarpedido&idpedido=" + p.getIdpedido() + "\" title=\"Enviar pedido\">Enviar pedido</a></td>");
                    }
                } else {
                    out.println("<td>Pedido rechazado</td>");
                }

                if (p.getEnviado()==0) {
                    if (p.getRechazado()==1) {
                        out.println("<td><a href=\"PedidoServlet?action=aceptarpedido&idpedido=" + p.getIdpedido() + "\" title=\"Aceptar pedido\">Aceptar pedido</a></td>");
                    } else {
                        out.println("<td><a href=\"PedidoServlet?action=rechazarpedido&idpedido=" + p.getIdpedido() + "\" title=\"Rechazar pedido\">Rechazar pedido</a></td>");
                    }
                } else {
                    out.println("<td></td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
//            RequestDispatcher rd = request.getRequestDispatcher("/pages/usuario/loginusuario.jsp");
//            rd.include(request, response);
        }
    }

    private void marcarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            String idpedido = request.getParameter("idpedido");

            Integer idpedido_ = Integer.parseInt(idpedido);

            Pedido pedido = pedidoDao.findById(idpedido_);

            pedido.setEnviado((short)1);

            int cantidadcompras = pedido.getUsuario().getCantidadcompras();

            pedido.getUsuario().setCantidadcompras(cantidadcompras + 1);

            pedidoDao.actualizar(pedido);

            try {

                CorreoElectronico correo = new CorreoElectronico();

                correo.enviar("inventario.pruebas.uni@gmail.com", "pruebasmailinv", "Su pedido ha sido enviado", "Queremos informarle que su pedido en la APP SongStock unimonito fue ENVIADO!", pedido.getUsuario().getEmail());

            } catch (MessagingException e) {
                //logger.error("Hay un error en el servicio de envio de correos", e);
            }

            out.println("<font color=green>Se envio el pedido correctamente</font>");
            RequestDispatcher rd = request.getRequestDispatcher("PedidoServlet?action=verpedidos");
            rd.include(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void rechazarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            String idpedido = request.getParameter("idpedido");

            Integer idpedido_ = Integer.parseInt(idpedido);

            Pedido pedido = pedidoDao.findById(idpedido_);

            pedido.setRechazado((short)1);

            pedidoDao.actualizar(pedido);

            try {

                CorreoElectronico correo = new CorreoElectronico();

                correo.enviar("inventario.pruebas.uni@gmail.com", "pruebasmailinv", "Su pedido ha sido rechazado", "Queremos informarle que su pedido en la APP SongStock unimonito fue RECHAZADO!", pedido.getUsuario().getEmail());

            } catch (MessagingException e) {
                //logger.error("Hay un error en el servicio de envio de correos", e);
            }

            out.println("<font color=green>Se rechazo el pedido</font>");
            RequestDispatcher rd = request.getRequestDispatcher("PedidoServlet?action=verpedidos");
            rd.include(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void aceptarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            String idpedido = request.getParameter("idpedido");

            Integer idpedido_ = Integer.parseInt(idpedido);

            Pedido pedido = pedidoDao.findById(idpedido_);

            pedido.setRechazado((short)0);

            pedidoDao.actualizar(pedido);

            try {

                CorreoElectronico correo = new CorreoElectronico();

                correo.enviar("inventario.pruebas.uni@gmail.com", "pruebasmailinv", "Su pedido ha sido aceptado", "Queremos informarle que su pedido en la APP SongStock unimonito fue ACEPTADO!", pedido.getUsuario().getEmail());

            } catch (MessagingException e) {
                //logger.error("Hay un error en el servicio de envio de correos", e);
            }

            out.println("<font color=green>Se acepto el pedido</font>");
            RequestDispatcher rd = request.getRequestDispatcher("PedidoServlet?action=verpedidos");
            rd.include(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
        }

    }

    private void verCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {

            List<Pedido> pedidos = pedidoDao.findPedidosByUsuarioComprador(usuario.getIdusuario());

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de pedidos</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylecss3.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de compras</h1>");
            out.println("<table class=\"responstable\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Id pedido</td>");
            out.println("<td>Medio de pago</td>");
            out.println("<td>Vinilo</td>");
            out.println("<td>Artista</td>");
            out.println("<td>Estado</td>");
            out.println("<td>Calificacion</td>");
            out.println("<td>Calificar</td>");
            out.println("</tr>");
            out.println("</thead>");
            for (Pedido p : pedidos) {
                out.println("<tr>");
                out.println("<td>" + p.getIdpedido() + "</td>");
                out.println("<td>" + p.getMediopago().getMediopago() + "</td>");
                List<Vinilo> vinilos = p.getViniloList();
                out.println("<td>");
                for (Vinilo v : vinilos) {
                    out.println(v.getNombrevinilo());
                    out.println("<br />");
                }
                out.println("</td>");
                out.println("<td>");
                for (Vinilo v : vinilos) {
                    out.println(v.getArtista());
                    out.println("<br />");
                }
                out.println("</td>");

                if (p.getEnviado()==1) {
                    out.println("<td>Pedido enviado</td>");
                } else {
                    if (p.getRechazado()==1) {
                        out.println("<td>Pedido rechazado</td>");
                    } else {
                        out.println("<td>Pedido aceptado</td>");
                    }
                }

                if (p.getCalificacion() == null) {
                    out.println("<td>Sin calificar</td>");
                } else {
                    out.println("<td>" + p.getCalificacion() + "</td>");
                }

                if (p.getEnviado()==1) {
                    out.println("<td><form name=\"calificacionpedido\" method=\"post\" action=\"PedidoServlet\"><input type=\"hidden\" name=\"idpedido\" value=\"" + p.getIdpedido() + "\"/><input type=\"hidden\" name=\"action\" value=\"calificarpedido\"/><input type=\"number\" name=\"calificacion\" required=\"required\" min=\"1\" max=\"5\" step=\"1\" /><input type=\"submit\" value=\"Calificar\"/>  </form></td>");
                } else {
                    out.println("<td></td>");
                }

                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void calificarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            String idpedido = request.getParameter("idpedido");

            String calificacion = request.getParameter("calificacion");

            Integer idpedido_ = Integer.parseInt(idpedido);

            Integer calificacion_ = Integer.parseInt(calificacion);

            Pedido pedido = pedidoDao.findById(idpedido_);

            pedido.setCalificacion(calificacion_);

            pedidoDao.actualizar(pedido);

//            try {
//
//                CorreoElectronico correo = new CorreoElectronico();
//
//                correo.enviar("inventario.pruebas.uni@gmail.com", "pruebasmailinv", "Su pedido ha sido aceptado", "Queremos informarle que su pedido en la APP SongStock unimonito fue ACEPTADO!", "cristianandresnieto@gmail.com");
//
//            } catch (MessagingException e) {
//                logger.error("Hay un error en el servicio de envio de correos", e);
//            }
            out.println("<font color=green>Se califico el pedido de forma correcta</font>");
            RequestDispatcher rd = request.getRequestDispatcher("PedidoServlet?action=vercompras");
            rd.include(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/pages/usuario/loginusuario.jsp");
        }

    }
}
