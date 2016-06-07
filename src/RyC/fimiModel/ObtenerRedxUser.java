package RyC.fimiModel;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObtenerRedxUser {
	private int cod_salida;
	private String des_salida;
	private int id_usuario_red;
	private int id_usuario;
	private int id_red_social;
	private String n_usuario_red;
	
	public ObtenerRedxUser(){
		
	}
	public ObtenerRedxUser(int cod_salida,String des_salida,int id_usuario_red,int id_usuario, int id_red_social,String n_usuario_red){
		this.cod_salida=cod_salida;
		this.des_salida=des_salida;
		this.id_usuario_red=id_usuario_red;
		this.id_usuario=id_usuario;
		this.id_red_social=id_red_social;
		this.n_usuario_red=n_usuario_red;
				
	}
	public String getDes_salida() {
		return des_salida;
	}
	public void setDes_salida(String des_salida) {
		this.des_salida = des_salida;
	}
	public int getCodSalida() {
		return cod_salida;
	}
	public void setCodSalida(int salida) {
		this.cod_salida = salida;
	}
	public int getId_usuario_red() {
		return id_usuario_red;
	}
	public void setId_usuario_red(int id_usuario_red) {
		this.id_usuario_red = id_usuario_red;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_red_social() {
		return id_red_social;
	}
	public void setId_red_social(int id_red_social) {
		this.id_red_social = id_red_social;
	}
	public String getN_usuario_red() {
		return n_usuario_red;
	}
	public void setN_usuario_red(String n_usuario_red) {
		this.n_usuario_red = n_usuario_red;
	}
}
