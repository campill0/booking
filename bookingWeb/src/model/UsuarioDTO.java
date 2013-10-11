package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long usuarioId;
	private String mail;
	private String nombre;
	private String apellidos;
	private String password;
	private CiudadDTO ciudad;
	private boolean bloqueado;
	private List<TarjetaDTO> tarjetas;
	
	public boolean isBloqueado() {
		return bloqueado;
	}
	public boolean isAdmin(){
		return mail.equals("admin");
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public UsuarioDTO() {
		super();
		this.tarjetas= new ArrayList<TarjetaDTO>();
		this.bloqueado=true;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CiudadDTO getCiudad() {
		return ciudad;
	}
	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}
	public List<TarjetaDTO> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(List<TarjetaDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}
	

	public long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
