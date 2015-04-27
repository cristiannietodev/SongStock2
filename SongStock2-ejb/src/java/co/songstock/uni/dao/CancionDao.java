/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.dao;

import co.songstock.uni.entity.Cancion;
import java.util.ArrayList;
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
public class CancionDao {

    @PersistenceContext
    EntityManager em;
    
    public void crear(Cancion cancion) {
        em.persist(cancion);
    }
    
    public void actualizar(Cancion cancion) {
        em.merge(cancion);
    }
    
    public void borrar(Cancion cancion) {
        em.remove(cancion);
    }
    
    public List<Cancion> findAll() {
        Query query=em.createNamedQuery("Cancion.findAll");
        return query.getResultList();
    }
    
    /**
     * <p>
     * Metodo encargado de budcar canciones segun su nombre
     * </p>
     *
     * @param nombre
     * @return
     */
    public List<Cancion> findCancionesByName(String nombre) {
        Query q=em.createNamedQuery("Cancion.findByNombre");
        q.setParameter(1, nombre);
        return q.getResultList();
    }
}
