package RyC.fimiModel;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObtenerUserAdmin {
	private int cod_salida;
	private String des_salida;
	@Id
	private String correo;
	private String nombre;
	private String contrasena;
	private int estado;
	
	public ObtenerUserAdmin(){
		
	}
	public ObtenerUserAdmin(int cod_salida,String des_salida,String correo,String nombre,String contrasena, int estado){
		this.cod_salida=cod_salida;
		this.des_salida=des_salida;
		this.correo=correo;
		this.nombre=nombre;
		this.contrasena=contrasena;
		this.estado=estado;
	}
	public int getCod_salida() {
		return cod_salida;
	}
	public void setCod_salida(int cod_salida) {
		this.cod_salida = cod_salida;
	}
	public String getDes_salida() {
		return des_salida;
	}
	public void setDes_salida(String des_salida) {
		this.des_salida = des_salida;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
