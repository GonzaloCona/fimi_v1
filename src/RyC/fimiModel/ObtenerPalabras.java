package RyC.fimiModel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObtenerPalabras {
	private int cod_salida;
	private String des_salida;
	private int tipo_palabra;
	private String palabra;
	private int id_usuario;

    public ObtenerPalabras(){
		
	}
    public ObtenerPalabras(int cod_salida,String des_salida,int tipo_palabra,int id_usuario,String palabra){
		this.cod_salida=cod_salida;
		this.des_salida=des_salida;
		this.tipo_palabra=tipo_palabra;
		this.id_usuario=id_usuario;
		this.palabra=palabra;
		
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

	public int getTipo_palabra() {
		return tipo_palabra;
	}

	public void setTipo_palabra(int tipo_palabra) {
		this.tipo_palabra = tipo_palabra;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	
}
