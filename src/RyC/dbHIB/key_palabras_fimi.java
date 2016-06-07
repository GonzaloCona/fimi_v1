package RyC.dbHIB;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class key_palabras_fimi implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String palabra;
	private int tipo_palabra;
	
	public key_palabras_fimi(){
		
	}
	public key_palabras_fimi(String palabra,int tipo_palabra){
		this.palabra=palabra;
		this.tipo_palabra=tipo_palabra;
	
	}
	public String getPalabra() {
		return palabra;
	}
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	public int getTipo_palabra() {
		return tipo_palabra;
	}
	public void setTipo_palabra(int tipo_palabra) {
		this.tipo_palabra = tipo_palabra;
	}
	
}
