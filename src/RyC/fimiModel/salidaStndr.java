package RyC.fimiModel;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class salidaStndr {
	private int cod_salida;
	private String des_salida;
	
	public salidaStndr(){
		
	}
	public salidaStndr(int cod_salida,String des_salida){
		this.cod_salida=cod_salida;
		this.des_salida=des_salida;
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
	
}
