/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.dao;

import co.songstock.uni.entity.Mediopago;
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
public class MedioPagoDao {

    @PersistenceContext
    EntityManager em;
    
    public void crear(Mediopago mediopago) {
        em.persist(mediopago);
    }
    
    public void actualizar(Mediopago mediopago) {
        em.merge(mediopago);
    }
    
    public void borrar(Mediopago mediopago) {
        em.remove(mediopago);
    }
    
    /**
     * 
     * @return 
     */
    public List<Mediopago> findAll() {
        Query query=em.createNamedQuery("Mediopago.findAll");
        return query.getResultList();
    }
    
    /**
     * 
     * @param idmediopago
     * @return 
     */
    public Mediopago findById(Integer idmediopago) {
        return (em.find(Mediopago.class, idmediopago));
    }
    
}
