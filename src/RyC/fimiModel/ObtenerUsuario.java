package RyC.fimiModel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObtenerUsuario {
	private int id_usuario;	
	private String nom1;
	private String nom2;
	private String apell1;
	private String apell2;
	private String emp1;
	private String emp2;
	private String correo;
	private String contrasena;
	private int estado;
	private int perfil_usuario;
	private String keyMovil;
	private List<ObtenerRedxUser> rs;
	private List<ObtenerPalabras> palabras;
	
	public ObtenerUsuario(){
		
	}
	
	public ObtenerUsuario(int id_usuario,int estado,int perfil_usuario,String nom1,String nom2,String apell1,String apell2,String emp1,String emp2,String correo,String contrasena,String keyMovil,List<ObtenerRedxUser> rs,List<ObtenerPalabras> lp){
		this.nom1=nom1;
		this.nom2=nom2;
		this.apell1=apell1;
		this.apell2=apell2;
		this.emp1=emp1;
		this.emp2=emp2;
		this.correo=correo;
		this.contrasena=contrasena;
		this.id_usuario=id_usuario;
		this.estado=estado;
		this.perfil_usuario=perfil_usuario;
		this.keyMovil=keyMovil;
		this.rs=rs;
		this.palabras=lp;
		
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNom1() {
		return nom1;
	}
	public void setNom1(String nom1) {
		this.nom1 = nom1;
	}
	public String getNom2() {
		return nom2;
	}
	public void setNom2(String nom2) {
		this.nom2 = nom2;
	}
	public String getApell1() {
		return apell1;
	}
	public void setApell1(String apell1) {
		this.apell1 = apell1;
	}
	public String getApell2() {
		return apell2;
	}
	public void setApell2(String apell2) {
		this.apell2 = apell2;
	}
	public String getEmp1() {
		return emp1;
	}
	public void setEmp1(String emp1) {
		this.emp1 = emp1;
	}
	public String getEmp2() {
		return emp2;
	}
	public void setEmp2(String emp2) {
		this.emp2 = emp2;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContraseña() {
		return contrasena;
	}
	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getPerfil_usuario() {
		return perfil_usuario;
	}
	public void setPerfil_usuario(int perfil_usuario) {
		this.perfil_usuario = perfil_usuario;
	}
	public String getKeyMovil() {
		return keyMovil;
	}
	public void setKeyMovil(String keyMovil) {
		this.keyMovil = keyMovil;
	}
	public List<ObtenerRedxUser> getRs() {
		return rs;
	}
	public void setRs(List<ObtenerRedxUser> rs) {
		this.rs = rs;
	}

	public List<ObtenerPalabras> getPalabras() {
		return palabras;
	}

	public void setPalabras(List<ObtenerPalabras> palabras) {
		this.palabras = palabras;
	}
	
	
}
