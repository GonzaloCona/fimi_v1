package RyC.dbHIB;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class key_rs_usuarios implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String n_usuario_red;
	private int id_red_social;
	
	public key_rs_usuarios(String n_usuario_red,int id_red_social){
		this.n_usuario_red=n_usuario_red;
		this.id_red_social=id_red_social;
	}
	public key_rs_usuarios(){
		
	}
	public String getN_usuario_red() {
		return n_usuario_red;
	}
	public void setN_usuario_red(String n_usuario_red) {
		this.n_usuario_red = n_usuario_red;
	}
	public int getId_red_social() {
		return id_red_social;
	}
	public void setId_red_social(int id_red_social) {
		this.id_red_social = id_red_social;
	}
	
}
