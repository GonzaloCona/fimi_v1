package RyC.dbHIB;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.persistence.Embeddable;

@Embeddable
public class key_palabras_usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String palabra;
	private int tipo_palabra;
	private int id_usuario;
	
	public key_palabras_usuario(){
		
	}
	public key_palabras_usuario(String palabra,int tipo_palabra,int id) throws UnsupportedEncodingException{
		
		byte[] byteText = palabra.getBytes(Charset.forName("UTF-8"));
		String palabra_aux= new String(byteText , "UTF-8");
		palabra=palabra_aux;
		
		this.palabra=palabra;
		this.tipo_palabra=tipo_palabra;
		this.id_usuario=id;
	}
	
	public int getId() {
		return id_usuario;
	}
	public void setId(int id) {
		this.id_usuario = id;
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
