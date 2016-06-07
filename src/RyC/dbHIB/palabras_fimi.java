package RyC.dbHIB;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class palabras_fimi {
	@EmbeddedId
	private  key_palabras_fimi key;
	
	public palabras_fimi(){
		
	}
	public palabras_fimi(key_palabras_fimi key){
		this.key=key;
	}
	public key_palabras_fimi getKey() {
		return key;
	}
	public void setKey(key_palabras_fimi key) {
		this.key = key;
	}
	
}
