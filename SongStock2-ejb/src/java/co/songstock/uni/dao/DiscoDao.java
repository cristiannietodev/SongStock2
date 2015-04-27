/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.dao;

import co.songstock.uni.entity.Disco;
import co.songstock.uni.entity.Disco;
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
public class DiscoDao {

    @PersistenceContext
    EntityManager em;
    
    public void crear(Disco disco) {
        em.persist(disco);
    }
    
    public void actualizar(Disco disco) {
        em.merge(disco);
    }
    
    public void borrar(Disco disco) {
        em.remove(disco);
    }
    
    public List<Disco> findAll() {
        Query query=em.createNamedQuery("Disco.findAll");
        return query.getResultList();
    }
    
}
