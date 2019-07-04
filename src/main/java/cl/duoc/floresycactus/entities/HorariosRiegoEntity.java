/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jpino
 */
@Entity
@Table(name = "horarios_riego")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorariosRiegoEntity.findAll", query = "SELECT h FROM HorariosRiegoEntity h")
})
public class HorariosRiegoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Long id;
    
    @Size(max = 45)
    @Column(name = "Horario", length = 45)
    private String horario;
    
    @Column(name = "estado")
    private Boolean estado;
    
    @JsonIgnore
    @JoinColumn(name = "muro_Id", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MuroEntity muroId;

    public HorariosRiegoEntity() {
    }

    public HorariosRiegoEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public MuroEntity getMuroId() {
        return muroId;
    }

    public void setMuroId(MuroEntity muroId) {
        this.muroId = muroId;
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
        if (!(object instanceof HorariosRiegoEntity)) {
            return false;
        }
        HorariosRiegoEntity other = (HorariosRiegoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.floresycactus.entities.HorariosRiegoEntity[ id=" + id + " ]";
    }
    
}
