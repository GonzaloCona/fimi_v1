package RyC.dbHIB;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class admin_user {
	@Id
	private String correo;
	private String contrasena;
	private String nombre;
	private int estado;
	
	public admin_user(){	
	}
	public admin_user(String correo,String contrasena,String nombre, int estado){
		this.correo=correo;
		this.contrasena=contrasena;
		this.nombre=nombre;
		this.estado=estado;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
