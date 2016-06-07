package RyC.fimiModel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObtenerNoticia {
	
	private int cod_salida;
	private String des_salida;
	@Column(name="id_noticias")
	@Id
	private int id;
	private String seccion;
	private String contenido;
	private String titulo;
	@Column (name="url_foto")
	private String foto;
	private int tipo_noticia;
	private int estado;
	private Date fecha;
	
	public ObtenerNoticia(){
		
	}
	public ObtenerNoticia(int cod_salida, String des_salida,String seccion,String contenido,String titulo,String foto,int tipo_noticia,int estado, Date fecha){
		this.cod_salida=cod_salida;
		this.des_salida=des_salida;
		this.seccion=seccion;
		this.contenido=contenido;
		this.titulo=titulo;
		this.foto=foto;
		this.tipo_noticia=tipo_noticia;
		this.estado=estado;
		this.fecha=fecha;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getTipo_noticia() {
		return tipo_noticia;
	}
	public void setTipo_noticia(int tipo_noticia) {
		this.tipo_noticia = tipo_noticia;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
