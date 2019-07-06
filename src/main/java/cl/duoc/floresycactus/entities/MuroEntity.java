/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author jpino
 */
@Entity
@Table(name = "muro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuroEntity.findAll", query = "SELECT m FROM MuroEntity m")
 })
public class MuroEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Long id;
    
    @Size(max = 45)
    @Column(name = "nombre", length = 45)
    private String nombre;
    
    @Size(max = 60)
    @Column(name = "descripcion", length = 60)
    private String descripcion;
    
    @Size(max = 60)
    @Column(name = "ciudad", length = 60)
    private String ciudad;
    
    @Column(name = "tipo_intalacion")
    private Boolean tipoIntalacion;
    
    @Column(name = "estado")
    private Boolean estado;
    
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date fechaCreacion;    
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muroId", fetch = FetchType.LAZY)
    private List<DecisionEntity> decisionEntityList;
    
    @JsonIgnore
    @JoinColumn(name = "clientes_Id", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteEntity clientesId;
    
    @JsonIgnore   
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity usuariosId;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muroId", fetch = FetchType.LAZY)
    private List<HorariosRiegoEntity> horariosRiegoEntityList;

    public MuroEntity() {
    }

    public MuroEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getTipoIntalacion() {
        return tipoIntalacion;
    }

    public void setTipoIntalacion(Boolean tipoIntalacion) {
        this.tipoIntalacion = tipoIntalacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }      

    @XmlTransient
    public List<DecisionEntity> getDecisionEntityList() {
        return decisionEntityList;
    }

    public void setDecisionEntityList(List<DecisionEntity> decisionEntityList) {
        this.decisionEntityList = decisionEntityList;
    }

    public ClienteEntity getClientesId() {
        return clientesId;
    }

    public void setClientesId(ClienteEntity clientesId) {
        this.clientesId = clientesId;
    }

    public UsuarioEntity getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(UsuarioEntity usuariosId) {
        this.usuariosId = usuariosId;
    }

    @XmlTransient
    public List<HorariosRiegoEntity> getHorariosRiegoEntityList() {
        return horariosRiegoEntityList;
    }

    public void setHorariosRiegoEntityList(List<HorariosRiegoEntity> horariosRiegoEntityList) {
        this.horariosRiegoEntityList = horariosRiegoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuroEntity)) {
            return false;
        }
        MuroEntity other = (MuroEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.floresycactus.entities.MuroEntity[ id=" + id + " ]";
    }
    
}
