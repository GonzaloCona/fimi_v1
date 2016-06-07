package RyC.fimiModel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObtenerHistorial {
	private int cod_salida;
	private String des_salida;
	
	private int id_usuario_red;
	private int id_red_social;
	@Id
	private String comentario;
	
	private Date fecha;
	private String tipo_comentario;
	private String id_onombre_quien_comenta;
	
	private int is_falso_positivo;
	
	public ObtenerHistorial(int cod_salida,String des_salida,int id_usuario_red,int id_red_social,String comentario,Date fecha,String tipo_comentario,String id_onombre_quien_comenta,int is_falso_positivo){
		this.cod_salida= cod_salida;
		this.comentario=comentario;
		this.des_salida=des_salida;
		this.id_usuario_red=id_usuario_red;
		this.id_red_social=id_red_social;
		this.fecha=fecha;
		this.tipo_comentario=tipo_comentario;
		this.is_falso_positivo=is_falso_positivo;
		this.id_onombre_quien_comenta=id_onombre_quien_comenta;
	}
    public ObtenerHistorial(){
		
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
	public int getId_usuario_red() {
		return id_usuario_red;
	}
	public void setId_usuario_red(int id_usuario_red) {
		this.id_usuario_red = id_usuario_red;
	}
	public int getId_red_social() {
		return id_red_social;
	}
	public void setId_red_social(int id_red_social) {
		this.id_red_social = id_red_social;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipo_comentario() {
		return tipo_comentario;
	}
	public void setTipo_comentario(String tipo_comentario) {
		this.tipo_comentario = tipo_comentario;
	}
	public String getId_onombre_quien_comenta() {
		return id_onombre_quien_comenta;
	}
	public void setId_onombre_quien_comenta(String id_onombre_quien_comenta) {
		this.id_onombre_quien_comenta = id_onombre_quien_comenta;
	}
	public int getIs_falso_positivo() {
		return is_falso_positivo;
	}
	public void setIs_falso_positivo(int is_falso_positivo) {
		this.is_falso_positivo = is_falso_positivo;
	}
	
	
}
