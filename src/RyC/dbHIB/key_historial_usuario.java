package RyC.dbHIB;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class key_historial_usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id_usuario_red;
	private String comentario;
	
	public key_historial_usuario(){
		
	}
	public key_historial_usuario(int id_usuario_red,String comentario){
		this.id_usuario_red=id_usuario_red;
		this.comentario=comentario;
	}
	
	public int getId_usuario_red() {
		return id_usuario_red;
	}
	public void setId_usuario_red(int id_usuario_red) {
		this.id_usuario_red = id_usuario_red;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
