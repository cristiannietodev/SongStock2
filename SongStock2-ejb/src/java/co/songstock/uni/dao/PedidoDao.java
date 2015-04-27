/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.dao;

import co.songstock.uni.entity.Pedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristian
 */
@Stateless
@LocalBean
public class PedidoDao {

    @PersistenceContext
    EntityManager em;
    
    public void crear(Pedido pedido) {
        em.persist(pedido);
    }
    
    public void actualizar(Pedido pedido) {
        em.merge(pedido);
    }
    
    public void borrar(Pedido pedido) {
        em.remove(pedido);
    }
    
    /**
     * 
     * @return 
     */
    public List<Pedido> findAll() {
        Query query=em.createNamedQuery("Pedido.findAll");
        return query.getResultList();
    }
    
    /**
     * 
     * @param idpedido
     * @return 
     */
    public Pedido findById(Integer idpedido) {
        return (em.find(Pedido.class, idpedido));
    }
    
    /**
     * 
     * @param idusuario
     * @return 
     */
    public List<Pedido> findPedidosByUsuarioProveedor(Integer idusuario) {
        Query query=em.createNamedQuery(Pedido.NAME_QUERY_FIND_PEDIDOS_BY_USUARIO_PROVEEDOR);
        
        query.setParameter("idusuario", idusuario);
        
        return query.getResultList();
    }
    
    /**
     * 
     * @param idusuario
     * @return 
     */
    public List<Pedido> findPedidosByUsuarioComprador(Integer idusuario) {
        Query query=em.createNamedQuery(Pedido.NAME_QUERY_FIND_PEDIDOS_BY_USUARIO_COMPRADOR);
        
        query.setParameter("idusuario", idusuario);
        
        return query.getResultList();
    }
}
