package RyC.dbHIB;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="rs_usuario")
public class rs_usuario {
	 
	private int id_usuario_red;
	private int id_usuario;
	@EmbeddedId
	private key_rs_usuarios id_rs_usuario;
	
	public rs_usuario(){
		
	}
	public rs_usuario(key_rs_usuarios id_rs_usuario,int id_usuario_red,int id_usuario){
		this.id_usuario_red=id_usuario_red;
		this.id_usuario=id_usuario;
		this.id_rs_usuario=id_rs_usuario;
		//this.id_red_social=id_red_social;
		//this.n_usuario_red=n_usuario_red;

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
	public key_rs_usuarios getId_rs_usuario() {
		return id_rs_usuario;
	}
	public void setId_rs_usuario(key_rs_usuarios id_rs_usuario) {
		this.id_rs_usuario = id_rs_usuario;
	}
	
	

}
