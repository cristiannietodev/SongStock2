/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.dao;

import co.songstock.uni.entity.Usuario;
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
public class UsuarioDao {

    @PersistenceContext
    EntityManager em;
    
    public void crear(Usuario usuario) {
        em.persist(usuario);
    }
    
    public void actualizar(Usuario usuario) {
        em.merge(usuario);
    }
    
    public void borrar(Usuario usuario) {
        em.remove(usuario);
    }
    
    /**
     * 
     * @return 
     */
    public List<Usuario> findAll() {
        Query query=em.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }
    
    /**
     * 
     * @param idusuario
     * @return 
     */
    public Usuario findById(Integer idusuario) {
        return (em.find(Usuario.class, idusuario));
    }
    
    
    public Usuario findUsuario(String email, String contrasena){
        Usuario usuario;
        Query query = em.createNamedQuery(Usuario.NAME_QUERY_FIND_USUARIO_BY_USER_AND_PASS);
        query.setParameter("email", email);
        query.setParameter("contrasena", contrasena);
        
        List<Usuario> listUsuario=query.getResultList();
        if (!listUsuario.isEmpty()) {
            usuario = listUsuario.get(0);
        } else {
            usuario = null;
        }
        
        return usuario;
    }
    
}
