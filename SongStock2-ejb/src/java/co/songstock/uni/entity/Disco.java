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
@Table(name = "disco")
@NamedQueries({
    @NamedQuery(name = "Disco.findAll", query = "SELECT d FROM Disco d"),
    @NamedQuery(name = "Disco.findByIddisco", query = "SELECT d FROM Disco d WHERE d.iddisco = :iddisco"),
    @NamedQuery(name = "Disco.findByArtista", query = "SELECT d FROM Disco d WHERE d.artista = :artista"),
    @NamedQuery(name = "Disco.findByGenero", query = "SELECT d FROM Disco d WHERE d.genero = :genero"),
    @NamedQuery(name = "Disco.findByImagen", query = "SELECT d FROM Disco d WHERE d.imagen = :imagen"),
    @NamedQuery(name = "Disco.findByNombre", query = "SELECT d FROM Disco d WHERE d.nombre = :nombre")})
public class Disco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddisco")
    private Integer iddisco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "artista")
    private String artista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "cancion_disco", joinColumns = {
        @JoinColumn(name = "iddisco", referencedColumnName = "iddisco")}, inverseJoinColumns = {
        @JoinColumn(name = "idcancion", referencedColumnName = "idcancion")})
    @ManyToMany
    private List<Cancion> cancionList;

    public Disco() {
    }

    public Disco(Integer iddisco) {
        this.iddisco = iddisco;
    }

    public Disco(Integer iddisco, String artista, String genero, String imagen, String nombre) {
        this.iddisco = iddisco;
        this.artista = artista;
        this.genero = genero;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public Integer getIddisco() {
        return iddisco;
    }

    public void setIddisco(Integer iddisco) {
        this.iddisco = iddisco;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddisco != null ? iddisco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disco)) {
            return false;
        }
        Disco other = (Disco) object;
        if ((this.iddisco == null && other.iddisco != null) || (this.iddisco != null && !this.iddisco.equals(other.iddisco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.songstock.uni.entity.Disco[ iddisco=" + iddisco + " ]";
    }
    
}
