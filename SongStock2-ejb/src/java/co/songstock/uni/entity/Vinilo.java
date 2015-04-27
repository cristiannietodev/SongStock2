/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.songstock.uni.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "vinilo")
@NamedQueries({
    @NamedQuery(name = "Vinilo.findAll", query = "SELECT v FROM Vinilo v"),
    @NamedQuery(name = "Vinilo.findByIdvinilo", query = "SELECT v FROM Vinilo v WHERE v.idvinilo = :idvinilo"),
    @NamedQuery(name = "Vinilo.findByArtista", query = "SELECT v FROM Vinilo v WHERE v.artista = :artista"),
    @NamedQuery(name = "Vinilo.findByAnosalida", query = "SELECT v FROM Vinilo v WHERE v.anosalida = :anosalida"),
    @NamedQuery(name = "Vinilo.findByNombrevinilo", query = "SELECT v FROM Vinilo v WHERE v.nombrevinilo = :nombrevinilo"),
    @NamedQuery(name = "Vinilo.findByPrecio", query = "SELECT v FROM Vinilo v WHERE v.precio = :precio"),
    @NamedQuery(name = "Vinilo.findByUnidades", query = "SELECT v FROM Vinilo v WHERE v.unidades = :unidades")})
public class Vinilo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvinilo")
    private Integer idvinilo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "artista")
    private String artista;
    @Column(name = "anosalida")
    private Integer anosalida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombrevinilo")
    private String nombrevinilo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidades")
    private int unidades;
    @ManyToMany(mappedBy = "viniloList")
    private List<Pedido> pedidoList;
    @ManyToMany(mappedBy = "viniloList")
    private List<Cancion> cancionList;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;

    public Vinilo() {
    }

    public Vinilo(Integer idvinilo) {
        this.idvinilo = idvinilo;
    }

    public Vinilo(Integer idvinilo, String artista, String nombrevinilo, double precio, int unidades) {
        this.idvinilo = idvinilo;
        this.artista = artista;
        this.nombrevinilo = nombrevinilo;
        this.precio = precio;
        this.unidades = unidades;
    }

    public Integer getIdvinilo() {
        return idvinilo;
    }

    public void setIdvinilo(Integer idvinilo) {
        this.idvinilo = idvinilo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Integer getAnosalida() {
        return anosalida;
    }

    public void setAnosalida(Integer anosalida) {
        this.anosalida = anosalida;
    }

    public String getNombrevinilo() {
        return nombrevinilo;
    }

    public void setNombrevinilo(String nombrevinilo) {
        this.nombrevinilo = nombrevinilo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }
    
    public void addCancion(Cancion cancion){
        this.cancionList.add(cancion);
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
        hash += (idvinilo != null ? idvinilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vinilo)) {
            return false;
        }
        Vinilo other = (Vinilo) object;
        if ((this.idvinilo == null && other.idvinilo != null) || (this.idvinilo != null && !this.idvinilo.equals(other.idvinilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.songstock.uni.entity.Vinilo[ idvinilo=" + idvinilo + " ]";
    }
    
}
