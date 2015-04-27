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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "mediopago")
@NamedQueries({
    @NamedQuery(name = "Mediopago.findAll", query = "SELECT m FROM Mediopago m"),
    @NamedQuery(name = "Mediopago.findByIdmediopago", query = "SELECT m FROM Mediopago m WHERE m.idmediopago = :idmediopago"),
    @NamedQuery(name = "Mediopago.findByMediopago", query = "SELECT m FROM Mediopago m WHERE m.mediopago = :mediopago")})
public class Mediopago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmediopago")
    private Integer idmediopago;
    @Size(max = 255)
    @Column(name = "mediopago")
    private String mediopago;
    @OneToMany(mappedBy = "mediopago")
    private List<Pedido> pedidoList;

    public Mediopago() {
    }

    public Mediopago(Integer idmediopago) {
        this.idmediopago = idmediopago;
    }

    public Integer getIdmediopago() {
        return idmediopago;
    }

    public void setIdmediopago(Integer idmediopago) {
        this.idmediopago = idmediopago;
    }

    public String getMediopago() {
        return mediopago;
    }

    public void setMediopago(String mediopago) {
        this.mediopago = mediopago;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmediopago != null ? idmediopago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mediopago)) {
            return false;
        }
        Mediopago other = (Mediopago) object;
        if ((this.idmediopago == null && other.idmediopago != null) || (this.idmediopago != null && !this.idmediopago.equals(other.idmediopago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.songstock.uni.entity.Mediopago[ idmediopago=" + idmediopago + " ]";
    }
    
}
