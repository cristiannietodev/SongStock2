/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.dao;

import co.songstock.uni.entity.Vinilo;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristian
 */
@Stateless
@LocalBean
public class ViniloDao {
    
    
    @PersistenceContext
    EntityManager em;
    
    public void crear(Vinilo vinilo) {
        em.persist(vinilo);
    }
    
    public void actualizar(Vinilo vinilo) {
        em.merge(vinilo);
    }
    
    public void borrar(Vinilo vinilo) {
        em.remove(vinilo);
    }
    
    /**
     * 
     * @return 
     */
    public List<Vinilo> findAll() {
        Query query=em.createNamedQuery("Vinilo.findAll");
        return query.getResultList();
    }
    
    /**
     * 
     * @param idvinilo
     * @return 
     */
    public Vinilo findById(Integer idvinilo) {
        return (em.find(Vinilo.class, idvinilo));
    }
}
