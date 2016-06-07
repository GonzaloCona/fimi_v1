package RyC.dbHIB;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class palabras_usuario {
	
	@EmbeddedId
	key_palabras_usuario id_palabras_usuarios;
	
	public palabras_usuario(){	
	}
	public palabras_usuario(key_palabras_usuario id_palabras_usuarios){
		this.id_palabras_usuarios=id_palabras_usuarios;
	}
	
	public key_palabras_usuario getId_palabras_usuarios() {
		return id_palabras_usuarios;
	}
	public void setId_palabras_usuarios(key_palabras_usuario id_palabras_usuarios) {
		this.id_palabras_usuarios = id_palabras_usuarios;
	}
	
}
