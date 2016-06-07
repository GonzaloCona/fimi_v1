package RyC.dbHIB;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
//import javax.persistence.
//import javax.validation.constraints.Null;



@Entity
public class historial_usuario {
	@EmbeddedId
	private key_historial_usuario id_historial;	
	private int id_red_social;
	private Date fecha;
	private String tipo_comentario;
	private String id_onombre_quien_comenta;
	private int is_falso_positivo;
	
	public historial_usuario(key_historial_usuario id_historial,int id_red_social,Date fecha,String tipo_comentario,String id_onombre_quien_comenta,int is_falso_positivo){
		this.id_historial=id_historial;
		//this.comentario=comentario;
		//this.id_usuario_red=id_usuario_red;
		this.id_red_social=id_red_social;
		this.fecha=fecha;
		this.tipo_comentario=tipo_comentario;
		this.is_falso_positivo=is_falso_positivo;
		this.id_onombre_quien_comenta=id_onombre_quien_comenta;
	}
	public historial_usuario(){
		
	}
	public key_historial_usuario getId_historial() {
		return id_historial;
	}
	public void setId_historial(key_historial_usuario id_historial) {
		this.id_historial = id_historial;
	}
	
	public int getId_red_social() {
		return id_red_social;
	}
	public void setId_red_social(int id_red_social) {
		this.id_red_social = id_red_social;
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
