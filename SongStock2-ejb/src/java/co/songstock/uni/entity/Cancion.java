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
@Table(name = "cancion")
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c"),
    @NamedQuery(name = "Cancion.findByIdcancion", query = "SELECT c FROM Cancion c WHERE c.idcancion = :idcancion"),
    @NamedQuery(name = "Cancion.findByCalidad", query = "SELECT c FROM Cancion c WHERE c.calidad = :calidad"),
    @NamedQuery(name = "Cancion.findByDuracionmin", query = "SELECT c FROM Cancion c WHERE c.duracionmin = :duracionmin"),
    @NamedQuery(name = "Cancion.findByDuracionseg", query = "SELECT c FROM Cancion c WHERE c.duracionseg = :duracionseg"),
    @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cancion.findByPrecio", query = "SELECT c FROM Cancion c WHERE c.precio = :precio"),
    @NamedQuery(name = "Cancion.findByTamano", query = "SELECT c FROM Cancion c WHERE c.tamano = :tamano")})
public class Cancion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcancion")
    private Integer idcancion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calidad")
    private Double calidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracionmin")
    private int duracionmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracionseg")
    private int duracionseg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "tamano")
    private Double tamano;
    @ManyToMany(mappedBy = "cancionList")
    private List<Disco> discoList=new ArrayList<>();
    @JoinTable(name = "vinilo_disco", joinColumns = {
        @JoinColumn(name = "idcancion", referencedColumnName = "idcancion")}, inverseJoinColumns = {
        @JoinColumn(name = "idvinilo", referencedColumnName = "idvinilo")})
    @ManyToMany
    private List<Vinilo> viniloList=new ArrayList<>();

    public Cancion() {
    }

    public Cancion(Integer idcancion) {
        this.idcancion = idcancion;
    }

    public Cancion(Integer idcancion, int duracionmin, int duracionseg, String nombre) {
        this.idcancion = idcancion;
        this.duracionmin = duracionmin;
        this.duracionseg = duracionseg;
        this.nombre = nombre;
    }

    public Integer getIdcancion() {
        return idcancion;
    }

    public void setIdcancion(Integer idcancion) {
        this.idcancion = idcancion;
    }

    public Double getCalidad() {
        return calidad;
    }

    public void setCalidad(Double calidad) {
        this.calidad = calidad;
    }

    public int getDuracionmin() {
        return duracionmin;
    }

    public void setDuracionmin(int duracionmin) {
        this.duracionmin = duracionmin;
    }

    public int getDuracionseg() {
        return duracionseg;
    }

    public void setDuracionseg(int duracionseg) {
        this.duracionseg = duracionseg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    

    public Double getTamano() {
        return tamano;
    }

    public void setTamano(Double tamano) {
        this.tamano = tamano;
    }

    public List<Disco> getDiscoList() {
        return discoList;
    }

    public void setDiscoList(List<Disco> discoList) {
        this.discoList = discoList;
    }

    public List<Vinilo> getViniloList() {
        return viniloList;
    }

    public void setViniloList(List<Vinilo> viniloList) {
        this.viniloList = viniloList;
    }
    
    public void addVinilo(Vinilo vinilo){
        this.viniloList.add(vinilo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcancion != null ? idcancion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.idcancion == null && other.idcancion != null) || (this.idcancion != null && !this.idcancion.equals(other.idcancion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.songstock.uni.entity.Cancion[ idcancion=" + idcancion + " ]";
    }
    
}
