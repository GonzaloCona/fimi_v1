package RyC.fimiModel;

public class RespuestaPush {
	private String tipo;
	private String descripcion;
	private String cuerpo;
	
	public RespuestaPush(){
		
	}
	
	public RespuestaPush(String tipo,String descripcion,String cuerpo){
		this.tipo=tipo;
		this.descripcion=descripcion;
		this.cuerpo=cuerpo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
}
