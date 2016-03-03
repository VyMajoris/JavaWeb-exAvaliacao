package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAttribute;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import br.com.fiap.converter.Identifiable;

@NamedQueries({
	@NamedQuery(name = "findUsuario", query = "from Usuario u where u.rm = :rm and u.senha = :senha")
})

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="seq", initialValue=10000, allocationSize=1)
public class Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656098653486072443L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq")
	private Long rm;
	private String senha;
	private TipoUsuarioEnum tipo;

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuarioEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuarioEnum tipo) {
		this.tipo = tipo;
	}
	public Long getRm() {
		return rm;
	}
	public void setRm(Long rm) {
		this.rm = rm;
	}
	public Long getId() {
		return rm;
	}
	public void setId(Long rm) {
		this.rm = rm;
	}

    @Override
    public int hashCode() {
        return (getId() != null) 
            ? (getClass().getSimpleName().hashCode() + getId().hashCode())
            : super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getId() != null
                && other.getClass().isAssignableFrom(getClass()) 
                && getClass().isAssignableFrom(other.getClass())) 
            ? getId().equals(((Usuario) other).getId())
            : (other == this);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
    }
	

}
