package RyC.fimi_v0;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.json.JSONObject;

import RyC.dbHIB.*;
import RyC.fimiModel.*;

import RyC.fimiService.FimiService;


@Path("u")
public class RootRest {
	FimiService su = new FimiService();
		
	/////////////////////ejemplos///////////////////////////////////////////
	/*
	@Path("d")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ClassUser getUserFimi(){
		return su.getUser();
	}*/
	
	@Path("hola")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
	
	/*@Path("i")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetails setUserFimi(UserDetails ud){
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(ud);
		session.getTransaction().commit();	
		session.close();
		
		
		return ud;
	}*/
	
	//////////////////////////////////servicios////////////////////////////////////////////
	@Path("getDatosGraf")
	@GET
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ObtenerGrafico> getDatos(@MatrixParam("id_usuario")int id){
		return su.getDatos( id);
		
	}
	
	
	
	@Path("getC")
	@GET
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr getNewContrasena(@MatrixParam("correo")String correo){
		return su.getContrasena(correo);
		
	}
	@Path("SPush")
	@GET
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String calPush(@MatrixParam("id") String id,@MatrixParam("cod") String cod,@MatrixParam("contenido") String con ){
		return su.CalPush(id,cod,con);
		
	}
	@Path("login")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnLogin returnLogin(@MatrixParam("correo") String correo,@MatrixParam("pass") String pass){
		return su.returnLogin(correo, pass);
		
	}
	@Path("lF")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnLogin returnLogin(@MatrixParam("correo") String correo){
		return su.getLogin(correo);
		
	}

	@Path("getRedes")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ObtenerRedxUser> getredes(@MatrixParam("id_usuario") int id){
		return su.getredes(id);
		
	}
	
	
	@Path("getSaludo")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getSaludo(){
		return "Saludos";
		
	}
		
	@Path("setRedes")
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	//@MatrixParam("rsUser") 
	//usuario es nombre usuario red
	public salidaStndr setredes(@MatrixParam("n_usuario_red")String usuario,@MatrixParam("id_usuario")int id_usuario,@MatrixParam("id_red")int id_red){
		return su.insertRedes(usuario,id_usuario,id_red);
		
	}
	
	///////////////////////MAntenedores/////////////////////////////////////////////////
	//USUARIO
	@Path("modUser")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr ModifUser(@MatrixParam("id_usuario") int id,@MatrixParam("pass") String pass,@MatrixParam("emp1") String emp1,@MatrixParam("emp2") String emp2){
		return su.modifUser(id,pass,emp1,emp2);
	}
	
	@Path("setUser")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr InsertUser(@MatrixParam("nombre1") String nom1,@MatrixParam("nombre2") String nom2,@MatrixParam("apellido1")String apell1,@MatrixParam("apellido2") String apell2,@MatrixParam("correo") String correo,@MatrixParam("contraseña")String pass,@MatrixParam("twitter") String twit,@MatrixParam("keyMovil") String keyMovil){
		return su.insertUser(nom1,nom2,apell1,apell2,correo,pass,twit,keyMovil);
	}
	
	@Path("getUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ObtenerUsuario SelectUser(@MatrixParam("id_usuario") int id){
		return su.selectUser(id);
	}
	/*
	public salidaStndr modifUser(usuario newU){
		return su.modificarUser(newU);
	}*/
	
	
	//USUARIO ADMIN
	
	@Path("getAllAdmin")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ObtenerUserAdmin> SelectAllUserAdmin(){
		return su.selectAllUserAdmin();
	}
	
	@Path("getAdmin")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ObtenerUserAdmin SelectUserAdmin(@MatrixParam("correo")String correo){
		return su.selectUserAdmin(correo);
	}
	
	@Path("setAdmin")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr InsertUserAdmin(@MatrixParam("correo")String correo,@MatrixParam("pass")String contrasena,@MatrixParam("nom")String nombre,@MatrixParam("estado")int estado){
		return su.insertUserAdmin(correo,contrasena,nombre,estado);
	}
	@Path("modAdmin")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr ModifUser(@MatrixParam("correo")String correo,@MatrixParam("estado")int estado){
		return su.modifUser(correo,estado);
	}
	
	
	//HISTORIAL
	@Path("getHistorial")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ObtenerHistorial> getPositivo(@MatrixParam("id_usuario") int id){
		return su.selectPositivo(id);
	}
	@Path("getHistorial100")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ObtenerHistorial> getAll100(@MatrixParam("id_usuario") int id){
		return su.selectAll100(id);
	}
	//PALABRAS USUARO
	@Path("setWord")
	@POST
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr InsertWord(@MatrixParam("id_usuario") int id,@MatrixParam("palabra") String palabra){
		return su.insertWord(id,palabra);
	}
	
	/*
	@Path("getWord")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ObtenerPalabras> SelecttWord(@MatrixParam("id") int id ){
		return su.selectWord(id);
	}*/
	
	//REDES POR USUARIO
	@Path("deleteRed")
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr deleteRed(@MatrixParam("id") int id,@MatrixParam("id_red") int id_red ){
		
		return su.deleteredes(id, id_red);
	}
	
	//NOTICIAS
	@Path("setNoticia")
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr InsertNoticia(@MatrixParam("seccion") String seccion,@MatrixParam("contenido") String contenido,@MatrixParam("titulo") String titulo,@MatrixParam("foto") String foto,@MatrixParam("tipo") int tipo,@MatrixParam("estado") int estado,@MatrixParam("fecha") Date fecha) throws UnsupportedEncodingException{
		return su.insertNoticia(seccion, contenido, titulo,  foto,tipo,estado,fecha);
	}
	
	@Path("getNoticias")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ObtenerNoticia> SelectNoticias(){
		return su.selectNoticias();
	}
	
	@Path("modNoticia")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public salidaStndr UpdateNoticiaEstado(@MatrixParam("id")int id,@MatrixParam("estado")int estado){
		return su.updateNoticiaEstado(id,estado);
	}
	
}
