package RyC.fimiModel;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReturnLogin {
	private int cod_salida;
	private String des_salida;
	private int idUsuario;
	private String nombre;
	private int perfil;
	private int estado;
	
	public ReturnLogin(){
		
	}
	
	public ReturnLogin(int cod_salida,String des_salida,String nombre,int estado, int idUsuario,int perfil){
		this.cod_salida=cod_salida;
		this.des_salida=des_salida;
		this.nombre=nombre;
		this.estado=estado;
		this.idUsuario=idUsuario;
		this.perfil=perfil;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPerfil() {
		return perfil;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	
}
