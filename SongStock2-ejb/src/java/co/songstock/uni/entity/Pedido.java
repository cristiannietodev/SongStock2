/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdpedido", query = "SELECT p FROM Pedido p WHERE p.idpedido = :idpedido"),
    @NamedQuery(name = "Pedido.findByEnviado", query = "SELECT p FROM Pedido p WHERE p.enviado = :enviado"),
    @NamedQuery(name = "Pedido.findByRechazado", query = "SELECT p FROM Pedido p WHERE p.rechazado = :rechazado"),
    @NamedQuery(name = "Pedido.findByCalificacion", query = "SELECT p FROM Pedido p WHERE p.calificacion = :calificacion"),
    @NamedQuery(name = Pedido.NAME_QUERY_FIND_PEDIDOS_BY_USUARIO_PROVEEDOR,query = Pedido.QUERY_FIND_PEDIDOS_BY_USUARIO_PROVEEDOR),
    @NamedQuery(name = Pedido.NAME_QUERY_FIND_PEDIDOS_BY_USUARIO_COMPRADOR,query = Pedido.QUERY_FIND_PEDIDOS_BY_USUARIO_COMPRADOR)
})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Integer idpedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enviado")
    private short enviado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rechazado")
    private short rechazado;
    @Column(name = "calificacion")
    private Integer calificacion;
    @JoinTable(name = "pedido_vinilo", joinColumns = {
        @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")}, inverseJoinColumns = {
        @JoinColumn(name = "idvinilo", referencedColumnName = "idvinilo")})
    @ManyToMany
    private List<Vinilo> viniloList=new ArrayList<>();
    @JoinColumn(name = "idmediopago", referencedColumnName = "idmediopago")
    @ManyToOne
    private Mediopago mediopago;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;

    public Pedido() {
    }

    public Pedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Pedido(Integer idpedido, short enviado, short rechazado) {
        this.idpedido = idpedido;
        this.enviado = enviado;
        this.rechazado = rechazado;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public short getEnviado() {
        return enviado;
    }

    public void setEnviado(short enviado) {
        this.enviado = enviado;
    }

    public short getRechazado() {
        return rechazado;
    }

    public void setRechazado(short rechazado) {
        this.rechazado = rechazado;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Vinilo> getViniloList() {
        return viniloList;
    }

    public void setViniloList(List<Vinilo> viniloList) {
        this.viniloList = viniloList;
    }
    
    public void addVinilos(Vinilo vinilo){
        this.viniloList.add(vinilo);
    }

    public Mediopago getMediopago() {
        return mediopago;
    }

    public void setMediopago(Mediopago mediopago) {
        this.mediopago = mediopago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.songstock.uni.entity.Pedido[ idpedido=" + idpedido + " ]";
    }
    
    
    public static final String NAME_QUERY_FIND_PEDIDOS_BY_USUARIO_PROVEEDOR="findPedidosByUsuarioProveedor";
    public static final String QUERY_FIND_PEDIDOS_BY_USUARIO_PROVEEDOR="select p from Pedido p join p.viniloList as v  join v.usuario as u where u.idusuario=:idusuario";
    
    public static final String NAME_QUERY_FIND_PEDIDOS_BY_USUARIO_COMPRADOR="findPedidosByUsuarioComprador";
    public static final String QUERY_FIND_PEDIDOS_BY_USUARIO_COMPRADOR="select p from Pedido p where p.usuario.idusuario=:idusuario";
    
}
