package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UsuarioSequence")
	@SequenceGenerator(allocationSize=1, name = "UsuarioSequence")
	private long usuarioId;
	@Column(unique=true)
	private String mail;
	private String nombre;
	private String apellidos;
	private String password;
	private boolean bloqueado;
	@ManyToOne (cascade= CascadeType.MERGE)
	private Ciudad ciudad;
	//@OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.PERSIST)
	@OneToMany
	//@Transient
	private List<Tarjeta> tarjetas;
	//@OneToMany
	//@Transient
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}   
	public long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}   
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}   
	public List<Tarjeta> getTarjetas() {
		return this.tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	} 
	

   
}
